package com.backmin.domains.store.converter;

import com.backmin.domains.menu.dto.MenuInfoAtStoreList;
import com.backmin.domains.store.domain.Store;
import com.backmin.domains.store.dto.StoreInfoAtList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {

    public StoreInfoAtList convertToStoreInfoAtList(Store store, List<MenuInfoAtStoreList> menuInfoAtStoreLists, double averageReviewScore, int totalReviewCount) {
        return StoreInfoAtList.of(
                store.getId(),
                store.getName(),
                store.getStoreIntro(),
                store.getMinOrderPrice(),
                store.getMinDeliveryTime(),
                store.getMaxDeliveryTime(),
                store.getDeliveryTip(),
                store.isPackage(),
                averageReviewScore, // TODO : 평균 평점 넣기
                totalReviewCount, // TODO : 총 리뷰 수 넣기
                menuInfoAtStoreLists
        );
    }

}
