package com.example.springblog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column (columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createdAt;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "blogUser_id", referencedColumnName = "id", nullable = false)
    private BlogUser blogUser;
}
