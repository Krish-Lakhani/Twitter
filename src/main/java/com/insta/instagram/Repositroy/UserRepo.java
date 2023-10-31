package com.insta.instagram.Repositroy;

import com.insta.instagram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    User findByUserEmail(String email);

    boolean existsByuserEmail(String userEmail);
}
