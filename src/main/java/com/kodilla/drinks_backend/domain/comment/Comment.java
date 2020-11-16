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

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

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
}
