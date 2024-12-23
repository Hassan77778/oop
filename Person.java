package com.example.project123;

import java.util.Date;
import java.util.Scanner;

public abstract class Person {
    protected String username;
    protected String password;
    protected String date_of_birth;
    public Person(){}
    public Person(String user,String pass,String date) {
        this.username=user;
        this.password=pass;
        this.date_of_birth=date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String  date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    abstract public void SignUp();
    abstract public Person Login(String user,String pass);
    abstract public void Modify_data();
    abstract public void View_data();

}
