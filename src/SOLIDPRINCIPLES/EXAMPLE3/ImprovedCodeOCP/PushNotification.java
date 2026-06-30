package SOLIDPRINCIPLES.EXAMPLE3.ImprovedCodeOCP;

public class PushNotification implements Notification{

    @Override
    public void sendMessage(String message){
        System.out.println("Email notification sent..");
    }
}
