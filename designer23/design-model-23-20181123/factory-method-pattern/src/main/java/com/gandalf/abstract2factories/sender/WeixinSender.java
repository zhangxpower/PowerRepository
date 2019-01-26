package com.gandalf.abstract2factories.sender;

import com.gandalf.Sendable;

public class WeixinSender implements Sendable {
    @Override
    public void send() {
        System.out.println("[Weixin] send message.");
    }
}
