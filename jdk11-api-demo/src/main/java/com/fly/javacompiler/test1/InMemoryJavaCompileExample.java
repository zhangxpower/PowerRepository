package com.fly.javacompiler.test1;

import org.mdkt.compiler.InMemoryJavaCompiler;

public class InMemoryJavaCompileExample {

    public static void main(String[] args) throws Exception {

        String sourceCode = "public class DynamicClass { " +
                "    public void run() { System.out.println(\"动态编译成功!\"); } " +
                "}";

        Class<?> dynamicClass = InMemoryJavaCompiler.newInstance().compile("DynamicClass", sourceCode);
        dynamicClass.getMethod("run").invoke(dynamicClass.getConstructor().newInstance());
    }
}
