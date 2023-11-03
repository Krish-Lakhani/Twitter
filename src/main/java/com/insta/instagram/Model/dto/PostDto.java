package com.insta.instagram.Model.dto;

import lombok.Data;

@Data
public class PostDto {
    public String title;
    public String description;
    public String url;
    public String formattedTime;
    private String userName;
}
