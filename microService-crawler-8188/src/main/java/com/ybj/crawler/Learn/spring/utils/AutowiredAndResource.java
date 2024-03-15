package com.ybj.crawler.Learn.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutowiredAndResource {

    //@Resource
    //private Person student;


}

interface Person {
}


@Component("studentBean")
class Student implements Person
{

}

@Component()
class Teacher implements Person
{

}




