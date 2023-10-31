package com.insta.instagram.Model;

import com.insta.instagram.Model.Enum.AccountType;
import com.insta.instagram.Model.Enum.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String userName;
    private String userHandle;
    private String userBio;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Column(unique = true)
    private String userEmail;

    @NotBlank
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;


}
