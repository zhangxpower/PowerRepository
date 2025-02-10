package com.fly.javascripting.test1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class NashornFilterExample {
    public static void main(String[] args) {
        // 模拟一个 userList，包含若干用户名
        List<String> userList = Arrays.asList("Alice", "Bob", "Anna", "Tom", "Amanda");

        // 获取 Nashorn 脚本引擎
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        // 将 userList 放入脚本上下文中（名称为 "userList"）
        engine.put("userList", userList);

        // 编写 JS 脚本：使用 Java.from 将 Java List 转换为 JavaScript 数组，
        // 然后调用 filter 方法筛选出以 "A" 开头的用户
        String script =
                "var jsArray = Java.from(userList); " +
                        "var filtered = jsArray.filter(function(user) { " +
                        "    return user.startsWith('A'); " +
                        "}); " +
                        "filtered;";  // 脚本的最后一个表达式返回结果

        try {
            // 执行脚本，返回结果为经过过滤后的数组
            Object result = engine.eval(script);

            // Nashorn 返回的结果通常是 ScriptObjectMirror 类型，
            // 可以直接打印或进一步转换为 Java 集合
            System.out.println("过滤后的用户数组：" + result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
