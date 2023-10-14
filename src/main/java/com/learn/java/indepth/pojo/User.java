package com.learn.java.indepth.pojo;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
public class User {
    String username = "liming";
    public User(String username) {
        this.username = username;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("用户已经被清理");
    }
}
