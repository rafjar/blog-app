package com.rafjar.blogapp.post;

import com.rafjar.blogapp.account.Account;
import com.rafjar.blogapp.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;
    private AccountService accountService;

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost = postService.getById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            return "404";
        }
    }

    @GetMapping("/new")
    public String createNewPost(Model model) {
        Optional<Account> optionalAccount = accountService.findByEmail("user@user.com");

        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);

            return "post_new";
        } else {
            return "404";
        }
    }

    @PostMapping("/new")
    public String saveNewPost(@ModelAttribute Post post) {
        postService.save(post);

        return "redirect:/posts/" + post.getId();
    }
}
