package com.kodilla.drinks_backend.domain.RP;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecipesDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("href")
    private String href;
    @JsonProperty("ingredients")
    private String ingredients;
    @JsonProperty("thumbnail")
    private String thumbnail;

    public RecipesDto() {}
}