package com.telran.java23.annotation.user;

import com.telran.java23.annotation.engine.annotation.ConsoleInput;
import com.telran.java23.annotation.engine.annotation.RequestBody;
import com.telran.java23.annotation.engine.annotation.Service;
import com.telran.java23.annotation.engine.annotation.StartMethod;

@Service
public class AdminService /*implements MyService*/ {

//    void a() {}
//    String b() { return ""; }

    @StartMethod
    public void run(@RequestBody Admin admin) {
        System.out.println("Admin service -> run() -> input");
        System.out.println(admin);
    }
}
