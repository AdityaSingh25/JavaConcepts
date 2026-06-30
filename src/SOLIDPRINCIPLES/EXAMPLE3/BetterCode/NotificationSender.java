package SOLIDPRINCIPLES.EXAMPLE3.BetterCode;

import java.util.List;

public class NotificationSender {
    public void sendNotification(List<String> n, String message){
        for(String m:n){
            switch(m){
                case "SMS":
                    SMSNotification en = new SMSNotification();
                    en.sendSMSNotification(message);
                    break;
                case "Email":
                    EmailNotification em = new EmailNotification();
                    em.sendEmailNotification(message);

            }
        }
    }
}
