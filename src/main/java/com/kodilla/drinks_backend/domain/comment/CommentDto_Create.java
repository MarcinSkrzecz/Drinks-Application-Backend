package com.kodilla.drinks_backend.domain.comment;

import lombok.Getter;

@Getter
public class CommentDto_Create {
    private Long drinkId;
    private String username;
    private String comment;
    private int rate;

    public CommentDto_Create(Long drinkId, String username, String comment, int rate) {
        this.drinkId = drinkId;
        this.username = username;
        this.comment = comment;
        this.rate = rate;
    }
}
