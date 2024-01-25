package com.example.springblog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String name;

    @OneToMany(mappedBy="blogUser")
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "bloguser_authority",
    joinColumns = {@JoinColumn(name = "bloguser_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority", referencedColumnName = "authName")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "BlogUser{" +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", authority='" + authorities + "'" +
                "}";
    }

}
