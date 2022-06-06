package com.rsd.myenum;

public enum Animal {
    dog("公",3),cat("母",5),pig("未知",4);

    private String sex;

    private Integer age;

    Animal(String sex,Integer age) {
        this.sex = sex;
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }
}
