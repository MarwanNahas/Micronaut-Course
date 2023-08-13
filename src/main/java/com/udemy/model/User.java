package com.udemy.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//dool men lombok
@Getter
@Setter
// Generates an all-args constructor. An all-args constructor requires one
// argument for every field in the class.
// it means i created a constructor that has all the arguments of the class in
// this case the id name mobilenumber and email
@AllArgsConstructor
// Generates a no-args constructor. Will generate an error message if such a
// constructor cannot be written due to the existence of final fields.
// it means as if i created a constructor that takes no arguements a blank
// constructor
@NoArgsConstructor

// If a class is annotated, then a package-private constructor is generated with
// all fields as arguments (as if @AllArgsConstructor(access =
// AccessLevel.PACKAGE) is present on the class),
// @Builder lets you automatically produce the code required to have your class
// be instantiable with code such as:Person.builder()
// .name("Adam Savage")
// .city("San Francisco")
// .job("Mythbusters")
// .job("Unchained Reaction")
// .build();
@Builder


public class User {

    private int id;
    private String name;
    private String mobileNumber;
    private String email;

}
