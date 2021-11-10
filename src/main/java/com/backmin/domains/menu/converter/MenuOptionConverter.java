package com.backmin.domains.menu.converter;

import com.backmin.domains.menu.domain.MenuOption;
import com.backmin.domains.menu.dto.response.StoreMenuOptionReadResult;
import com.backmin.domains.menu.dto.response.BestMenuOptionResult;
import org.springframework.stereotype.Component;

@Component
public class MenuOptionConverter {

    public BestMenuOptionResult convertMenuOptionToMenuOptionInfoAtStoreList(MenuOption menuOption) {
        BestMenuOptionResult bestMenuOptionResult = new BestMenuOptionResult();
        bestMenuOptionResult.setOptionId(menuOption.getId());
        bestMenuOptionResult.setOptionName(menuOption.getName());
        bestMenuOptionResult.setOptionPrice(menuOption.getPrice());
        return bestMenuOptionResult;
    }

    public StoreMenuOptionReadResult convertMenuOptionToMenuOptionInfoAtStoreDetail(MenuOption menuOption) {
        StoreMenuOptionReadResult storeMenuOptionReadResult = new StoreMenuOptionReadResult();
        storeMenuOptionReadResult.setOptionId(menuOption.getId());
        storeMenuOptionReadResult.setOptionName(menuOption.getName());
        storeMenuOptionReadResult.setOptionPrice(menuOption.getPrice());
        return storeMenuOptionReadResult;
    }

}
