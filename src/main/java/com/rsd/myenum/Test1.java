package com.rsd.myenum;

public class Test1 {
    public static void main(String[] args) {
        Zoom zoom = new Zoom();

        System.out.println(zoom.aa(Animal.cat));

        Animal[] values = Animal.values();
        for (Animal animal : values) {
            System.out.println(animal.name()+ "\t" + animal.getSex()+ "\t" + animal.getAge());
        }
    }
}
