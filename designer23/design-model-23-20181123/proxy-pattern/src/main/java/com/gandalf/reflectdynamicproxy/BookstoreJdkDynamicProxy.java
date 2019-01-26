package com.gandalf.reflectdynamicproxy;

import com.gandalf.PublishHouse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 书店 (基于JDK的reflect 动态代理)
 */
public class BookstoreJdkDynamicProxy implements InvocationHandler {

    private PublishHouse delegate;

    public BookstoreJdkDynamicProxy(PublishHouse delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[jdk-reflect-dynamic] 卖书前");
        Object v = method.invoke(delegate, args);
        System.out.println("[jdk-reflect-dynamic] 卖书后");
        return v;
    }

}
