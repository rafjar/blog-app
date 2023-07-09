package com.rafjar.blogapp.config;

import com.rafjar.blogapp.account.*;
import com.rafjar.blogapp.post.Post;
import com.rafjar.blogapp.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {
            Authority userAuthority = new Authority();
            Authority adminAuthority = new Authority();
            userAuthority.setName("ROLE_USER");
            adminAuthority.setName("ROLE_ADMIN");
            authorityRepository.save(userAuthority);
            authorityRepository.save(adminAuthority);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("admin");
            account1.setLastName("admin");
            account1.setEmail("admin@admin.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("user");
            account2.setLastName("user");
            account2.setEmail("user@user.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);
            accountService.save(account1);
            accountService.save(account2);


            Post post1 = new Post();
            post1.setTitle("Title of post1");
            post1.setBody("This is the body of post1");
            post1.setAccount(account1);

            Post post2 = new Post();
            post2.setTitle("Title of post2");
            post2.setBody("This is the body of post2");
            post2.setAccount(account2);
            postService.save(post1);
            postService.save(post2);
        }
    }
}
