package com.kodilla.drinks_backend.domain.ingredients;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Ingredients")
public class Ingredient {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "INGREDIENT_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "HOW_MANY_TIMES_USED")
    private int howManyTimesUsed;

    public Ingredient(Long id, String description, int howManyTimesUsed) {
        this.id = id;
        this.description = description;
        this.howManyTimesUsed = howManyTimesUsed;
    }

    public Ingredient(String description) {
        this.description = description;
    }
    public Ingredient(Long id, String description) {
        this.id = id;
        this.description = description;
    }
    public Ingredient(String description, int howManyTimesUsed) {
        this.description = description;
        this.howManyTimesUsed = howManyTimesUsed;
    }
}
