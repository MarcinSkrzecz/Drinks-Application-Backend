package com.kodilla.drinks_backend.domain.rating;

import com.kodilla.drinks_backend.domain.drink.Drink;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Rating")
public class Rating {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "RATE_ID")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "DRINK_ID")
    private Drink drink;

    @Column(name = "RATING")
    private String rating = "Not rated yet, be first one!";

    public Rating(Drink drink) {
        this.drink = drink;
    }

    public Rating(Long id, Drink drink, String rating) {
        this.id = id;
        this.drink = drink;
        this.rating = rating;
    }
}
