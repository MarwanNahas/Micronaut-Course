package com.udemy.repository;

import java.util.List;

import com.udemy.model.User;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

//the repository annotation provides implementations of respective operations at compile time
//So basically we are just declaring this repository as an interface and internally CrudRepository will give us all the operations we need like save update updateAll saveAll findById existsById count delete deleteById deleteAll and so on
@Repository
///extends CrudRepository which takes 2 arguements first is the entity in which this repository will save and the second is the type of the primary key of that entity in this case it will be an integer since its an id
public interface UserRepository extends CrudRepository<User,Integer>{
    //We have to go to the Repository and make a Method for findAll so we declare that it takes List<User> we have to do it because the default of the method in CrudRepository doesnt understands that it holds List<User>
   //I want to try first without it 
    List<User> findAll();


}
