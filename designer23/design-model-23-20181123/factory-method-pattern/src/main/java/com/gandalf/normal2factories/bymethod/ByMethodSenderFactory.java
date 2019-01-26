package com.gandalf.normal2factories.bymethod;

import com.gandalf.MailSender;
import com.gandalf.Sendable;
import com.gandalf.SmsSender;

/**
 * 工厂方法模式
 * 通过各自对应的方法获取实例
 * 问题：需要一个新功能类的实例时需要增加一个方法
 */
public class ByMethodSenderFactory {

    public ByMethodSenderFactory() {
        System.out.println("[ByMethod-factory]");
    }

    public Sendable getMailSender(){
        return new MailSender();
    }

    public Sendable getSmsSender(){
        return new SmsSender();
    }
}
