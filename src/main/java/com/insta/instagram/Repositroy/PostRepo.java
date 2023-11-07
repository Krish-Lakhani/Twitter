package com.insta.instagram.Repositroy;

import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByPostOwnerUserEmail(String userEmail);
}
