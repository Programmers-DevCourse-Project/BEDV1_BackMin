package com.backmin.domains.store.dto.response;

import com.backmin.domains.menu.dto.response.BestMenuResult;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoresReadResult {

    private Long storeId;

    private String storeName;

    private String storeIntro;

    private String storeImageUrl;

    private int minOrderPrice;

    private int deliveryTip;

    private int minDeliveryTime;

    private int maxDeliveryTime;

    private boolean isPackage;

    private double averageScore;

    int totalReviewCount;

    List<BestMenuResult> bestMenus = new ArrayList<>();

}
