package com.example.chessproject;

public class Base_of_position {

    String[] Base = new String[5];

    public Base_of_position(String s) {
        for (int i = 0; i < 5; ++i) {
            Base[i] = Base[i + 1];
        }
        Base[0] = s;
    }
}
