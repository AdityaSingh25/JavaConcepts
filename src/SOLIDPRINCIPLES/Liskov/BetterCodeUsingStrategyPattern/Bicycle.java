package SOLIDPRINCIPLES.Liskov.BetterCodeUsingStrategyPattern;

public class Bicycle implements Vehicle{

    private final StartStrategy startStrategy;

    public Bicycle(StartStrategy startStrategy){
        this.startStrategy = startStrategy;
    }

    @Override
    public void start(){
        startStrategy.start();
    }
}
