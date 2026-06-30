package SOLIDPRINCIPLES.EXAMPLE3.ProblematicCode;

import java.util.List;

public class NotificationSender {
    public void sendNotifications(List<NotificationType> notificationTypes, String message) {
        for (NotificationType n : notificationTypes) {
            switch (n) {
                case SMS:
                    n.sendSMSNotification(message);
                    break;
                case EMAIL:
                    n.sendEmailNotification(message);
                    break;
                case PUSH:
                    n.sendPushNotification(message);
                    break;
                case WHATSAPP:
                    n.sendWhatsAppNotification(message);

            }
        }
    }
}
