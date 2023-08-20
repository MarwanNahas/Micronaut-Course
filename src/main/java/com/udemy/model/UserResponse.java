package com.udemy.model;

import com.udemy.client.Preference;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//this will contain Both responses together and we will put it in the model folder 
@Getter
@Setter
@Builder
@Serdeable
public class UserResponse {

    private User user;
    private Preference preference;

    
}
