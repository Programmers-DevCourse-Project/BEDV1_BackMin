package com.backmin.domains.menu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreMenuOptionReadResult {

    private Long optionId;

    private String optionName;

    private int optionPrice;

}
