package com.example.springblog.services;

import com.example.springblog.models.Post;
import com.example.springblog.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public Optional<Post> getById(Long id){
        return postRepo.findById(id);
    }

    public List<Post> getAll(){
        return postRepo.findAll();
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        return postRepo.save(post);
    }

    public void delete(Post post) { postRepo.delete(post);}

    public List<Post> search(String query) {
        return postRepo.findByTitleContainingOrBodyContaining(query, query);
    }


}
