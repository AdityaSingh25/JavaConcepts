package SOLIDPRINCIPLES.EXAMPLE3.ImprovedCodeOCP;

public class SMSNotification implements Notification{
    @Override
    public void sendMessage(String message){
        System.out.println("Email notification sent..");
    }
}
