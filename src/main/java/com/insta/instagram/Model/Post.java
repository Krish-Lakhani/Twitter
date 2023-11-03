package com.insta.instagram.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer postId;
    public String title;
    public String description;
    public String url;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    public LocalDateTime dateTime;

    String formattedTime;
    Long time;

    @PrePersist
    private void prePersist() {
        this.time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Define your desired date/time format
        this.formattedTime = sdf.format(new Date(this.time));
    }

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "fk_post_userid")
    private User postOwner;
}
