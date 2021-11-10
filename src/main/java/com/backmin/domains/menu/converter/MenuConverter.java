package com.backmin.domains.menu.converter;

import com.backmin.domains.menu.domain.Menu;
import com.backmin.domains.menu.dto.response.StoreMenuReadResult;
import com.backmin.domains.menu.dto.response.BestMenuResult;
import com.backmin.domains.menu.dto.response.StoreMenuOptionReadResult;
import com.backmin.domains.menu.dto.response.BestMenuOptionResult;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter {

    public BestMenuResult convertBestMenuToBestMenuResult(Menu menu) {
        BestMenuResult bestMenuResult = new BestMenuResult();
        bestMenuResult.setId(menu.getId());
        bestMenuResult.setName(menu.getName());
        bestMenuResult.setImageUrl(null);
        bestMenuResult.setBest(menu.isBest());
        bestMenuResult.setSoldOut(menu.isSoldOut());
        bestMenuResult.setPopular(menu.isPopular());
        bestMenuResult.setPrice(menu.getPrice());
        bestMenuResult.setMenuOptions(menu.getMenuOptions().stream()
                .map(menuOption -> {
                    BestMenuOptionResult bestMenuOptionResult = new BestMenuOptionResult();
                    bestMenuOptionResult.setOptionId(menuOption.getId());
                    bestMenuOptionResult.setOptionName(menuOption.getName());
                    bestMenuOptionResult.setOptionPrice(menuOption.getPrice());
                    return bestMenuOptionResult;
                })
                .collect(Collectors.toList()));
        return bestMenuResult;
    }

    public StoreMenuReadResult convertMenuToMenuInfoAtStoreDetail(Menu menu) {
        StoreMenuReadResult storeMenuReadResult = new StoreMenuReadResult();
        storeMenuReadResult.setId(menu.getId());
        storeMenuReadResult.setName(menu.getName());
        storeMenuReadResult.setImageUrl(null);
        storeMenuReadResult.setBest(menu.isBest());
        storeMenuReadResult.setSoldOut(menu.isSoldOut());
        storeMenuReadResult.setPopular(menu.isPopular());
        storeMenuReadResult.setPrice(menu.getPrice());
        storeMenuReadResult.setMenuOptions(menu.getMenuOptions().stream()
                .map(menuOption -> {
                    StoreMenuOptionReadResult bestMenuOptionResult = new StoreMenuOptionReadResult();
                    bestMenuOptionResult.setOptionId(menuOption.getId());
                    bestMenuOptionResult.setOptionName(menuOption.getName());
                    bestMenuOptionResult.setOptionPrice(menuOption.getPrice());
                    return bestMenuOptionResult;
                })
                .collect(Collectors.toList()));
        return storeMenuReadResult;
    }

}
