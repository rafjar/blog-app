package com.rafjar.blogapp.home;

import com.rafjar.blogapp.post.Post;
import com.rafjar.blogapp.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private PostService postService;

    @GetMapping
    public String home(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);

        return "home";
    }
}
