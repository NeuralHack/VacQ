package com.example.vacq;

public class admin {
    public String email;
    public String pass;

    public admin() {
    }

    public admin(String email,String pass) {
        this.pass = pass;
        this.email= email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


