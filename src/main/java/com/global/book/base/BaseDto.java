package com.global.book.base;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public abstract class BaseDto<ID> {

    private ID id ;
    private  String statusCode;
    private String createdBy;
    private LocalDateTime createdData;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedData;

}
