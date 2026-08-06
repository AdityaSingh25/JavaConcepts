package LambdaFunction;

public class Lambda {

    public static void main(String[] args) {

        //1
        Cat c1 = new Cat();
        c1.print();


        //3
        Cat c2 = new Cat();
        printThing(c2);

        //4 now if you notice here this printThing is taking a obj of type Printable which is a interface ( with only one function there(functional interface)). and in order to define this print method, we need to make a class which implements this Printable interface then define the method, then make an obj of that class and then call it or pass to the printThing. in the end all matters is the implementation of the print() function!!

        // here comes the lambda function.

        // lambda allows to just pass the implementation of this print method directly with out making an extra class and defining the method.


        //5

        printThing(

                // just pass the function definition here, no need to pass the obj of the class which implements the interface
                () -> {
                    System.out.println("print method using lambds");
                }

                // so basically instead of passing the obj which contains this action we are passing the action itself

                ///  if the function definition has only one line you can skip this {}

                // printThong(()->System.out.println("print method using lambds"));

                // so this printThing(Printable thing) and this printThong(()->System.out.println("print method using lambds")) are same

                // therefore Printable thing = ()-> .....;
                // menaing this lambda expression is kind of an obj (Printable type)

        );
    }


    //2
    static void printThing(Printable thing) {
        // as this "thing" is of type Printable therefore it knows that it must have defined this method, so we can call it here without any error.
        thing.print();
    }


}
