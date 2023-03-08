package com.tahafurkan.sandbox.blogapplication.service.impl;

import com.tahafurkan.sandbox.blogapplication.entity.Category;
import com.tahafurkan.sandbox.blogapplication.entity.Post;
import com.tahafurkan.sandbox.blogapplication.exception.ResourceNotFoundException;
import com.tahafurkan.sandbox.blogapplication.payload.PostDto;
import com.tahafurkan.sandbox.blogapplication.payload.PostResponse;
import com.tahafurkan.sandbox.blogapplication.repository.CategoryRepository;
import com.tahafurkan.sandbox.blogapplication.repository.PostRepository;
import com.tahafurkan.sandbox.blogapplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PostDto create(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        // convert DTO to Entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);

        Post newPost = postRepository.save(post);

        // convert Entity to DTO
        PostDto postResponse = mapToDTO(newPost);


        return postResponse;
    }

    @Override
    public PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postList = postRepository.findAll(pageable);

        List<Post> listOfPosts = postList.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postList.getNumber());
        postResponse.setPageSize(postList.getSize());
        postResponse.setTotalElements(postList.getTotalElements());
        postResponse.setTotalPages(postList.getTotalPages());
        postResponse.setLastPages(postList.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto update(long id, PostDto postDto) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        existingPost.setTitle(postDto.getTitle());
        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        existingPost.setCategory(category);

        Post updatePost = postRepository.save(existingPost);
        return mapToDTO(updatePost);

    }

    @Override
    public void delete(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.deleteById(id);

    }

    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> postList = postRepository.findByCategoryId(categoryId);

        return postList.stream().map((post) -> mapToDTO(post)).collect(Collectors.toList());
    }

    // convert Entity to DTO
    private PostDto mapToDTO(Post post) {

        PostDto postDto = modelMapper.map(post, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;

    }

    // convert DTO to Entity
    private Post mapToEntity(PostDto postDto) {

        Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}