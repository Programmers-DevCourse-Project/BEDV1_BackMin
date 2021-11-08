package com.backmin.domains.menu.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOptionReadParam {

    @NotNull
    private Long id;

}
