package com.fly.javascripting.test1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

public class RunJavaScriptExample {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        //绑定java对象到脚步环境
        engine.put("userList", List.of("John", "Jane", "Jack"));

        //执行脚本操作java对象
        engine.eval("function filterNames(prefix){return userList.stream().filter(name => name.startsWith(prefix))} var result=filterNames('A');");

        List<String> result = (List<String>) engine.get("result");

        System.out.println(result);
    }
}
