package com.example.finalassignment;

public class User {
    // set up the basic information about a book
    String name;
    String lastName;
    String pin;
    String id;

    User(String n, String ln, String p, String i){
        name = n;
        lastName = ln;
        pin = p;
        id = i;
    }

//    getters and setters

    public String toString(){
        return name;
    }
}
