package com.udemy.controller;

import java.util.List;

import com.udemy.model.User;
import com.udemy.model.UserResponse;
import com.udemy.service.UserService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;


//this is our endpoint of the HTTP request which is /api/users
@Controller("/api/users")
public class UserController {
   
    //lazem el controller ye2ra el service 3shan yedeeha el parameters ely heya 3ayzaha 3shan el 7aga tet save w keda w lazem yeb2a feh constructor leeha 
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    //this is a post method since we gonna create user from it .
    //it will return HttpResoponse of type user  
    //and it will take the paramater of user from the body (It will take request body which will be user class )
    //yeb2a elly ana fahamto laa 2a5osh 3ala /api/users ha5od el parameters men el body w ha7otaha fel user yeb2a ana keda gebt el id wel email wel 7agat beta3et el user w 7atetha f user b3d keda ha return response.created dah el mafrood yeraga3ly 201 lw eldenya tmm w haya5od el arguement beta3et el UserService.createUser w hadeeha el user ely 2a5adna el arguements beta3to men el body fa keda ana 7afazto fel list of users w hate3mely return lel user fel 2a5er fal mafrood el return el kebeera teraga3ly 201
    @Post
    public HttpResponse<User> createUser(@Body @Valid  User user){

        return HttpResponse.created(userService.createUser(user));
        
    }

    //dah hayraga3ly List of Users ya3ny hayraga3ly el users kollaha 
    @Get
    public HttpResponse<List<User>> getAllUsers(){
        return HttpResponse.ok(userService.getAllUsers());
    }


   //This is Before the preference part 
    // @Get("/{id}")
    // public HttpResponse<User> getUserById(@PathVariable int id){
    //     //here i always return HttpResponse.ok and that is not correct for Error Handling
    //     return HttpResponse.ok(userService.getUserById(id));
    // }


    //This is After the Preference Part
    //Before we jst returned User as a Response But now we will need to return The User Response + The Preference Response together So for this we will create a User repsonse this will contain Both responses together and we will put it in the model folder 
    //We will also go to userService.java and change the getUserByID method
    @Get("/{id}")
    public HttpResponse<UserResponse> getUserById(@PathVariable int id){
        //here i always return HttpResponse.ok and that is not correct for Error Handling
        return HttpResponse.ok(userService.getUserDetails(id));
    }

    @Put("/{id}")
    public HttpResponse<User> updateUser(@PathVariable int id,@Body User user){
        return HttpResponse.ok(userService.updateUser(id, user));
    }
    @Delete("/{id}")
    public HttpResponse<String> deleteUser (@PathVariable int id){
        return HttpResponse.ok(userService.deleteUser(id));
    }

}
