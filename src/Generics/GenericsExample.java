package Generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample {




    // generics in methods

    private static <T> void genericMethod(T abc){
        System.out.println("Inside generic method : "+abc);
    }

    private static <T,V> void generic2(T abc, V xyz){
        System.out.println("Value of T is "+ abc +" and type of T is "+ abc.getClass()+" and Value of V is : "+ xyz + " and Type of V is "+xyz.getClass());
    }

    // wild card : when you don't know what the type is

    public static void printList(List<? extends Object> myList){ // here you can't write List<Object>
        System.out.println(myList);
    }

    public static void main(String[] args){
//        IntegerPrinter printer = new IntegerPrinter(2);
//        printer.print();

        GenericPrinter<Cat> gp = new GenericPrinter<>(new Cat());
        gp.print();
        gp.valueToPrint.say();


        GenericsExample.genericMethod("Adi");



        generic2(23,"Adi");


        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);

        printList(list);

    }
}
