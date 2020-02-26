package JUCProject.Pro_Consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Data{
    private int number=0;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public  void product() throws InterruptedException {
        lock.lock();
        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();

        try {
            while (number!=1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
        }
    }
}


public class Product_Consumer {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
