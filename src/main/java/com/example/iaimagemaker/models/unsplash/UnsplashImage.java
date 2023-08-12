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
    //private User user;
    @JsonProperty("current_user_collections")
    private List<String> currentUserCollections;
    private Urls urls;
    private UnsplashLinks links;
}

/*public class User {
    private String id;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String instagram_username;
    private String twitter_username;
    private String portfolio_url;
    private ProfileImage profile_image;
    private Links links;

    // Getters y setters
}*/

/*public class ProfileImage {
    private String small;
    private String medium;
    private String large;

    // Getters y setters
}*/





