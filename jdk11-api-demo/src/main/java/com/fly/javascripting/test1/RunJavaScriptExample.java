package com.fly.javascripting.test1;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;

public class RunJavaScriptExample {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        //绑定java对象到脚步环境
        engine.put("userList", Arrays.asList("Alice", "Bob", "Anna", "Tom", "Amanda"));

        //执行脚本操作java对象
        engine.eval("var arr = Java.from(userList); print('js invoke.'); function filterNames(prefix){ return arr.filter(function(item) {return item.startsWith(prefix)})} var result=filterNames('A'); ");

        Object result = engine.get("result");
        System.out.println(result);
    }
}
