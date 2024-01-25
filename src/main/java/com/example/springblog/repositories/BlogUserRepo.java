package com.example.springblog.repositories;

import com.example.springblog.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepo extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findOneByEmail(String email);
}
