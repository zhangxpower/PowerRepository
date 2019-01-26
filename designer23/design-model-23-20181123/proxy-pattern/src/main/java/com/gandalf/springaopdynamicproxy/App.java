package com.gandalf.springaopdynamicproxy;

import com.gandalf.PublishHouse;
import com.gandalf.Salable;
import org.springframework.cglib.proxy.Enhancer;

public class App {
    public static void main(String[] args) {

        BookstoreAopDynamicProxy proxy = new BookstoreAopDynamicProxy(new PublishHouse());
        final ClassLoader classLoader = PublishHouse.class.getClassLoader();
        final Class<?>[] interfaces = PublishHouse.class.getInterfaces();
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(classLoader);
        enhancer.setInterfaces(interfaces);
        enhancer.setCallback(proxy);
        Salable salable = (Salable)enhancer.create();
        salable.sale();
    }
}
