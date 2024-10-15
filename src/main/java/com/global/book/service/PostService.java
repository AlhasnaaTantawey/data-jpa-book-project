package com.global.book.service;

import com.global.book.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {


private static final String BASE_POST_URL="https://jsonplaceholder.typicode.com/posts";

    public PostDto getPost(Long id){
        RestTemplate restTemplate= new RestTemplate();
      ResponseEntity<PostDto> response= restTemplate.getForEntity(BASE_POST_URL +"/" + id,PostDto.class);
    return   response.getBody();
    }


}
