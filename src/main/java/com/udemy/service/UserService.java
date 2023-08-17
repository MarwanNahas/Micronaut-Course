package com.udemy.service;

import java.util.ArrayList;
import java.util.List;

import com.udemy.exception.UserNotFoundExeption;
import com.udemy.model.User;

import jakarta.inject.Singleton;

//el service howa el makan ely feh el methods beta3ty 

@Singleton
public class UserService {
    // the place where we will save our users
    private List<User> users = new ArrayList<>();

    // we will add the user to the users list and then return the user
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        return users;
        
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
        return users.stream().filter(user -> user.getId()==id).findFirst().orElseThrow(() -> new UserNotFoundExeption());
    }

    public User updateUser(int id , User user){
        User prevUser = getUserById(id);
        prevUser.setName(user.getName());
        prevUser.setEmail(user.getEmail());
        prevUser.setMobileNumber(user.getMobileNumber());
        prevUser.setId(user.getId());
        return prevUser;   
    }

    public String deleteUser (int id ){
      users.remove(getUserById(id));
      return "Removed";
    }
}
