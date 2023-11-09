package com.Twitter.Clone.Repositroy;

import com.Twitter.Clone.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByPostOwnerUserEmail(String email);
}
