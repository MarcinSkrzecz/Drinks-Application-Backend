package com.kodilla.drinks_backend.client;

import com.kodilla.drinks_backend.domain.RP.MainApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class RPClient {

    @Autowired
    private com.kodilla.drinks_backend.config.RPConfig RPConfig;

    private URI getRecipePuppyUrl() {
        return UriComponentsBuilder.fromHttpUrl(RPConfig.getRecipePuppyApiEndpoint())
                .build().encode().toUri();
    }

    private RestTemplate restTemplate() {
        RestTemplate myRest = new RestTemplate ();
        for (HttpMessageConverter<?> myConverter : myRest.getMessageConverters ()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType> ();
                myMediaTypes.addAll (myConverter.getSupportedMediaTypes ());
                myMediaTypes.add (MediaType.parseMediaType ("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes (myMediaTypes);
            }
        }
        return myRest;
    }

    public MainApiDto getMainCard() {
        URI url = getRecipePuppyUrl();
        try {
            MainApiDto boardResponse = restTemplate().getForObject(url,MainApiDto.class);
            return boardResponse;
        } catch (RestClientException e) {
            return new MainApiDto();
        }
    }
}