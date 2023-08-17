package com.udemy.service;

import java.util.ArrayList;
import java.util.List;

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
    return users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
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
