package com.educavalieri.services;

import com.educavalieri.dtos.PostDto;
import com.educavalieri.entities.Post;
import com.educavalieri.entities.User;
import com.educavalieri.repositories.PostRepository;
import com.educavalieri.repositories.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.jboss.logging.annotations.Pos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostService {

    @Inject
    private PostRepository postRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public PostDto save(Long id, PostDto postDto){
        User user = userRepository.findById(id);
        if(user == null){
            throw new EntityNotFoundException("Entity not found");
        }
        Post post = new Post();
        post.setPost_text(postDto.getPost_text());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);
        return postDto;
    }

    @Transactional
    public List<PostDto> findAllPosts(Long id){
        User user = userRepository.findById(id);
        if(user == null){
            throw new EntityNotFoundException("Entity not found");
        }
        List<PostDto> posts = user.getPosts().stream().map(x -> new PostDto(x.getPost_text())).collect(Collectors.toList());
        return posts;
    }


}
