package com.udemy;

import io.micronaut.runtime.Micronaut;

public class Application {


    //Sometimes running gives an error make sure u delete everything in the database then try running again
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}