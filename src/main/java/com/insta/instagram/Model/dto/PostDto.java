package com.insta.instagram.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    public String title;
    public String description;
    public String url;
    public String time;
    private String userName;
}
