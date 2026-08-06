package Multithreading;
import java.util.concurrent.*;

// 1. create a class which extends the callable.
public class UsingCallable implements Callable<String> {

    private final String name;

    public UsingCallable(String name){
        this.name = name;
    }

    // 2. Override the call method.
    @Override
    public String call() throws Exception{ // one of the features of callable is that it can throw checked exception
        StringBuilder result = new StringBuilder();
        for(int i = 1; i<=5;i++){
            result.append("Callable ").append(name).append(" is running ").append("on thread ").append(Thread.currentThread().threadId()).append(" "+i +"\n");
            Thread.sleep(1000);
        }
        return result.toString(); // this is the benifit of using callable, that it can retun the value in form of Future object.
    }

    public static void main(String[] agrs){

        // 3. create executor service with a fixed thread pool ( executors are like they will manage the treads on behalf of you)
        ExecutorService executor = Executors.newFixedThreadPool(2);


        // 3. create callable instance
        UsingCallable callable = new UsingCallable("Aditya");
        UsingCallable callable2 = new UsingCallable("Cosmo");
        UsingCallable callable3 = new UsingCallable("Goku");

        try{
            // submit the callable tasks to the executor and get the future objects
            Future<String> future1 = executor.submit(callable);
            Future<String> future2 = executor.submit(callable2);
            Future<String> future3 = executor.submit(callable3);


            // get results from future objects
            System.out.println(future2.get());
            System.out.println(future1.get());// blocks(main thread) until the task completes
            System.out.println(future3.get());


        } catch(InterruptedException | ExecutionException e){
            System.out.println(e);
        }finally{
            executor.shutdown();
        }
    }
}
