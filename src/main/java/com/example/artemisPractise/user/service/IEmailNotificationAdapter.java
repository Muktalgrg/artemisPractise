package com.example.artemisPractise.user.service;

import java.util.List;

public interface IEmailNotificationAdapter {
    public void sendEmail(List<String> to, List<String> cc, String subject, String content) throws Exception;
    public void sendEmail(String to, String subject, String content) throws Exception;
}
