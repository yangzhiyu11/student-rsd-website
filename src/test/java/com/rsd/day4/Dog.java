package com.rsd.day4;

public class Dog {

    private static Dog dog;

    private Dog() {

    }

    public static Dog getInstance() {
        if (dog == null) {
            dog = new Dog();
        }
        return new Dog();
    }

    public void run() {

    }
}
