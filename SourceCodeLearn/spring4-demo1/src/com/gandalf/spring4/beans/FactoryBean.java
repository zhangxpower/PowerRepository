package com.gandalf.spring4.beans;

import java.util.Random;

public class FactoryBean {

    public static Integer createRandom(int bound){
        return new Integer(new Random().nextInt(bound));
    }
}
