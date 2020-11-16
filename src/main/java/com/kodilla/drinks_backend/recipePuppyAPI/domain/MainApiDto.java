package com.kodilla.drinks_backend.recipePuppyAPI.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainApiDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("version")
    private int version;
    @JsonProperty("href")
    private String href;
    @JsonProperty("results")
    private List<RecipesDto> recipes;
}