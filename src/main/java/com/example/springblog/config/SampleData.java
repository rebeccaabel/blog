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

            Authority user = new Authority();
            user.setAuthName("ROLE_USER");
            authorityRepo.save(user);

            Authority admin = new Authority();
            admin.setAuthName("ROLE_ADMIN");
            authorityRepo.save(admin);

            BlogUser blogUser1 = new BlogUser();
            BlogUser blogUser2 = new BlogUser();

            blogUser1.setName("Rebecca");
            blogUser1.setEmail("rebecca@rebecca.se");
            blogUser1.setPassword("rebecca");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepo.findByAuthName("ROLE_USER").ifPresent(authorities1::add);
            blogUser1.setAuthorities(authorities1);

            blogUser2.setName("admin");
            blogUser2.setEmail("admin@admin");
            blogUser2.setPassword("admin");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepo.findByAuthName("ROLE_USER").ifPresent(authorities2::add);
            authorityRepo.findByAuthName("ROLE_ADMIN").ifPresent(authorities2::add);
            blogUser2.setAuthorities(authorities2);


            blogUserService.save(blogUser1);
            blogUserService.save(blogUser2);

            Post post1 = new Post();
            post1.setTitle("Title of post1");
            post1.setBody("Body of post1");
            post1.setBlogUser(blogUser1);


            Post post2 = new Post();
            post2.setTitle("Title of post2");
            post2.setBody("Body of post2");
            post2.setBlogUser(blogUser2);

            postService.save(post1);
            postService.save(post2);

        }
    }

}

