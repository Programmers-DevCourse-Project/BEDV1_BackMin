package com.backmin.domains.store.dto.response;

import com.backmin.domains.menu.dto.response.BestMenuResult;
import com.backmin.domains.menu.dto.response.StoreMenuReadResult;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreReadResult {

    private Long storeId;

    private String storeName;

    private String phoneNumber;

    private String storeIntro;

    private int minOrderPrice;

    private int minDeliveryTime;

    private int maxDeliveryTime;

    private int deliveryTip;

    private boolean isPackage;

    private List<BestMenuResult> bestMenus;

    private List<StoreMenuReadResult> menus;

}
