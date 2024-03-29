package com.example.springblog.services;

import com.example.springblog.models.BlogUser;
import com.example.springblog.repositories.BlogUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BlogUserRepo blogUserRepo;

    public BlogUser save(BlogUser blogUser) {
        blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));

        return blogUserRepo.save(blogUser);
    }

    public Optional<BlogUser> findByEmail(String email){
        return blogUserRepo.findOneByEmail(email);
    }

}
