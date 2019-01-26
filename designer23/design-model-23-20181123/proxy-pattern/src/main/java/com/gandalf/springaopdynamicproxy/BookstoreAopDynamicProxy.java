package com.gandalf.springaopdynamicproxy;

import com.gandalf.PublishHouse;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * 书店 (基于spring AOP 动态代理)
 */
public class BookstoreAopDynamicProxy implements InvocationHandler {

    private PublishHouse delegate;

    public BookstoreAopDynamicProxy(PublishHouse delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[spring-aop-dynamic] 卖书前");
        Object v = method.invoke(delegate, args);
        System.out.println("[spring-aop-dynamic] 卖书后");
        return v;
    }

}
