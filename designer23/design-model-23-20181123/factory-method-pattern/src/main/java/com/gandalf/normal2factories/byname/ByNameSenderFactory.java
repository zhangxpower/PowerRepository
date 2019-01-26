package com.gandalf.normal2factories.byname;

import com.gandalf.MailSender;
import com.gandalf.Sendable;
import com.gandalf.SmsSender;

/**
 * 普通工厂方法
 * 根据传递的名称来实例化对应的实例
 * 问题：太依赖传入的实例名称
 */
public class ByNameSenderFactory {

    public ByNameSenderFactory() {
        System.out.println("[ByName-Factory]");
    }

    /**
     * 通过名称获取对应的实例
     * @param sendName
     * @return
     */
    public Sendable getSenderInstance(String sendName){
        if("MAIL".equals(sendName)){
            return new MailSender();
        }else if("SMS".equals(sendName)){
            return new SmsSender();
        }
        return null;
    }
}
