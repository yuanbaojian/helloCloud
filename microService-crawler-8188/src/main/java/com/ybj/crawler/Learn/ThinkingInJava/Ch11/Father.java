package com.ybj.crawler.Learn.ThinkingInJava.Ch11;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Owner yuanbaojian
 * @Team
 */
public interface Father extends Serializable {

    long getId();


    @AllArgsConstructor
    @Data
    class Person  implements Father {

        String name;
        public int age;
        private String sex;


        public static void main(String[] args) {
            Person person = new Person("bob",11,"m");
            Person person1 = new Person("anna",12,"f");

            List<Person> personList = new ArrayList<>();
            personList.add(person1);
            personList.add(person);

            List<Father> fatherList = new ArrayList<>();
            fatherList.addAll(personList);
            List<Person> collect = fatherList.stream().map(item -> {
                String string = JSON.toJSONString(item);
                return (Person)item;
                // String string = JSON.toJSONString(item);
                // Person person2 = JSONObject.parseObject(string, Person.class);
                // return person2;
            }).collect(Collectors.toList());

            String jsonString = JSONObject.toJSONString(fatherList);
            System.out.println("interfaceDemos = " + fatherList);
            List<Person> personList1 = JSONObject.parseArray(jsonString, Person.class);
            System.out.println("personList1 = " + personList1);
        }

        @Override
        public long getId() {
            return 0;
        }
    }
}
