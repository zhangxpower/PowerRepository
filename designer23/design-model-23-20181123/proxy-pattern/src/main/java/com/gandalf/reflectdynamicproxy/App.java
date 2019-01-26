package com.gandalf.reflectdynamicproxy;

import com.gandalf.PublishHouse;
import com.gandalf.Salable;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class App {
    public static void main(String[] args) {

        BookstoreJdkDynamicProxy proxy = new BookstoreJdkDynamicProxy(new PublishHouse());
        final ClassLoader classLoader = PublishHouse.class.getClassLoader();
        final Class<?>[] interfaces = PublishHouse.class.getInterfaces();
        Salable salable = (Salable)Proxy.newProxyInstance(classLoader, interfaces, proxy);
        salable.sale();

        try {
            Field field = System.class.getDeclaredField("props");
            field.setAccessible(true);
            Properties props = (Properties) field.get(null);
            props.put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
