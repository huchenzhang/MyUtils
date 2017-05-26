package com.example.huchenzhang.myutils.myDataBinding;

/**
 * javabean
 * Created by hu on 2017/5/23.
 */

public class User {
    private String name;
    private String age;

    public User(String name, String age){
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
}
