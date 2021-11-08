package com.backmin.domains.menu.dto.request;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuReadParam {

    @NotNull
    private Long id;

    @Min(value = 0L, message = "올바른 수량을 입력해주세요.")
    @Max(value = 1L, message = "올바른 수량을 입력해주세요.")
    private int quantity;

    private List<Long> menuOptionIds;

}
