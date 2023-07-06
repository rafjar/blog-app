package com.rafjar.blogapp.config;

import com.rafjar.blogapp.account.Account;
import com.rafjar.blogapp.account.AccountRepository;
import com.rafjar.blogapp.account.AccountService;
import com.rafjar.blogapp.post.Post;
import com.rafjar.blogapp.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if (posts.size() == 0) {
            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("admin");
            account1.setLastName("admin");
            account1.setEmail("admin@admin.com");
            account1.setPassword("password");

            account2.setFirstName("user");
            account2.setLastName("user");
            account2.setEmail("user@user.com");
            account2.setPassword("password");
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
