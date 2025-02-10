package com.fly.javacompiler.test1.mem;

import java.util.HashMap;
import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {

    private Map<String, byte[]> classBytes = new HashMap<>();

    public InMemoryClassLoader(Map<String, byte[]> classBytes) {
        this.classBytes = classBytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(!classBytes.isEmpty()){
            byte[] bytes = classBytes.get(name);
            if(bytes != null){
                defineClass(name, bytes, 0, bytes.length);
            }
        }
        return super.findClass(name);
    }
}
