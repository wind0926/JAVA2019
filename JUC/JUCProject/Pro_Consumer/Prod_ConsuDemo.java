package JUCProject.Pro_Consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Resource{
    private volatile boolean FLAG=true;
     BlockingQueue<String> blockingQueue=null;
    private AtomicInteger automicInteger=new AtomicInteger();

    public Resource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void prod() throws InterruptedException {
        String data=null;
        boolean retValue;
        while (FLAG){
            data=automicInteger.getAndIncrement()+"";
            retValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t"+"插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停，表示FLAG=false,生产动作结束");
    }
    public void consumer() throws InterruptedException {
        String result=null;
        while (FLAG){
            result=blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null==result||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过2m没有取到 消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop(){
        FLAG=false;
    }
}

public class Prod_ConsuDemo {
    public static void main(String[] args) {
        Resource resource = new Resource(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            try {
                resource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                resource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到,停止活动");
        resource.stop();

    }
}
