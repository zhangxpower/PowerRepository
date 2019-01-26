package com.gandalf;

public class DoubleCheckSingleton {

    /**
     * volatile 禁止指令重排序
     */
    private volatile static DoubleCheckSingleton singleton = null;

    private DoubleCheckSingleton() {}

    public static DoubleCheckSingleton getInstance(){
        if(singleton == null){
            synchronized (DoubleCheckSingleton.class){
                if(singleton == null){
                    /*
                    1. memory = allowed 分配内存地址
                    2. 初始化对象
                    3. 把memory指向引用
                     */
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
