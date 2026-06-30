package SOLIDPRINCIPLES.EXAMPLE3.ImprovedCodeOCP;

import SOLIDPRINCIPLES.EXAMPLE3.BetterCode.EmailNotification;
import SOLIDPRINCIPLES.EXAMPLE3.BetterCode.SMSNotification;

import java.util.List;

public class NotificationSender {
    public void sendNotification(List<Notification> notifications, String message){
        for(Notification notification : notifications){
            notification.sendMessage(message);
        }
    }
}
