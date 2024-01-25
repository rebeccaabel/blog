package com.example.springblog.config;

import com.example.springblog.models.Post;
import com.example.springblog.models.BlogUser;
import com.example.springblog.services.PostService;
import com.example.springblog.services.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private BlogUserService blogUserService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {

            BlogUser blogUser1 = new BlogUser();
            BlogUser blogUser2 = new BlogUser();

            blogUser1.setName("Rebecca");
            blogUser1.setEmail("rebecca@rebecca.se");
            blogUser1.setPassword("rebecca");

            blogUser2.setName("admin");
            blogUser2.setEmail("admin@admin");
            blogUser2.setPassword("admin");

            blogUserService.save(blogUser1);
            blogUserService.save(blogUser2);


            Post post1 = new Post();
            post1.setTitle("Title of post1");
            post1.setBody("Body of post1");
            post1.setBlogUser(blogUser1);

            postService.save(post1);
        }
    }

}

