package com.Twitter.Clone.Repositroy;

import com.Twitter.Clone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUserEmail(String email);

    boolean existsByuserEmail(String userEmail);

}
