package com.ybj.crawler.Learn.DesignPattern.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    String name;

    int age;

    public static void main(String[] args) {
        List<User> userList = new LinkedList<>();
        User user2 = new User("2",2);
        User user1 = new User("1",1);
        userList.add(user2);
        userList.add(user1);
        userList.sort(new AgeComparator());
        System.out.println("user2 = " + user2);
    }

}
