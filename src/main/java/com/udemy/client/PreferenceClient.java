package com.udemy.client;

import java.util.Optional;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;


//inside the annotation we have to give the URL of our preference-service
@Client("http://localhost:8080/api/preferences")
public interface PreferenceClient {
    
    //We will create a file that is the same file of Preference Model in the preference-service project BUT NOT as an entity we only want it as HTTP Response from another service
    //Once we define this client , we can inject it wherever we want so basically we will  need to use this preference client in our userService.java
    @Get("/{userId}")
    Optional<Preference> getUserPreference(Integer userId);

}
