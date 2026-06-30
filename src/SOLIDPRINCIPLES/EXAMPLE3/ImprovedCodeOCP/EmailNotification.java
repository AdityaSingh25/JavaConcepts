package SOLIDPRINCIPLES.EXAMPLE3.ImprovedCodeOCP;

public class EmailNotification implements Notification{

    @Override
    public void sendMessage(String message){
        System.out.println("Email notification sent..");
    }
}
