package com.example.lab12security.Service;

import com.example.lab12security.Exception.ApiException;
import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import com.example.lab12security.Repository.AuthRepository;
import com.example.lab12security.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;


    public List getBlogs(){
        return blogRepository.findAll();
    }

    public List getMyBlogs(Integer auth){
        User user = authRepository.findUserById(auth);
        List<Blog> blogs = blogRepository.findAllByMyUser(user);
        return blogs;
    }


    public void addMyBlog(Blog blog,Integer auth){
    User user =authRepository.findUserById(auth);
    blog.setMyUser(user);
    blogRepository.save(blog);
    }

    public void updateMyBlog(Integer id,Blog blog,Integer auth){
        Blog blog1 = blogRepository.findBlogById(id);
        User myUser = authRepository.findUserById(auth);
        if (blog1 == null){
        throw new ApiException("Blog not found");
        }else if (!blog1.getMyUser().getId().equals(auth)){
            throw new ApiException("Sorry, You cannot update this blog");
        }
        blog.setId(id);
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }


    public void deleteMyBlog(Integer id, Integer auth){
        Blog blog = blogRepository.findBlogById(id);
        if (blog == null){
            throw new ApiException("Blog not found");
        }else if (!blog.getMyUser().getId().equals(auth)){
            throw new ApiException("Sorry, You cannot delete this blog");

        }

        blogRepository.delete(blog);
    }

   public Blog getBlogById(Integer id){
       Blog blog = blogRepository.findBlogById(id);
       if (blog == null){
           throw new ApiException("Blog not found");
       }
        return blog;
    }

    public Blog getBlogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null){
            throw new ApiException("Blog not found");
        }
        return blog;
    }




}
