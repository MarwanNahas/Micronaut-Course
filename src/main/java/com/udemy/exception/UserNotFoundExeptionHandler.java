package com.udemy.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

//it will implement exeptionhandler from micronaut.http.server.exepetions not any other library the ExeptionHandler takes 2 arguments first the is T which is : The Throwable so in our case its UserNotFoundExeption and the second is R which is :The Return Type of your handling (if this was some other web application we could have returned a rendered web page for this error )
@Singleton
//make this handler available only if UserNotFoundExeption,HttpResponse are ready (Make sure this bean UserNotFoundExeptionHandler is not hotwired unless thos 2 beans (classes) are available )
@Requires(classes = {UserNotFoundExeption.class,ExceptionHandler.class})
public class UserNotFoundExeptionHandler implements ExceptionHandler<UserNotFoundExeption,HttpResponse>{
    // this method is from the implementation u jst CNRL+space and add it 
    @Override
    public HttpResponse handle(HttpRequest request, UserNotFoundExeption exception) {
        // TODO Auto-generated method stub
        return HttpResponse.notFound("User Not Found");
    }
}
