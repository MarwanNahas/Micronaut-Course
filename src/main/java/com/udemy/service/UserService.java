package com.udemy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.udemy.client.Preference;
import com.udemy.client.PreferenceClient;
import com.udemy.exception.UserNotFoundExeption;
import com.udemy.model.User;
import com.udemy.model.UserResponse;
import com.udemy.repository.UserRepository;

import jakarta.inject.Singleton;

//el service howa el makan ely feh el methods beta3ty 

@Singleton
public class UserService {
    // the place where we will save our users
    ///Now we gonna Use Database instead of that list we Gonna use something called Microsoft Data which is a database access toolkit that uses Ahead Of Time (AoT) compilation to pre compute queries for repository interfaces it provides a general API for translating a compile time query model into a query at compilation time & provides runtime support for GPA SQL
    //private List<User> users = new ArrayList<>();
   
    //here is what we need to link to db we will have repository in here and put it in the constructor
    private final UserRepository userRepository;


    //after the part of preference we need to add the preferenceClient in here
    private final PreferenceClient preferenceClient;

    

  

    public UserService(UserRepository userRepository, PreferenceClient preferenceClient) {
        this.userRepository = userRepository;
        this.preferenceClient = preferenceClient;
    }

    // we will add the user to the users list and then return the user
    public User createUser(User user) {
        // users.add(user);
        // return user;

        // after DB
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
       // return users;
        //We have to go to the Repository and make a Method for findAll so we declare that it takes List<User> we have to do it because the default of the method in CrudRepository doesnt understands that it holds List<User>
       return userRepository.findAll();
    }


    //THIS WAS public before the preference part after the preference part we made this private and created another method to satisfy the UserResponse
    private User getUserById(int id){
    //      for (User user : users) {
    //     if (user.getId()==id) {
    //         return user;
    //     }
    // }
    // return null;
    ////Here if user wasnt found we jst simply returned null but that is not correct error handling
    ///The correct error handling is when user is not found we should return 404 error which is HTTPNotFound micronaut provides us a way to globally handle these exceptions so we will create a new package called exception and the exeption handler for it so instead of returning orELse(null) we will return orElseThrow(the exception we created) This is called Error Handling At the Global Level because since we gonna need The UserNotFoundException in many many casses so we reuse the code fast
    //return users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
       // return users.stream().filter(user -> user.getId()==id).findFirst().orElseThrow(() -> new UserNotFoundExeption());
        
       //Now After DB
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundExeption());
    }

    //This method was created to Satisfy the UserResponse after the part of preference
    public UserResponse getUserDetails(int id){
        //first we will get user details from user-db thats why i didnt remove the above method i just made it private
        User user = getUserById(id);
        //There is a huge difference between Controller and Client The Controller uses the method defined in UserService.java while UserService.java uses the method written in Client therefore we had to reference preferenceClient in the constructor while we didnt need to do that for the UserController
        //We are using Optional because if the preferences are not found for that userid in that case we want this to be optional preference because it can be the case that user is newly created and the preference does not exist so in that case we will get optional.empty from our preference-service
        Optional<Preference> optionalPreference = preferenceClient.getUserPreference(id);

        //Now we will get the preference of that user
        //isPresent is a method given by the Optional object : if it is present then we will say optionalpreference.get otherwise we want to return null we can type it like below or like below below
       // Preference preference = optionalPreference.isPresent() ? optionalPreference.get() : null;
        Preference preference = optionalPreference.orElse(null);
        //builder is jst an easy way to put values like a constructor
        return UserResponse.builder()
                            .user(user)
                            .preference(preference)
                            .build();


    }


    public User updateUser(int id , User user){
        User prevUser = getUserById(id);
        prevUser.setName(user.getName());
        prevUser.setEmail(user.getEmail());
        prevUser.setMobileNumber(user.getMobileNumber());
        prevUser.setId(user.getId());
       // return prevUser;   
        //After DB
        return userRepository.update(prevUser);
    }

    public String deleteUser (int id ){
     // users.remove(getUserById(id));
     //After the DB
     userRepository.delete(getUserById(id)); 
     return "Removed";
    }

    //count Users will be a ScheduledTask that will trigger every 5 second and give us the count of Users
    //we will create a folder named tasks that will hold the tasks that will be scheduled 
    public long getUserCount (){
        return userRepository.count();
    }
}
