package com.gandalf.normal2factories.byname;

import com.gandalf.Sendable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ByNameSenderFactory senderFactory = new ByNameSenderFactory();
        final Sendable mail = senderFactory.getSenderInstance("MAIL");
        mail.send();

        final Sendable sms = senderFactory.getSenderInstance("SMS");
        sms.send();
    }
}
