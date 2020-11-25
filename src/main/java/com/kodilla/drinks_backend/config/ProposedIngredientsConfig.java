package com.kodilla.drinks_backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProposedIngredientsConfig {
    @Value("${proposedIngredientsStatusChange}")
    private Integer countToChangeStatus;
}
