package com.gandalf.spring4;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringDemo {
    /**
     * spring的设计模式
     */

    public static void main(String[] args) {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Integer v = (Integer)context.getBean("random");
        System.out.println(v);
    }
}
