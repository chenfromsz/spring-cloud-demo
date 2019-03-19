package com.demo.data.util;

import com.fasterxml.uuid.Generators;

import java.util.UUID;


public abstract class BaseIDUtils {
    public static String randomUUID(){
        UUID uuid = Generators.randomBasedGenerator().generate();
        return uuid.toString().replace("-", "");
    }

    public static void main(String[] args) {
//        for(int i=0;i<100;i++){
//            System.out.println(randomUUID());
//        }
    }
}
