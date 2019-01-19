package com.kits.project.services.interfaces;

import com.kits.project.model.User;

public interface EmailServiceInterface {
    void sendActivationMail(User account);

    void sendMail(String title, String text, String to);
}
