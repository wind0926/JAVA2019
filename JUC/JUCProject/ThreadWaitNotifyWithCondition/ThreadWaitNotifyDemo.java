package JUCProject.ThreadWaitNotifyWithCondition;
/*
*  * 笔记：Java里面如何进行工程级别的多线程编写
 * 1 多线程变成模板（套路）-----上
 *     1.1  线程    操作    资源类
 *     1.2  高内聚  低耦合
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知
    3 防止虚假唤醒用while
    4.注意标志位
 *
*
* */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner{
    private int number=0;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment() throws Exception {
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
    public void decrement(){
        lock.lock();
        try {
            while (number==0){
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

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {

                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {

                airConditioner.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {

                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {

                airConditioner.decrement();
            }
        },"D").start();

    }
}
