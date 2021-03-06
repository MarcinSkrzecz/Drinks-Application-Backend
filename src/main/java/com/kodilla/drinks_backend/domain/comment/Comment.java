package com.kodilla.drinks_backend.domain.comment;

import com.kodilla.drinks_backend.domain.drink.Drink;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Comment")
public class Comment {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DRINK_ID", referencedColumnName = "DRINK_ID")
    private Drink drink;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "RATE")
    private int rate;

    @Column(name = "LIKES")
    private int likes = 0;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    public Comment(Long id) {
        this.id = id;
    }

    public Comment(String username, String comment, int rate) {
        this.username = username;
        this.comment = comment;
        this.rate = rate;
    }

    public Comment(Drink drink, String username, String comment, int rate) {
        this.drink = drink;
        this.username = username;
        this.comment = comment;
        this.rate = rate;
    }

    public Comment(Long id, Drink drink, String comment, int rate) {
        this.id = id;
        this.drink = drink;
        this.comment = comment;
        this.rate = rate;
    }

    public Comment(long id, Drink drink, String username, String comment, int rate) {
        this.id = id;
        this.drink = drink;
        this.username = username;
        this.comment = comment;
        this.rate = rate;
    }

    public Comment(long id, Drink drink, String username, String comment, int rate, LocalDate creationDate) {
        this.id = id;
        this.drink = drink;
        this.username = username;
        this.comment = comment;
        this.rate = rate;
        this.creationDate = creationDate;
    }

    public Comment(long id, Drink drink, String username, String comment, int rate, int likes, LocalDate creationDate) {
        this.id = id;
        this.drink = drink;
        this.username = username;
        this.comment = comment;
        this.rate = rate;
        this.likes = likes;
        this.creationDate = creationDate;
    }
}
