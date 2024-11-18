package com.global.book.controller;

import com.global.book.dto.PostDto;
import com.global.book.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

   @GetMapping("/{id}")
   public ResponseEntity<?> getPost(@PathVariable Long id){
       return   ResponseEntity.ok(postService.getPost(id));
   }

    @GetMapping
    public ResponseEntity<?> getAllPost(){
        return   ResponseEntity.ok(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto){
        return   ResponseEntity.ok(postService.addPost(postDto));
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto){
        postService.updatePost(postDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok(null);
    }

}
