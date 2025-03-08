package com.post2.PostController;


import com.post2.Dto.PostDto;
import com.post2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto,String postId) {

        PostDto postDto1 = postService.addPost(postDto,postId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/post")
    public ResponseEntity<?> deletePost(@RequestParam String postId)
    {
        postService.deletePost(postId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/updateDetails/{id}")
    public ResponseEntity<?> update(@RequestBody PostDto postDto,@PathVariable String id)
    {
        PostDto postDto1 = postService.updatePostById(postDto,id);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    @GetMapping("/getAllDetails")
    public ResponseEntity<?> getAllPosts(PostDto postDto)
    {
        List<PostDto> allPosts = postService.getAllPosts(postDto);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);

    }

    @GetMapping("/getDetails/{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id)
    {
        PostDto postDto1 = postService.getPostById(id);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }

    @GetMapping("/getDetailsWithComment/{postId}")
    public ResponseEntity<?> getPostByIdWithComments(@PathVariable String postId)
    {
        PostDto postDto1 = postService.getPostByIdWithComments(postId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }



}
