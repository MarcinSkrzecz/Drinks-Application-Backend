package com.kodilla.drinks_backend.recipePuppyAPI.proposedIngredients;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "IngredientsToPropose")
public class ProposedIngredients {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "INGREDIENT_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VOTES")
    private int votes = 0;

    @Column(name = "STATUS")
    private String status = "Collecting votes";
    //Collecting votes -> Votes collected - WIP -> Completed

    @Column(name = "IS_ADDED_TO_RECIPES")
    private boolean isAddedToRecipes = false;

    public ProposedIngredients(Long id, String description, int votes, String status) {
        this.id = id;
        this.description = description;
        this.votes = votes;
        this.status = status;;
    }

    public ProposedIngredients(String description) {
        this.description = description;
    }
}
