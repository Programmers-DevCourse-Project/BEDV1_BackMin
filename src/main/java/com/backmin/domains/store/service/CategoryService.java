package com.backmin.domains.store.service;

import com.backmin.domains.store.converter.CategoryConverter;
import com.backmin.domains.store.domain.Category;
import com.backmin.domains.store.domain.CategoryRepository;
import com.backmin.domains.store.dto.CategoryInfoAtList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryConverter categoryConverter;

    public List<CategoryInfoAtList> readAllCategories() {
        final List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryConverter::convertCategoryToCategoryInfoAtList)
                .collect(Collectors.toList());
    }

}
