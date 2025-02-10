package com.fly.javacompiler.test1;


import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;

/**
 * 需要改造
 * 当前的ClassLoader不包含新编译的class
 */
public class DynamicClassExample {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        String code = " public class DynamicClass { public void run() { System.out.println(\"动态编译成功.\"); } }";
        JavaFileObject fileObject = new SimpleJavaFileObject(URI.create("string:///DynamicClass.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
                return code;
            }
        };

        Boolean call = compiler.getTask(null, fileManager, null, null, null, List.of(fileObject)).call();


        if(Boolean.TRUE.equals(call)) {
            System.out.println("内存编译成功");
        }else {
            System.out.println("内存编译失败");
        }

        //反射调用
        Class<?> dynamicClass = Class.forName("DynamicClass");
        dynamicClass.getMethod("run").invoke(dynamicClass.getDeclaredConstructor().newInstance());
    }
}
