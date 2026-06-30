package SOLIDPRINCIPLES.Liskov.ProblematicCode;

public class Bicycle implements Vehicle{
    public void startEngine(){
        throw new UnsupportedOperationException("Bicycle doesn't have the engine...!");
    }
}
