package Multithreading.Threadpool.Executors;
import java.util.concurrent.Executors;
// step2 : make a class of type runnable so that we can pass an runnable obj to run the run method inside by threads
class WorkerThreadTask implements Runnable{
    private final int taskId;
    public WorkerThreadTask(int taskId){
        this.taskId = taskId;
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getId()+" processing task "+taskId);
        try{
            Thread.sleep(3000);// simulate some work
        }catch(InterruptedException e){
            System.out.println("Task Interrupted : "+e.getMessage());
        }
        finally{
            System.out.println("Tasks is done for task id : "+ taskId);
        }
    }
}
public class ExecutorService {
    public static void main(String[] args){
        // step1 : create the threads
        java.util.concurrent.ExecutorService service = Executors.newFixedThreadPool(2);
        //submit 5 task to the threads in thread pool
        for(int i = 1; i<=5;i++){
            service.submit(new WorkerThreadTask(i));
        }
        //OR you can use lambda
        for(int i = 1; i<=5;i++){
            final int taskId = i;
            service.submit(()->{
                System.out.println(Thread.currentThread().getId()+" processing task "+taskId);
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    System.out.println("Task Interrupted : "+e.getMessage());
                }
                finally{
                    System.out.println("Tasks is done for task id : "+ taskId);
                }
            });
        }
        service.shutdown();
    }
}
