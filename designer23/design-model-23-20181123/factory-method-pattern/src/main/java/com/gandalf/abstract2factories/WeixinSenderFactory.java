package com.gandalf.abstract2factories;

import com.gandalf.Sendable;
import com.gandalf.abstract2factories.sender.WeixinSender;

public class WeixinSenderFactory implements Provider{
    @Override
    public Sendable product() {
        return new WeixinSender();
    }
}
