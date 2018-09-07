package org.ktlnbstn.hangmanapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RestClientService {

    private final RestTemplate restTemplate;

    //define an argument constructor, pass in the RestTemplate object
    @Autowired
    public RestClientService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    // get words by difficulty
    public String findWordsByDifficulty(String id){

        // generate random int in order to grab a random word from api (within bounds to avoid timeouts)
        Random random = new Random();
        int numToGrab = random.nextInt(500);

        String url = "http://app.linkedin-reach.io/words?start={numToGrab}&count=1&difficulty={id}";

        Map<String, String> params = new HashMap<>();
        params.put("numToGrab", Integer.toString(numToGrab));
        params.put("id", id);

        return restTemplate.getForObject(url, String.class, params);
    }

}
