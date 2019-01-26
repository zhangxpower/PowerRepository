package com.gandalf;

public class MailSender implements Sendable {
    @Override
    public void send() {
        System.out.println("[Mail] send message.");
    }
}
