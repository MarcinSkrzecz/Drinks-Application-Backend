package com.kodilla.drinks_backend.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CommentDto {
    private Long id;
    private Long drinkId;
    private String username;
    private String comment;
    private int rate;
    private int likes;
    private LocalDate creationDate;
}
