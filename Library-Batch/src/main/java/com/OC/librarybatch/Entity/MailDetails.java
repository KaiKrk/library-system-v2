package com.OC.librarybatch.Entity;

import org.springframework.stereotype.Service;

@Service
public class MailDetails {


    private  String myAccountEmail = "annalibraryoc@gmail.com";

    private  String password = "LibraryOCP7+";

    private String subject = "Retour de livre emprunté";

    private String message = "Bonjour vous avez oublier de rendre votre livre veuillez nous le faire parvenir.\n Livre : ";

    public String getSubject() {
        return subject;
    }


    public String getMessage() {
        return message;
    }


    public String getMyAccountEmail() {
        return myAccountEmail;
    }

    public String getPassword() {
        return password;
    }
}
