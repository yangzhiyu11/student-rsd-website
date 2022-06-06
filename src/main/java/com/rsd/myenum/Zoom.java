package com.rsd.myenum;

public class Zoom {
    public String aa(Animal animal) {
        String str = "";
        if (animal.equals(Animal.cat)) {
            str = "小猫咪";
        }
        if (animal.equals(Animal.dog)) {
            str = "小狗狗";
        }
        if (animal.equals(Animal.pig)) {
            str = "小猪猪";
        }
        return str;
    }
}
