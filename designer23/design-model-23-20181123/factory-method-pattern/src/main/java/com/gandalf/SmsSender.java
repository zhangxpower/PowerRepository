package com.gandalf;

public class SmsSender implements Sendable {
    @Override
    public void send() {
        System.out.println("[Sms] send message.");
    }
}
