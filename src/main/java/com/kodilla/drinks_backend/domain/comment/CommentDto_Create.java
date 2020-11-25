package com.kodilla.drinks_backend.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDto_Create {
    private Long drinkId;
    private String username;
    private String comment;
    private Integer rate;
}
