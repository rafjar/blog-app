package com.rafjar.blogapp.account;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rafjar.blogapp.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;


}
