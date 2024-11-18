package com.global.book.service;

import com.global.book.dto.PostDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//consume REST API by RestTemplate in controller
@Service
public class PostService {


private static  String BASE_POST_URL="https://jsonplaceholder.typicode.com/posts";

    RestTemplate restTemplate= new RestTemplate();

    public PostDto getPost(Long id){
      ResponseEntity<PostDto> response= restTemplate.getForEntity(BASE_POST_URL +"/" + id,PostDto.class);
    return   response.getBody();
    }

    public List<PostDto> getAllPost(){
        ResponseEntity<List> response= restTemplate.getForEntity(BASE_POST_URL ,List.class);
        return   response.getBody();
    }

    public PostDto addPost( PostDto postDto){
        HttpHeaders headers=new HttpHeaders();
        headers.add("accept","application/json");
        headers.add("accept-language","en");
        HttpEntity<PostDto> request=new HttpEntity<>(postDto,headers);
        ResponseEntity<PostDto> response= restTemplate.postForEntity(BASE_POST_URL,request,PostDto.class);
        return   response.getBody();
    }

    public void updatePost( PostDto postDto){
       HttpEntity<PostDto> request=new HttpEntity<>(postDto);
         restTemplate.put(BASE_POST_URL,request);
    }

    public void deletePost( Long id){
        restTemplate.delete(BASE_POST_URL);
    }

}
