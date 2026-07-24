package SOLIDPRINCIPLES.Liskov.BetterCodeUsingStrategyPattern;

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car(new KeyStart());
        car.start();

        Vehicle bike = new Bicycle(new FingerprintStart());
        bike.start();
    }
}
