package com.example.springblog.services;

import com.example.springblog.models.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class BlogUserDetails implements UserDetailsService {

    @Autowired
    private BlogUserService blogUserService;
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<BlogUser> optionalBlogUser = blogUserService.findByEmail(email);
        if(!optionalBlogUser.isPresent()) {
            throw new UsernameNotFoundException("USER DOES NOT EXIST");
        }

        BlogUser blogUser = optionalBlogUser.get();

        List<GrantedAuthority> grantedAuthorities = blogUser
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(blogUser.getEmail(), blogUser.getPassword(), grantedAuthorities);
    }

}
