package com.example.springstudy.user.model;

public enum UserStatus {

    None, Using, Stop;

    int value;

    UserStatus() {

    }

    public int getValue() {
        return this.value;
    }


}
