package com.telran.java23.annotation.user;

import com.telran.java23.annotation.engine.Main;
import com.telran.java23.annotation.engine.annotation.ConsoleInput;
import com.telran.java23.annotation.engine.annotation.Service;
import com.telran.java23.annotation.engine.annotation.StartMethod;

@Service
public class UserService /*implements MyService*/ {

//    @StartMethod
    public void run(@ConsoleInput(displayMessage = "Enter user data:") String input) {
        System.out.println("User service -> run() -> input");
        System.out.println(input);
    }

}