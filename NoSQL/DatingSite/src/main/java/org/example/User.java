package org.example;

public class User {
    String name;
    long registrationTime = System.currentTimeMillis()/1000;

    public String getName() {
        return name;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public User(int name){
        this.name = Integer.toString(name);
    }
}
