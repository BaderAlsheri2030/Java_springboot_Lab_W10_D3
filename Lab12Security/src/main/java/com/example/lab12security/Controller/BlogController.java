package com.example.lab12security.Controller;


import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import com.example.lab12security.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all-blogs")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(blogService.getBlogs());
    }


    @GetMapping("/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }
    @GetMapping("/get-blog-by/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }
    @GetMapping("/get-blog-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String  title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog, @AuthenticationPrincipal User user){
        blogService.addMyBlog(blog,user.getId());
        return ResponseEntity.status(200).body("blog added");
    }
    @PutMapping("/update-my-blog/{id}")
    public ResponseEntity updateBlog(@PathVariable Integer id,@RequestBody @Valid Blog blog,@AuthenticationPrincipal User user){
        blogService.updateMyBlog(id,blog,user.getId());
        return ResponseEntity.status(200).body("blog updated");

    }

    @DeleteMapping("/delete-my-blog/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id,@AuthenticationPrincipal User user){
        blogService.deleteMyBlog(id,user.getId());
        return ResponseEntity.status(200).body("blog deleted");
    }

}
