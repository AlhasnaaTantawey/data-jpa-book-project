package com.global.book.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
    private Long id;
    private Long userId;
    private String body;
   private String title;
}
