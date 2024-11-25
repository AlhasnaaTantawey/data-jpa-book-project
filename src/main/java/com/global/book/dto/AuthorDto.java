package com.global.book.dto;

import com.global.book.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto extends BaseDto<Long> {

    private String name;
    private String email;
//    private String ipAdress;
   // private String imagePath;
   // private List<Book> books =new ArrayList<>();

}
