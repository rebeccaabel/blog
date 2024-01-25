package com.example.springblog.controllers;

import com.example.springblog.models.BlogUser;
import com.example.springblog.models.Post;
import com.example.springblog.services.BlogUserService;
import com.example.springblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private BlogUserService blogUserService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        //find post by id
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }else{
            return "404";

        }
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
       Optional<BlogUser> optionalBlogUser = blogUserService.findByEmail("rebecca@rebecca.se");
       if (optionalBlogUser.isPresent()) {
           Post post = new Post();
           post.setBlogUser(optionalBlogUser.get());
           model.addAttribute("post", post);
           return "post_create";
       }else {
           return "404";
       }
    }

    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
