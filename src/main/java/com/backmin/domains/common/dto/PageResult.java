package com.backmin.domains.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResult<T> {

    private List<T> list;

    private long totalCount;

    private int pageNumber;

    private int pageSize;

    private boolean hasNext;

}
