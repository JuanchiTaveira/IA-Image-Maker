package com.example.iaimagemaker.models.unsplash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsplashUrls {
    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;
}
