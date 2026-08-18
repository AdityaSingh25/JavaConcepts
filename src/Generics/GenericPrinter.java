package Generics;

//public class GenericPrinter <T>{
//    T valueToPrint;
//
//    public GenericPrinter(T value){
//        this.valueToPrint = value;
//    }
//
//    public void print(){
//        System.out.println("Value is : " + valueToPrint);
//    }
//}


public class GenericPrinter<T extends Animal> {
    T valueToPrint;

    public GenericPrinter(T value) {
        this.valueToPrint = value;
    }

    public void print() {
        System.out.println("Value is : " + valueToPrint);
    }
}