package com.insta.instagram.Repositroy;

import com.insta.instagram.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticatinRepo extends JpaRepository<AuthenticationToken,Long> {
}
