package com.backmin.domains.store.converter;

import com.backmin.domains.common.dto.PageResult;
import com.backmin.domains.menu.dto.response.BestMenuResult;
import com.backmin.domains.menu.dto.response.StoreMenuReadResult;
import com.backmin.domains.store.domain.Store;
import com.backmin.domains.store.dto.response.StoreReadResult;
import com.backmin.domains.store.dto.response.StoresReadResult;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {

    public StoresReadResult convertStoresToStoresReadResults(Store store, List<BestMenuResult> bestMenuResults, double averageReviewScore,
            int totalReviewCount) {
        StoresReadResult storesReadResult = new StoresReadResult();
        storesReadResult.setStoreId(store.getId());
        storesReadResult.setStoreName(store.getName());
        storesReadResult.setStoreImageUrl(null);
        storesReadResult.setStoreIntro(store.getStoreIntro());
        storesReadResult.setMinOrderPrice(store.getMinOrderPrice());
        storesReadResult.setMinDeliveryTime(store.getMinDeliveryTime());
        storesReadResult.setMaxDeliveryTime(store.getMaxDeliveryTime());
        storesReadResult.setPackage(store.isPackage());
        storesReadResult.setDeliveryTip(store.getDeliveryTip());
        storesReadResult.setAverageScore(averageReviewScore);
        storesReadResult.setTotalReviewCount(totalReviewCount);
        storesReadResult.setBestMenus(bestMenuResults);
        return storesReadResult;
    }

    public StoreReadResult convertToStoreInfoAtDetail(Store store, List<BestMenuResult> bestMenuResults, List<StoreMenuReadResult> menuResult) {
        StoreReadResult storeReadResult = new StoreReadResult();
        storeReadResult.setStoreId(store.getId());
        storeReadResult.setStoreName(store.getName());
        storeReadResult.setPhoneNumber(store.getPhoneNumber());
        storeReadResult.setStoreIntro(store.getStoreIntro());
        storeReadResult.setMinOrderPrice(store.getMinOrderPrice());
        storeReadResult.setMinDeliveryTime(store.getMinDeliveryTime());
        storeReadResult.setMaxDeliveryTime(store.getMaxDeliveryTime());
        storeReadResult.setDeliveryTip(store.getDeliveryTip());
        storeReadResult.setPackage(store.isPackage());
        storeReadResult.setBestMenus(bestMenuResults);
        storeReadResult.setMenus(menuResult);
        return storeReadResult;
    }

    public PageResult<StoresReadResult> convertToPageResult(Page<Store> findStores, List<StoresReadResult> pagingStore) {
        PageResult<StoresReadResult> pageResult = new PageResult<>();
        pageResult.setList(pagingStore);
        pageResult.setHasNext(findStores.hasNext());
        pageResult.setPageSize(findStores.getSize());
        pageResult.setPageNumber(findStores.getNumber());
        pageResult.setTotalCount(findStores.getTotalElements());
        return pageResult;
    }

}
