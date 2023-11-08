package com.insta.instagram.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.instagram.Model.Enum.AccountType;
import com.insta.instagram.Model.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String userName;
    public String getUserHandle;

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

    private String status;

    private int total=0;
    private String otp;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String isBlueTicked;
}
