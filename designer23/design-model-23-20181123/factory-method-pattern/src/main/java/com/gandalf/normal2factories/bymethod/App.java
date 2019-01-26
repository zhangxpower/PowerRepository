package com.gandalf.normal2factories.bymethod;

import com.gandalf.Sendable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ByMethodSenderFactory senderFactory = new ByMethodSenderFactory();
        final Sendable mail = senderFactory.getMailSender();
        mail.send();
        final Sendable sms = senderFactory.getSmsSender();
        sms.send();

        final Sendable mailSender = ByMethodStaticSenderFactory.getMailSender();
        mailSender.send();
        final Sendable smsSender = ByMethodStaticSenderFactory.getSmsSender();
        smsSender.send();
    }
}
