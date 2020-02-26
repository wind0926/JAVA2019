package JUCProject.ThreadPool;

import java.util.concurrent.*;

/*
* 第四种使用java多线程的方法，线程池
* */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());//
//        ExecutorService threadPool= Executors.newFixedThreadPool(5);//一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个线程
//       ExecutorService threadPool = Executors.newCachedThreadPool();//一池n个线程
        ExecutorService threadPool= new ThreadPoolExecutor(2,7,2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy()
                );


        //模拟有10 个顾客来银行办理业务，目前池子里有五个工作人员提供服务
        try {
            for (int i = 1; i <=200; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
