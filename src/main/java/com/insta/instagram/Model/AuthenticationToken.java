package com.insta.instagram.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;

    private Date tokenCreationDateTime;

    @OneToOne
    @JoinColumn(name = "fk_user_Id")
    User user;

    public AuthenticationToken(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = new Date();
    }

}
