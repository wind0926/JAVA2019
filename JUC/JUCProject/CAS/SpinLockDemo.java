package JUCProject.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
* 题目：
*   实现一个自旋锁
*   自旋锁的好处：循环比较直到成功为止，没有类似wait的阻塞
*
*   通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒，B随后进来发现当前
*   有线程持有锁，不是null,所有只能通过自旋等待，所以只能通过自旋等待，直到A释放锁后B随后抢到
* */
public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock()");

    }

    public static void main(String[] args) throws InterruptedException {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"AA").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"BB").start();
    }
}
