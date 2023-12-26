package com.example.lab12security.Repository;

import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);
    Blog findBlogByTitle(String title);

    List<Blog> findAllByMyUser(User user);


}
