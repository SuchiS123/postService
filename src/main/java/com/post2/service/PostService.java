package com.post2.service;


import com.post2.Dto.PostDto;
import com.post2.Exception.ResourceNotFound;
import com.post2.config.RestTemplateConfig;

import com.post2.entity.Post;
import com.post2.repository.PostRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {


    private org.modelmapper.ModelMapper modelMapper;
    private PostRepository postRepository;
    private RestTemplate restTemplate;

    public PostService(org.modelmapper.ModelMapper modelMapper,PostRepository postRepository, RestTemplate restTemplate)
    {
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }


    public PostDto addPost (PostDto postDto, String postId) {

        Post post = mapToEntity(postDto);
        String id = UUID.randomUUID().toString();
        post.setId(id);
        Post savedPost = postRepository.save(post);
        PostDto postDto1 = mapToDto(savedPost);


        return postDto1;


    }


    public Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto,Post.class);
    }

    public PostDto mapToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

    public PostDto updatePostById(PostDto postDto, String id) {
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Not Post Found"));
        Post post = mapToEntity(postDto);
        Post save = postRepository.save(post);
        return mapToDto(save);
    }

    public List<PostDto> getAllPosts(PostDto postDto) {
        Post post = mapToEntity(postDto);
        List<Post> allPost= postRepository.findAll();
        List<PostDto> collect = allPost.stream().map(d -> mapToDto(d)).collect(Collectors.toList());

        return collect;

    }

    public PostDto getPostById(String id) {
        postRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Not Post Found"));
        Post byId = postRepository.getById(id);
        return mapToDto(byId);

    }

    public PostDto getPostByIdWithComments(String postId) {
        ArrayList forObject = restTemplate.getForObject("http://localhost:8082/api/comments/" + postId, ArrayList.class);

        Post postById = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound(
                "Post Nottttttt Found"));

        PostDto postDto = new PostDto();
        postDto.setId(postById.getId());
        postDto.setDescription(postById.getDescription());
        postDto.setTitle(postById.getTitle());
        postDto.setContent(postById.getContent());
        postDto.setComments(forObject);
        return postDto;

//        to return the postDto

    }
}
