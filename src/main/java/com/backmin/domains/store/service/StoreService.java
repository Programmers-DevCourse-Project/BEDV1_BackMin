package com.backmin.domains.store.service;

import static com.backmin.domains.common.enums.ErrorInfo.*;

import com.backmin.config.exception.BusinessException;
import com.backmin.domains.common.dto.PageResult;
import com.backmin.domains.common.enums.ErrorInfo;
import com.backmin.domains.menu.converter.MenuConverter;
import com.backmin.domains.menu.domain.Menu;
import com.backmin.domains.menu.domain.MenuRepository;
import com.backmin.domains.menu.dto.response.BestMenuResult;
import com.backmin.domains.menu.dto.response.StoreMenuReadResult;
import com.backmin.domains.review.domain.ReviewRepository;
import com.backmin.domains.store.converter.StoreConverter;
import com.backmin.domains.store.domain.Store;
import com.backmin.domains.store.domain.StoreRepository;
import com.backmin.domains.store.dto.response.StoreReadResult;
import com.backmin.domains.store.dto.response.StoresReadResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final ReviewRepository reviewRepository;
    private final StoreConverter storeConverter;
    private final MenuConverter menuConverter;

    /**
     * 카테고리 Id로 해당하는 가게를 페이징 조회
     */
    public PageResult<StoresReadResult> getStoresByCategoryId(Long categoryId, Pageable pageRequest) {
        return createPageResult(storeRepository.findPagingStoresByCategoryId(categoryId, pageRequest));
    }

    private PageResult<StoresReadResult> createPageResult(Page<Store> findStores) {
        PageResult<StoresReadResult> pageResult = new PageResult<>();
        pageResult.setList(createPagingStore(findStores));
        pageResult.setHasNext(findStores.hasNext());
        pageResult.setPageSize(findStores.getSize());
        pageResult.setPageNumber(findStores.getNumber());
        pageResult.setTotalCount(findStores.getTotalElements());
        return pageResult;
    }

    private List<StoresReadResult> createPagingStore(Page<Store> findStores) {
        return findStores.getContent().stream()
                .map(store -> storeConverter.convertStoresToStoresReadResults(
                        store,
                        getBestMenuResults(store.getId()),
                        reviewRepository.getReviewAverageByStore(store.getId()),
                        reviewRepository.getReviewTotalCountByStore(store.getId())
                ))
                .collect(Collectors.toList());
    }

    /**
     * 가게 단건 조회
     */
    public StoreReadResult getStore(Long storeId) {
        Store findStore = storeRepository.findStoreById(storeId)
                .orElseThrow(() -> new BusinessException(STORE_NOT_FOUND));
        getMenuResult(findStore);
        return storeConverter.convertToStoreInfoAtDetail(findStore, getBestMenuResults(storeId), getMenuResult(findStore));
    }

    private List<StoreMenuReadResult> getMenuResult(Store findStore) {
        return findStore.getMenus().stream()
                .map(menuConverter::convertMenuToMenuInfoAtStoreDetail)
                .collect(Collectors.toList());
    }

    private List<BestMenuResult> getBestMenuResults(Long storeId) {
        return menuRepository.findBestMenusByStore(storeId).stream()
                .map(menuConverter::convertBestMenuToBestMenuResult)
                .collect(Collectors.toList());
    }

    /**
     * 가게 이름으로 페이징 조회
     */
    public PageResult<StoresReadResult> getStoresByName(String storeName, Pageable pageRequest) {
        return createPageResult(storeRepository.findStoresByNameContaining(storeName, pageRequest));
    }

}
