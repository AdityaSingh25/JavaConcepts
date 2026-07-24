package SOLIDPRINCIPLES.Liskov.BetterCodeUsingStrategyPattern;

public class Car implements Vehicle{

    private final StartStrategy startStrategy;

    public Car(StartStrategy startStrategy){
        this.startStrategy = startStrategy;
    }

    @Override
    public void start(){
        startStrategy.start();
    }
}
