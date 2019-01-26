package com.gandalf.abstract2factories;

import com.gandalf.MailSender;
import com.gandalf.Sendable;

public class MailSenderFactory implements Provider {

    @Override
    public Sendable product() {
        return new MailSender();
    }
}
