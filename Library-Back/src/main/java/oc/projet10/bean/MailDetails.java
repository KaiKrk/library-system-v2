package oc.projet10.bean;

import org.springframework.stereotype.Service;

@Service
public class MailDetails {

//    @Autowired
//    MemberService memberService;

    private  String myAccountEmail = "annalibraryoc@gmail.com";

    private  String password = "19032020P10OC";

    private String subject = "Retour de livre emprunté";

    private String message = "Bonjour vous avez oublier de rendre votre livre veuillez nous le faire parvenir.\n Livre : ";

    private String pickupMessage = "Bonjour un livre sur lequel vous etiez en liste d'attente est desormais disponible" +
            " veuillez venir le recuperer sous 48 heures sinon le livre ne vous sera plus reservé." +
            "\n Libre concerné : ";

    public String getPickupMessage() {
        return pickupMessage;
    }

    public void setPickupMessage(String pickupMessage) {
        this.pickupMessage = pickupMessage;
    }

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
