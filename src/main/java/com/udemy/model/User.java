package com.udemy.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
// both Serdeable and Introspected are used so that object can be transfered to
// JSON format
// Introspected is used for validation also , so that you can put Bean
// Validation Annotations like @NotBlank and @Min(5) and @Max(10)
@Introspected
@Serdeable

//NOW WE CHANGED TO DB MODE so we will make USer as an Entity and create a Table for it
@Entity
@Table(name = "users")
public class User {

    ////USE THE LIBRARY OF jakrata.persistance IMPORTAAAAAAAAAAAAAAAANT
    //Specifies the primary key of an entity
    @Id 
    //telling our DB to store primary key in the identity column which is a default column in SQL for default auto incremented primary key generation.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column
    private int id;
   @NotBlank
    @Column
    private String name;
    @NotBlank
     @Column
    private String mobileNumber;
    @NotBlank
     @Column
    private String email;

}
