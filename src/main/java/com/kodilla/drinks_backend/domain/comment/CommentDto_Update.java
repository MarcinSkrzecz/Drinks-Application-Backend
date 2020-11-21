package com.kodilla.drinks_backend.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDto_Update {
    private Long id;
    private Long drinkId;
    private String comment;
    private int rate;
}
