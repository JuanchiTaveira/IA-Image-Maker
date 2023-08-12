package com.example.iaimagemaker.models.unsplash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnsplashApiResponse {
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<UnsplashImage> results;
}
