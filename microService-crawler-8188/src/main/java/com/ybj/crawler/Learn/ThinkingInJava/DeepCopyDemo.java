package com.ybj.crawler.Learn.ThinkingInJava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeepCopyDemo {

    @Data
    @AllArgsConstructor
    static class User implements Cloneable{
        private String name;
        private Address address;

        @Override
        public User clone() {
            try {
                User clone = (User) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Address implements Cloneable{
        private String city;

        private String country;

        @Override
        public Address clone() {
            try {
                Address clone = (Address) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public static void main(String[] args) {
        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);
        User newUser = getByClone(user);
        log.info("user = new User is {}",user == newUser);
    }

    public static User getByConstructor(User user){
        return new User(user.getName(),user.getAddress());
    }

    public static User getByClone(User user){
        return user.clone();
    }


}
