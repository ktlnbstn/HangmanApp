package org.ktlnbstn.hangmanapp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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

        String url = "http://app.linkedin-reach.io/words?start=&difficulty=" + id;

        String listOWords = restTemplate.getForObject(url, String.class, id);
        List<String> test = Arrays.asList(listOWords.split("\n"));
        int numOfWords = test.size();

        // generate random int in order to grab a random word from api (within bounds to avoid timeouts)
        Random random = new Random();
        int numToGrab = random.nextInt(numOfWords);

        return test.get(numToGrab);
    }

}
