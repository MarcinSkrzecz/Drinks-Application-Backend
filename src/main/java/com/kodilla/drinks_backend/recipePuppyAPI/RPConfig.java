package com.kodilla.drinks_backend.recipePuppyAPI;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RPConfig {
    @Value("${recipePuppy.api.endpoint.prod}")
    private String recipePuppyApiEndpoint;
}
