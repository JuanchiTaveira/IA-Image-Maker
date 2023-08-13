package com.example.iaimagemaker.models.unsplash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsplashImage {
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    private int width;
    private int height;
    private String color;
    @JsonProperty("blur_hash")
    private String blurHash;
    private int likes;
    @JsonProperty("liked_by_user")
    private boolean likedByUser;
    private String description;
    @JsonProperty("current_user_collections")
    private List<String> currentUserCollections;
    private UnsplashUrls urls;
    private UnsplashLinks links;
}





