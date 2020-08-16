package com.sue;

import java.io.Serializable;

/**
 * @author sue
 * @date 2020/8/16 19:23
 */


public class Users {
    String name;
    String age;

    public Users(){

    }
    public Users(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
