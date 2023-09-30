package com.learn.java.indepth.pojo;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class User {
    String username = "liming";
    public User(String username) {
        this.username = username;
    }
}
