package com.jdkgroup.utils;

public class GlobalClass {
    public static GlobalClass instance;

    public static GlobalClass getInstance() {
        if (instance == null) {
            instance = new GlobalClass();
        }
        return instance;
    }



}
