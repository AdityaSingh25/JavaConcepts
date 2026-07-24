package SOLIDPRINCIPLES.Liskov.BetterCodeUsingStrategyPattern;

public class FingerprintStart implements StartStrategy {

    @Override
    public void start() {
        System.out.println("Engine started by finger print...!");
    }
}
