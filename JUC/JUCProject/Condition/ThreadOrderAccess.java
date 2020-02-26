package JUCProject.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @Description:
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 *1 多线程变成模板（套路）-----上
 *     1.1  线程    操作    资源类
 *     1.2  高内聚  低耦合
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知
3 防止虚假唤醒用while
4.注意标志位
 */

class ShareResource{
    private int number=1;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            for (int i = 0; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+i);
            }
            number=2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            for (int i = 0; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+i);
            }
            number=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            for (int i = 0; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+i);
            }
            number=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            try {
                shareResource.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                shareResource.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                shareResource.print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
