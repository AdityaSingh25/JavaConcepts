package Generics;

public class DoublePrinter {
    Double integerPrinter;

    public DoublePrinter(Double integerPrinter) {
        this.integerPrinter = integerPrinter;
    }

    public void print() {
        System.out.println("Double value is : " + integerPrinter);
    }
}
