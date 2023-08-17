package com.udemy.service;

import java.util.ArrayList;
import java.util.List;

import com.udemy.exception.UserNotFoundExeption;
import com.udemy.model.User;
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
    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User getUserById(int id){
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
}
