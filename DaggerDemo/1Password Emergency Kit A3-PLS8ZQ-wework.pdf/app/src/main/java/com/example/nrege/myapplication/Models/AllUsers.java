package com.example.nrege.myapplication.Models;

import java.util.ArrayList;

/**
 * Created by nrege on 6/2/17.
 */

public class AllUsers {

//    private String name;
//    private String userName;
//    private String email;
//    private String phone;
    private ArrayList<User> allUsers;

    public AllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }
}
