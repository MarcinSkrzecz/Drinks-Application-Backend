package com.kodilla.drinks_backend.domain.drink;

import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.rating.Rating;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Drink")
public class Drink {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @NotNull
    @Column(name = "DRINK_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "DRINK_NAME")
    private String drinkName;

    @Column(name = "RECIPE")
    private String recipe;

    @Column(name = "INGREDIENTS")
    private String ingredients;

    @Column(name = "IS_SEND_TO_TRELLO")
    private boolean isSend = false;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "drink", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "drink", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DRINK_ID", referencedColumnName = "DRINK_ID")
    private Rating rating;

    public Drink(Long id) {
        this.id = id;
    }

    public Drink(String username, String drinkName, String recipe, String ingredients) {
        this.username = username;
        this.drinkName = drinkName;
        this.recipe = recipe;
        this.ingredients = ingredients;
    }

    public Drink(Long id, String username, String drinkName, String recipe, String ingredients) {
        this.id = id;
        this.username = username;
        this.drinkName = drinkName;
        this.recipe = recipe;
        this.ingredients = ingredients;
    }

    public Drink(Long id, String username, String drinkName, String recipe, String ingredients, LocalDate creationDate) {
        this.id = id;
        this.username = username;
        this.drinkName = drinkName;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Drink {" + "\n" +
                "- Drink id: " + id + ",\n" +
                "- Created by: " + username + ",\n" +
                "- Drink Name: " + drinkName + ",\n" +
                "- Recipe: " + recipe + ",\n" +
                "- Ingredients: " + ingredients + "\n" +
                '}';
    }

    public static class DrinkBuilder {
        private Long id;
        private String username;
        private String drinkName;
        private String recipe;
        private String ingredients;

        public DrinkBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public DrinkBuilder username(String username) {
            this.username = username;
            return this;
        }
        public DrinkBuilder drinkName(String drinkName) {
            this.drinkName = drinkName;
            return this;
        }
        public DrinkBuilder recipe(String recipe) {
            this.recipe = recipe;
            return this;
        }
        public DrinkBuilder ingredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Drink build() {
            return new Drink(id, username, drinkName, recipe, ingredients);
        }
    }
}