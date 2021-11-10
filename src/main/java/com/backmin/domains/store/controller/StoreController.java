package com.backmin.domains.store.controller;

import static com.backmin.domains.store.controller.StoreController.*;
import static org.springframework.http.MediaType.*;

import com.backmin.domains.common.dto.ApiResult;
import com.backmin.domains.common.dto.PageResult;
import com.backmin.domains.store.dto.response.StoreReadResult;
import com.backmin.domains.store.dto.response.StoresReadResult;
import com.backmin.domains.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping(STORES)
public class StoreController {

    protected static final String STORES = "/api/v1/stores";
    protected static final String STORE_ID = "/{storeId}";
    protected static final String CATEGORIES = "/categories/{categoryId}";

    private final StoreService storeService;

    @GetMapping(value = CATEGORIES, produces = APPLICATION_JSON_VALUE)
    public ApiResult<PageResult<StoresReadResult>> list(@PathVariable("categoryId") Long categoryId, Pageable pageRequest) {
        return ApiResult.ok(storeService.getStoresByCategoryId(categoryId, pageRequest));
    }

    @GetMapping(value = STORE_ID, produces = APPLICATION_JSON_VALUE)
    public ApiResult<StoreReadResult> detail(@PathVariable("storeId") Long storeId) {
        return ApiResult.ok(storeService.getStore(storeId));
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ApiResult<PageResult<StoresReadResult>> list(@RequestParam("keyword") String storeName, Pageable pageRequest) {
        return ApiResult.ok(storeService.getStoresByName(storeName, pageRequest));
    }

}
