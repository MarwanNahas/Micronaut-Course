package com.udemy.client;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
@Serdeable
// Here we Do not need it as an entity its jst an HTTP Response from another service 
public class Preference {


    private int id;
    private int userId;
    private String locale;
    private String diet;
    private boolean notifyOff;

}

