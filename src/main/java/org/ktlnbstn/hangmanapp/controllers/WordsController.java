package org.ktlnbstn.hangmanapp.controllers;

import org.ktlnbstn.hangmanapp.model.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordsController{

    private final RestClientService service;

    @Autowired
    public WordsController(RestClientService service){
        this.service = service;
    }

    public String getByDifficulty(String difficulty){

        return service.findWordsByDifficulty(difficulty);
    }

}

