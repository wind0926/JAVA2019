package JUCProject.createThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//通过创建线程池的方法创建线程
class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <100 ; i++) {
            if (i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadDemo04 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor)service;
        threadPoolExecutor.execute(new NumberThread());
//       service.execute(new NumberThread());

    }
}
