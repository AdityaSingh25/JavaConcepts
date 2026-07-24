package SOLIDPRINCIPLES.Liskov.BetterCodeUsingStrategyPattern;

public class KeyStart implements StartStrategy {

    @Override
    public void start() {
        System.out.println("Engine started with Key...!");
    }
}
