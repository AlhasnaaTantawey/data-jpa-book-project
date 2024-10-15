package com.global.book.controller;

import com.global.book.dto.PostDto;
import com.global.book.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/posts")
public class PostsController {

    @Autowired
    private PostService postService;


  //  public PostDto getPost

}
