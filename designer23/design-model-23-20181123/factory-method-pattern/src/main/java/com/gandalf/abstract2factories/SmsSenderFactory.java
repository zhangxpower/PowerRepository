package com.gandalf.abstract2factories;

import com.gandalf.Sendable;
import com.gandalf.SmsSender;

public class SmsSenderFactory implements Provider {
    @Override
    public Sendable product() {
        return new SmsSender();
    }
}
