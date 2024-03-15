package com.ybj.mystarter.mystarter.util;


import org.springframework.stereotype.Service;

@Service
public class MyPrintService {

    public void printMessage(String message) {
        System.out.println("Printing message: " + message);
    }
}
