package JUCProject.createThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//通过实现Callable接口创建线程
class MyThread02 implements Callable{

    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 1; i <100 ; i++) {
            if (i%2==0){
                System.out.println(i);
                sum+=i;
            }
        }
        return sum;
    }
}

public class ThreadDemo03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread02 myThread02 = new MyThread02();
        FutureTask futureTask = new FutureTask<>(myThread02);

        Thread thread = new Thread(futureTask);
        thread.start();
        Object o = futureTask.get();
        System.out.println(o);

    }
}
