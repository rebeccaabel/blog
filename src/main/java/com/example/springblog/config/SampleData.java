package com.example.springblog.config;

import com.example.springblog.models.Authority;
import com.example.springblog.models.Post;
import com.example.springblog.models.BlogUser;
import com.example.springblog.repositories.AuthorityRepo;
import com.example.springblog.services.PostService;
import com.example.springblog.services.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SampleData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private AuthorityRepo authorityRepo;

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

            Authority authority1 = new Authority();
            authority1.setAuthName("ROLE_USER");

            Authority authority2 = new Authority();
            authority2.setAuthName("ROLE_ADMIN");

            // Save Authorities
            authorityRepo.save(authority1);
            authorityRepo.save(authority2);

            blogUser1.getAuthorities().add(authority1);
            blogUser2.getAuthorities().add(authority1); // Assuming both users have ROLE_USER
            blogUser2.getAuthorities().add(authority2); // Admin has ROLE_USER and ROLE_ADMIN

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

