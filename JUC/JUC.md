
* [1、JUC是什么](#1、juc是什么)
  * [进程/线程回顾](#进程线程回顾)
    * [1进程/线程是什么？](#1进程线程是什么？)
    * [2线程状态？](#2线程状态？)
      * [join()](#join)
      * [yield()](#yield)
      * [InterruptedException](#interruptedexception)
      * [interrupted()](#interrupted)
    * [3wait/sleep的区别？](#3waitsleep的区别？)
    * [4什么是并发？什么是并行？](#4什么是并发？什么是并行？)
* [2、Synchronized](#2、synchronized)
  * [synchronized实现原理](#synchronized实现原理)
  * [synchronized如何获取monitor对象？](#synchronized如何获取monitor对象？)
      * [synchronized修饰代码块](#synchronized修饰代码块)
      * [synchronized修饰方法](#synchronized修饰方法)
    * [锁的升级](#锁的升级)
  * [偏向锁](#偏向锁)
  * [轻量级锁](#轻量级锁)
  * [几种锁的优缺点](#几种锁的优缺点)
  * [用锁的最佳实践](#用锁的最佳实践)
  * [Lock接口的使用](#lock接口的使用)
* [3、创建线程的方式](#3、创建线程的方式)
    * [1.继承Thread类](#1继承thread类)
    * [2.实现Runnable接口创建线程](#2实现runnable接口创建线程)
    * [3.通过实现Callable接口创建线程](#3通过实现callable接口创建线程)
    * [4.通过创建线程池的方法创建线程](#4通过创建线程池的方法创建线程)
* [4、线程间通信](#4、线程间通信)
    * [1运用notify和wait实现线程的通信](#1运用notify和wait实现线程的通信)
    * [2Condition实现](#2condition实现)
* [5、线程间定制化调用通信](#5、线程间定制化调用通信)
* [6、NotSafeDemo](#6、notsafedemo)
    * [1证明集合不安全](#1证明集合不安全)
    * [2解决方案](#2解决方案)
      * [Vector](#vector)
      * [Collections](#collections)
      * [写时复制](#写时复制)
* [7、多线程锁](#7、多线程锁)
    * [8锁分析](#8锁分析)
* [8、ReentrantReadWriteLock读写锁](#8、reentrantreadwritelock读写锁)
* [9、BlockingQueueDemo阻塞队列](#9、blockingqueuedemo阻塞队列)
    * [种类分析](#种类分析)
    * [BlockingQueue核心方法](#blockingqueue核心方法)
    * [生产者消费者问题](#生产者消费者问题)
* [10、ThreadPool线程池](#10、threadpool线程池)
    * [1为什么用线程池](#1为什么用线程池)
    * [2程池如何使用](#2程池如何使用)
      * [架构说明](#架构说明)
      * [编码实现](#编码实现)
    * [3线程池几个重要参数](#3线程池几个重要参数)
    * [4线程池底层工作原理](#4线程池底层工作原理)
    * [5线程池的拒绝策略](#5线程池的拒绝策略)
      * [JDK内置的拒绝策略](#jdk内置的拒绝策略)
    * [6自定义线程池](#6自定义线程池)
* [11、Java8之流式计算](#11、java8之流式计算)
    * [函数式接口](#函数式接口)
      * [java内置核心四大函数式接口](#java内置核心四大函数式接口)
    * [特点](#特点)
    * [阶段](#阶段)
* [12、分支合并框架](#12、分支合并框架)
    * [原理](#原理)
* [13、异步回调](#13、异步回调)
* [14、谈谈你对volatile的理解](#14、谈谈你对volatile的理解)
    * [1.volatile是Java虚拟机提供的轻量级的同步机制](#1volatile是java虚拟机提供的轻量级的同步机制)
    * [2.JMM你谈谈](#2jmm你谈谈)
      * [2.1可见性](#21可见性)
      * [2.2原子性](#22原子性)
      * [2.3VolatileDemo代码演示可见性+原子性代码](#23volatiledemo代码演示可见性原子性代码)
      * [2.4有序性](#24有序性)
    * [3.你在哪些地方用到过volatile?](#3你在哪些地方用到过volatile)
      * [3.1 单例模式DCL代码](#31-单例模式dcl代码)
      * [3.2代理模式volatile分析](#32代理模式volatile分析)
* [15、CAS你知道吗](#15、cas你知道吗)
    * [1.比较并交换](#1比较并交换)
    * [2.CAS底层原理?如果知道,谈谈你对UnSafe的理解](#2cas底层原理如果知道谈谈你对unsafe的理解)
      * [atomicInteger.getAndIncrement();](#atomicintegergetandincrement)
      * [UnSafe](#unsafe)
      * [CAS是什么](#cas是什么)
        * [unSafe.getAndIncrement](#unsafegetandincrement)
        * [底层汇编](#底层汇编)
        * [简单版小总结](#简单版小总结)
    * [3.CAS缺点](#3cas缺点)
        * [循环时间长开销很大](#循环时间长开销很大)
        * [只能保证一个共享变量的原子性](#只能保证一个共享变量的原子性)
        * [引出来ABA问题???](#引出来aba问题)
* [16、原子类AtomicInteger的ABA问题谈谈?原子更新引用知道吗](#16、原子类atomicinteger的aba问题谈谈原子更新引用知道吗)
    * [ABA问题的产生](#aba问题的产生)
    * [原子引用](#原子引用)
    * [时间戳原子引用](#时间戳原子引用)
* [17、公平锁/非公平锁/可重入锁/递归锁/自旋锁谈谈你的理解?请手写一个自旋锁](#17、公平锁非公平锁可重入锁递归锁自旋锁谈谈你的理解请手写一个自旋锁)
    * [公平锁和非公平锁](#公平锁和非公平锁)
        * [是什么](#是什么)
        * [两者的区别](#两者的区别)
    * [可重入锁(又名递归锁)](#可重入锁又名递归锁)
        * [ReentrantLock/synchronized就是一个典型的可重入锁](#reentrantlocksynchronized就是一个典型的可重入锁)
    * [自旋锁](#自旋锁)
    * [独占锁(写)/共享锁(读)/互斥锁](#独占锁写共享锁读互斥锁)
* [18、JUC强大的辅助类讲解](#18、juc强大的辅助类讲解)
    * [CountDownLatch减少计数](#countdownlatch减少计数)
    * [CyclicBarrier循环栅栏](#cyclicbarrier循环栅栏)
    * [Semaphore信号量](#semaphore信号量)
* [19、死锁编码及定位分析](#19、死锁编码及定位分析)
    * [解决](#解决)
        * [jps命令定位进程编号](#jps命令定位进程编号)
        * [jstack找到死锁查看](#jstack找到死锁查看)
* [20、同步器 AQS](#20、同步器-aqs)
    * [AQS 的原理是什么？](#aqs-的原理是什么？)
* [21、谈谈对 ThreadLocal 的理解？](#21、谈谈对-threadlocal-的理解？)
    * [在哪些场景下会使用到 ThreadLocal？](#在哪些场景下会使用到-threadlocal？)


# 1、JUC是什么

```
java.util.concurrent在并发编程中使用的工具类
```

## 进程/线程回顾



### 1进程/线程是什么？

```
进程是“执行中的程序”，进行资源分配和调度的独立单位，线程是进程的实体，一个进程可以拥有多个线程，线程的上下文切换比进程要快，线程之间共享地址空间和其他资源。
```



### 2线程状态？

```
Thread 的源码中定义了6种状态：new（新建）、runnnable（可运行）、blocked（阻塞）、waiting（等待）、time waiting （定时等待）和 terminated（终止）。
```

![1584673818018](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/1.png)

线程状态转换如下图所示：

![1584673906043](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/2.png)



#### join()

在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。

对于以下代码，虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，因此最后能够保证 a 线程的输出先于 b 线程的输出。

```java
public class JoinExample {

    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
public static void main(String[] args) {
    JoinExample example = new JoinExample();
    example.test();
}
A
B
```



#### yield()

对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。

```java
public void run() {
    Thread.yield();
}
```



#### InterruptedException

通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。

对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。

```java
public class InterruptExample {

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new MyThread1();
    thread1.start();
    thread1.interrupt();
    System.out.println("Main run");
}

Main run
java.lang.InterruptedException: sleep interrupted
    at java.lang.Thread.sleep(Native Method)
    at InterruptExample.lambda$main$0(InterruptExample.java:5)
    at InterruptExample$$Lambda$1/713338599.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)
```



#### interrupted()

如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。

但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。

```java
public class InterruptExample {

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
                // ..
            }
            System.out.println("Thread end");
        }
    }
}
public static void main(String[] args) throws InterruptedException {
    Thread thread2 = new MyThread2();
    thread2.start();
    thread2.interrupt();
}

Thread end
```



### 3wait/sleep的区别？

```
1.sleep() 方法正在执行的线程主动让出 cpu（然后 cpu 就可以去执行其他任务），在sleep 指定时间后 cpu 再回到该线程继续往下执行（注意：sleep 方法只让出了 cpu，	而并不会释放同步资源锁）；而 wait() 方法则是指当前线程让自己暂时退让出同步	资源锁，以便其他正在等待该资源的线程得到该资源进而运行，只有调用了notify()方法，之前调用 wait() 的线程才会解除 wait 状态，可以去参与竞争同步资源锁，进	而得到执行。（注意notify 的作用相当于叫醒睡着的人，而并不会给他分配任务，	就是说 notify 只是让之前调用 wait 的线程有权利重新参与线程的调度）；

2.sleep() 方法可以在任何地方使用，而 wait() 方法则只能在同步方法或同步块中使用；

3.sleep() 是线程类（Thread）的方法，调用会暂停此线程指定的时间，但监控依然保持，不会释放对象锁，到时间自动恢复；wait() 是 Object 的方法，调用会放弃对象锁,进入等待队列，待调用 notify()/notifyAll() 唤醒指定的线程或者所有线程，才会进	入锁池，不再次获得对象锁才会进入运行状态。
```



### 4什么是并发？什么是并行？

```
1.解释一：并行是指两个或者多个事件在同一时刻发生；而并发是指两个或多个事件在同一时间间隔发生。

2.解释二：并行是在不同实体上的多个事件，并发是在同一实体上的多个事件.
```



# 2、Synchronized

我们知道并发编程会产生各种问题的源头是可见性，原子性，有序性。而synchronized能同时保证可见性，原子性，有序性。所以我们在解决并发问题的时候经常用synchronized，当然还有很多其他工具，如volatile。但是volatile只能保证可见性，有序性，不能保证原子性 .

synchronized可以用在如下地方

1. 修饰实例方法，对当前实例对象this加锁
2. 修饰静态方法，对当前类的Class对象加锁
3. 修饰代码块，指定加锁对象，对给定对象加锁

**修饰实例方法**

```java
public class SynchronizedDemo {    
    public synchronized void methodOne() {    
    
    }
}
```

**修饰静态方法**

```java
public class SynchronizedDemo {    
	public static synchronized void methodTwo() { 
        
    }
}
```

**修饰代码块**

```java
public class SynchronizedDemo {    
    public void methodThree() {        
        // 对当前实例对象this加锁        
        synchronized (this) {   
            
        }    
    }    
    public void methodFour() {        
        // 对class对象加锁        
        synchronized (SynchronizedDemo.class) {        
            
        }    
    }
}
```



## synchronized实现原理

 我们都知道对象是放在堆内存中的，对象大致可以分为三个部分，分别是对象头，实例变量和填充字节 

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/3.jpg) 

- 对象头，主要包括两部分1. Mark Word (标记字段)，2.Klass Pointer(类型指针)。Klass Point 是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。Mark Word用于存储对象自身的运行时数据
- 实例变量，存放类的属性数据信息，包括父类的属性信息，这部分内存按4字节对齐
- 填充数据，由于虚拟机要求对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐



synchronized不论是修饰方法还是代码块，都是通过持有修饰对象的锁来实现同步，那么synchronized锁对象是存在哪里的呢？答案是存在锁对象的对象头Mark Word，来看一下Mark Word存储了哪些内容？

由于对象头的信息是与对象自身定义的数据没有关系的额外存储成本，因此考虑到JVM的空间效率，Mark Word 被设计成为一个非固定的数据结构，以便存储更多有效的数据，它会根据对象本身的状态复用自己的存储空间，也就是说，Mark Word会随着程序的运行发生变化，变化状态如下 (32位虚拟机)：

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/4.png) 

 其中轻量级锁和偏向锁是Java 6 对 synchronized 锁进行优化后新增加的，稍后我们会简要分析。这里我们主要分析一下重量级锁也就是通常说synchronized的对象锁，锁标识位为10，其中指针指向的是monitor对象（也称为管程或监视器锁）的起始地址。每个对象都存在着一个 monitor 与之关联。在Java虚拟机(HotSpot)中，monitor是由ObjectMonitor实现的，其主要数据结构如下（位于HotSpot虚拟机源码ObjectMonitor.hpp文件，C++实现的），省略部分属性 

```c
ObjectMonitor() {
    _count        = 0; //记录数
    _recursions   = 0; //锁的重入次数
    _owner        = NULL; //指向持有ObjectMonitor对象的线程 
    _WaitSet      = NULL; //调用wait后，线程会被加入到_WaitSet
    _EntryList    = NULL ; //等待获取锁的线程，会被加入到该列表
}
```

 ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/5.png) 

结合线程状态解释一下执行过程。(状态装换参考自《深入理解Java虚拟机》)



1. 新建（New），新建后尚未启动的线程

2. 运行（Runable），Runnable包括了操作系统线程状态中的Running和Ready

3. 无限期等待（Waiting），不会被分配CPU执行时间，要等待被其他线程显式的唤醒。例如调用没有设置Timeout参数的Object.wait()方法

4. 限期等待（Timed Waiting），不会被分配CPU执行时间，不过无需等待其他线程显示的唤醒，在一定时间之后会由系统自动唤醒。例如调用Thread.sleep()方法

5. 阻塞（Blocked），线程被阻塞了，“阻塞状态”与“等待状态”的区别是：“阻塞状态”在等待获取着一个排他锁，这个事件将在另外一个线程放弃这个锁的时候发生，而“等待状态”则是在等待一段时间，或者唤醒动作的发生。在程序等待进入同步区域的时候，线程将进入这种状态

6. 结束（Terminated）：线程结束执行

   ![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/6.png)

对于一个synchronized修饰的方法(代码块)来说：

1. 当多个线程同时访问该方法，那么这些线程会先被**放进_EntryList队列**，此时线程处于blocked状态
2. 当一个线程获取到了对象的monitor后，那么就可以进入running状态，执行方法，此时，ObjectMonitor对象的**_owner指向当前线程，_count加1**表示当前对象锁被一个线程获取
3. 当running状态的线程调用wait()方法，那么当前线程释放monitor对象，进入waiting状态，ObjectMonitor对象的**_owner变为null，_count减1**，同时线程进入_WaitSet队列，直到有线程调用notify()方法唤醒该线程，则该线程进入_EntryList队列，竞争到锁再进入_Owner区
4. 如果当前线程执行完毕，那么也释放monitor对象，ObjectMonitor对象的**_owner变为null，_count减1**

由此看来，monitor对象存在于每个Java对象的对象头中(存储的是指针)，synchronized锁便是通过这种方式获取锁的，也是**为什么Java中任意对象可以作为锁的原因，同时也是notify/notifyAll/wait等方法存在于顶级对象Object中的原因**



## synchronized如何获取monitor对象？

那么synchronized是通过什么方式来获取monitor对象的？

#### synchronized修饰代码块

```java
public class SyncCodeBlock {    
    public int count = 0;    
    public void addOne() {        
        synchronized (this) {            
            count++;        
        }    
    }
}

javac SyncCodeBlock.java
javap -v SyncCodeBlock.class
```

反编译的字节码如下

```java
 public void addOne();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=3, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter // 进入同步方法
         4: aload_0
         5: dup
         6: getfield      #2                  // Field count:I
         9: iconst_1
        10: iadd
        11: putfield      #2                  // Field count:I
        14: aload_1
        15: monitorexit // 退出同步方法
        16: goto          24
        19: astore_2
        20: aload_1
        21: monitorexit // 退出同步方法
        22: aload_2
        23: athrow
        24: return
      Exception table:
```

可以看到进入同步代码块，执行monitorenter指令，退出同步代码块，执行monitorexit指令，可以看到有2个monitorexit指令，第一个是正常退出执行的，第二个是当异常发生时执行的

#### synchronized修饰方法

```java
public class SyncMethod {
    public int count = 0;
    public synchronized void addOne() {
        count++;
    }
}
```

反编译的字节码如下

```java
  public synchronized void addOne();
    descriptor: ()V
    // 方法标识ACC_PUBLIC代表public修饰，ACC_SYNCHRONIZED指明该方法为同步方法
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: dup
         2: getfield      #2                  // Field count:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field count:I
        10: return
      LineNumberTable:
```

我们并没有看到monitorenter和monitorexit指令，那是怎么来实现同步的呢？
可以看到方法被标识为ACC_SYNCHRONIZED，表明这是一个同步方法



### 锁的升级

在Java早期版本中，synchronized属于重量级锁，效率低下，因为操作系统实现线程之间的切换时需要从用户态转换到核心态，这个状态之间的转换需要相对比较长的时间，时间成本相对较高。庆幸的是在Java 6之后Java官方对从JVM层面对synchronized较大优化，所以现在的synchronized锁效率也优化得很不错了，Java 6之后，为了减少获得锁和释放锁所带来的性能消耗，引入了偏向锁和轻量级锁，简单介绍一下

synchronized锁有四种状态，无锁，偏向锁，轻量级锁，重量级锁，这几个状态会随着竞争状态逐渐升级，**锁可以升级但不能降级，但是偏向锁状态可以被重置为无锁状态**



## 偏向锁

**为什么要引入偏向锁？**

因为经过HotSpot的作者大量的研究发现，大多数时候是不存在锁竞争的，常常是一个线程多次获得同一个锁，因此如果每次都要竞争锁会增大很多没有必要付出的代价，为了降低获取锁的代价，才引入的偏向锁。

**偏向锁原理和升级过程**

当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向的锁的threadID，因为**偏向锁不会主动释放锁**，因此以后线程1再次获取锁的时候，需要**比较当前线程的threadID和Java对象头中的threadID是否一致**，如果一致（还是线程1获取锁对象），则无需使用CAS来加锁、解锁；如果不一致（其他线程，如线程2要竞争锁对象，而偏向锁不会主动释放因此还是存储的线程1的threadID），那么**需要查看Java对象头中记录的线程1是否存活**，如果没有存活，那么锁对象被重置为无锁状态，其它线程（线程2）可以竞争将其设置为偏向锁；如果存活，那么立刻**查找该线程（线程1）的栈帧信息，如果还是需要继续持有这个锁对象**，那么暂停当前线程1，撤销偏向锁，升级为轻量级锁，如果线程1 不再使用该锁对象，那么将锁对象状态设为无锁状态，重新偏向新的线程。



## 轻量级锁

**为什么要引入轻量级锁？**

轻量级锁考虑的是竞争锁对象的线程不多，而且线程持有锁的时间也不长的情景。因为阻塞线程需要CPU从用户态转到内核态，代价较大，如果刚刚阻塞不久这个锁就被释放了，那这个代价就有点得不偿失了，因此这个时候就干脆不阻塞这个线程，让它自旋这等待锁释放。

**轻量级锁原理和升级过程**

线程1获取轻量级锁时会先**把锁对象的对象头MarkWord复制一份到线程1的栈帧中创建的用于存储锁记录的空间**（称为DisplacedMarkWord），然后使**用CAS把对象头中的内容替换为线程1存储的锁记录（DisplacedMarkWord）的地址；**

如果在线程1复制对象头的同时（在线程1CAS之前），线程2也准备获取锁，复制了对象头到线程2的锁记录空间中，但是在线程2CAS的时候，发现线程1已经把对象头换了，**线程2的CAS失败，那么线程2就尝试使用自旋锁来等待线程1释放锁。** 自旋锁简单来说就是让线程2在循环中不断CAS

但是如果自旋的时间太长也不行，因为自旋是要消耗CPU的，因此自旋的次数是有限制的，比如10次或者100次，**如果自旋次数到了线程1还没有释放锁，或者线程1还在执行，线程2还在自旋等待，这时又有一个线程3过来竞争这个锁对象，那么这个时候轻量级锁就会膨胀为重量级锁。重量级锁把除了拥有锁的线程都阻塞，防止CPU空转。**



## 几种锁的优缺点

![img](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/7.png)



## 用锁的最佳实践

**错误的加锁姿势1**

```java
synchronized (new Object())
```

每次调用创建的是不同的锁，相当于无锁

**错误的加锁姿势2**

```java
private Integer count;synchronized (count)
```

Integer，Boolean在实现了都用了享元模式，即值在一定范围内，对象是同一个。所以看似是用了不同的对象，其实用的是同一个对象。会导致一个锁被多个地方使用

[Java常量池详解，秒懂各种对象相等操作](http://mp.weixin.qq.com/s?__biz=MzIxMzk3Mjg5MQ==&mid=2247484247&idx=1&sn=85422ae7f853d283d9147240516263a9&chksm=97afe1a3a0d868b5a4faf23b60f07942bbc44b474832e638786b6c669122c5894738c00e49a9&scene=21#wechat_redirect)

**正确的加锁姿势**

```java
// 普通对象锁
private final Object lock = new Object();
// 静态对象锁
private static final Object lock = new Object();
```





## Lock接口的使用

既然有了synchronized，为啥还要提供Lock接口呢？也许你会说Lock接口比synchronized性能高。在jdk1.5之前确实如此，但是在jdk1.6之后，两者性能差不多了。直接来看Lock接口的定义，看看比synchronized多了哪些功能？

```java
public interface Lock {

    // 加锁
    void lock();
    // 能够响应中断
    void lockInterruptibly() throws InterruptedException;
    // 非阻塞获取锁
    boolean tryLock();
    // 非阻塞超时获取锁
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
    // 解锁
    void unlock();
    // 定义阻塞条件
    Condition newCondition();
}
```

可以看到Lock接口相比synchronized多了很多特性，详细解释一下方法

1. lock()方法，用来获取锁，如果锁被其他线程获得则进行等待，需要和unlock方法配合主动释放锁。发生异常时，不会主动释放锁，所以释放锁的操作放在finally块中
2. lockInterruptibly()方法，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
3. tryLock()方法，用来尝试获取锁，如果获取成功，则返回true。如果获取失败则返回false。也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待
4. tryLock(long time, TimeUnit unit)方法，和tryLock()类似。只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，就返回false。如果一开始拿到锁或者在等待期间内拿到了锁，则返回true
5. unlock()方法，解锁
6. newCondition()方法，定义条件

其余的应该都很好理解，演示一下lockInterruptibly()和newCondition()方法

**lockInterruptibly()方法**

```java
ReentrantLock myLock = new ReentrantLock();
// 先获取一次锁，让后续获取锁的操作阻塞
myLock.lock();
Thread thread = new Thread(() -> {
    try {
        // myLock.lock();
        myLock.lockInterruptibly();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // 当使用myLock.lockInterruptibly()时
        // 会抛出java.lang.InterruptedException，打印over
        // 使用myLock.lock()，一直阻塞获取锁，不会打印over
        System.out.println("over");
    }
});
thread.start();
TimeUnit.SECONDS.sleep(1);
thread.interrupt();
TimeUnit.SECONDS.sleep(100);
```

**Condition的使用**

synchronized与wait()和nitofy()/notifyAll()方法相结合可以实现等待/通知模型，ReentrantLock同样可以，但是需要借助Condition，且Condition有更好的灵活性，具体体现在

1. 一个Lock里面可以创建多个Condition实例，实现多路通知
2. notify()方法进行通知时，被通知的线程时Java虚拟机随机选择的，但是ReentrantLock结合Condition可以实现有选择性地通知

```java
public class WaitNotify {

    static ReentrantLock lock = new ReentrantLock();
    static Condition conditionA  = lock.newCondition();
    static Condition conditionB = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThreadA = new Thread(new WaitA(), "WaitThreadA");
        waitThreadA.start();
        Thread waitThreadB = new Thread(new WaitB(), "WaitThreadB");
        waitThreadB.start();
        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        try {
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    static class WaitA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " begin await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionA.await();
                System.out.println(Thread.currentThread() + " end await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class WaitB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " begin await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionB.await();
                System.out.println(Thread.currentThread() + " end await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
Thread[WaitThreadA,5,main] begin await @ 00:49:57
Thread[WaitThreadB,5,main] begin await @ 00:49:57
Thread[WaitThreadA,5,main] end await @ 00:49:59
```

WaitThreadB因为没有被通知，一直阻塞



**最后总结一波，synchronized和ReentrantLock的异同**

1. ReentrantLock支持非阻塞的方式获取锁，能够响应中断，而synchronized不行
2. ReentrantLock必须手动获取和释放锁，而synchronized不需要
3. ReentrantLock可以是公平锁或者非公平锁，而synchronized只能是非公平锁
4. synchronized在发生异常的时候，会自动释放线程占有的锁，而ReentrantLock在发生异常时，如果没有通过unlock去释放锁，很有可能造成死锁，因此需要在finally块中释放锁
5. synchronized和ReentrantLock都是可重入锁





# 3、创建线程的方式

### 1.继承Thread类

```java
class ThreadDemo1 extends Thread{

    @Override
    public  void run(){
        System.out.println("通过extendsThread来创建线程");
    }
}

public class ThreadDemo{
    public static void main(String[] args) {
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        threadDemo1.start();
    }

}

```



### 2.实现Runnable接口创建线程

```java
class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable接口创建线程");
    }
}

public class ThreadDemo02 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}

```



### 3.通过实现Callable接口创建线程

```java
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

```



### 4.通过创建线程池的方法创建线程

```java
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

```



# 4、线程间通信

### 1运用notify和wait实现线程的通信

```java
  
class ShareData//资源类
{
  private int number = 0;//初始值为零的一个变量
 
  public synchronized void increment() throws InterruptedException 
  {
     //判断
     while(number!=0) {
       this.wait();
     }
     //干活
     ++number;
     System.out.println(Thread.currentThread().getName()+" \t "+number);
     //通知
     this.notifyAll();;
  }
  
  public synchronized void decrement() throws InterruptedException 
  {
     //判断
     while(number!=1) {
       this.wait();
     }
     //干活
     --number;
     System.out.println(Thread.currentThread().getName()+" \t "+number);
     //通知
     this.notifyAll();
  }
}
 
/**
 * 
 * @Description:
 *现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。 
 * @author xialei
 *
 *  * 笔记：Java里面如何进行工程级别的多线程编写
 * 1 多线程变成模板（套路）-----上
 *     1.1  线程    操作    资源类  
 *     1.2  高内聚  低耦合
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知
 
 */
public class NotifyWaitDemo
{
  public static void main(String[] args)
  {
     ShareData sd = new ShareData();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.increment();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "A").start();
     
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.decrement();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "B").start();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.increment();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "C").start();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.decrement();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "D").start();
     
  }
}

```



![1584756735819](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/8.png)

### 2Condition实现

```java

class ShareData//资源类
{
  private int number = 0;//初始值为零的一个变量
 
  private Lock lock = new ReentrantLock();
  private Condition condition  = lock.newCondition(); 
   
  public  void increment() throws InterruptedException 
  {
     
      lock.lock();
         try {
          //判断
          while(number!=0) {
            condition.await();
          }
          //干活
          ++number;
          System.out.println(Thread.currentThread().getName()+" \t "+number);
          //通知
          condition.signalAll();
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       lock.unlock();
     }
     
  }
  
  
  public  void decrement() throws InterruptedException 
  {
      
      lock.lock();
         try {
          //判断
          while(number!=1) {
            condition.await();
          }
          //干活
          --number;
          System.out.println(Thread.currentThread().getName()+" \t "+number);
          //通知
          condition.signalAll();
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       lock.unlock();
     }
     
  }
  
  
public class NotifyWaitDemo
{
  public static void main(String[] args)
  {
     ShareData sd = new ShareData();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.increment();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "A").start();
     
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.decrement();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "B").start();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.increment();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "C").start();
     new Thread(() -> {
 
       for (int i = 1; i <= 10; i++) {
          try {
            sd.decrement();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
       }
     }, "D").start();
     
  }
}
 
```



# 5、线程间定制化调用通信

```java
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

```



# 6、NotSafeDemo

### 1证明集合不安全

java.util.ConcurrentModificationException

ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
并发修改异常

```java
List<String> list = new ArrayList<>();
for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
 
看ArrayList的源码
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
没有synchronized线程不安全

```



### 2解决方案

#### Vector

```java
看Vector的源码
public synchronized boolean add(E e) {
    modCount++;
    ensureCapacityHelper(elementCount + 1);
    elementData[elementCount++] = e;
    return true;
}
有synchronized线程安全
```



#### Collections

```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
```


Collections提供了方法synchronizedList保证list是同步线程安全的

那HashMap，HashSet是线程安全的吗？也不是
所以有同样的线程安全方法

![1584757469935](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/9.png)



#### 写时复制

```java
List<String> list = new CopyOnWriteArrayList<>();
```



```java
/**

 * Appends the specified element to the end of this list.
   *
 * @param e element to be appended to this list
 * @return {@code true} (as specified by {@link Collection#add})
   */
   public boolean add(E e) {
   final ReentrantLock lock = this.lock;
   lock.lock();
   try {
       Object[] elements = getArray();
       int len = elements.length;
       Object[] newElements = Arrays.copyOf(elements, len + 1);
       newElements[len] = e;
       setArray(newElements);
       return true;
   } finally {
       lock.unlock();
   }
   }

 

CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，然后向新的容器Object[] newElements里添加元素。
添加元素后，再将原容器的引用指向新的容器setArray(newElements)。
这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
```

 

# 7、多线程锁

```java
/*
* * 1 标准访问，先打印邮件还是短信
2 停4秒在邮件方法内，先打印邮件还是短信
3 普通的hello方法，是先打邮件还是hello
4 现在有两部手机，先打印邮件还是短信
5 两个静态同步方法，1部手机，先打印邮件还是短信
6 两个静态同步方法，2部手机，先打印邮件还是短信
7 1个静态同步方法，1个普通同步方法，1部手机，先打印邮件还是hello
8 1个静态同步方法，1个普通同步方法，2部手机，先打印邮件还是hello
*
* */
/*
        1、邮件
        2、邮件
        3、Hello
        4、短信
        5、邮件
        6、邮件
        7、hello
        8、hello
*/

import java.util.concurrent.TimeUnit;

class Phone{
    public static   synchronized void sendEmail() throws InterruptedException {
            //发邮件
       TimeUnit.SECONDS.sleep(4);
        System.out.println("----------sendEmail");
    }
    public static synchronized void sendSMS() throws InterruptedException {
            //发短信
        System.out.println("----------sendSMS");
    }
    public  void hello(){
        System.out.println("----------hello");
    }
}

public class Lock_8 {
    public static void main(String[] args) {
        Phone phone01 = new Phone();
        Phone phone02 = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone01.sendEmail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                phone02.hello();
            }
        },"B").start();
    }
}

```

### 8锁分析

```
A 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法

加个普通方法后发现和同步锁无关
换成两个对象后，不是同一把锁了，情况立刻变化。


synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
具体表现为以下3种形式。
对于普通同步方法，锁是当前实例对象。
对于静态同步方法，锁是当前类的Class对象。
对于同步方法块，锁是Synchonized括号里配置的对象

当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。

也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

所有的静态同步方法用的也是同一把锁——类对象本身，
这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
而不管是同一个实例对象的静态同步方法之间，
还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
```

 

# 8、ReentrantReadWriteLock读写锁

```java
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写" + key);
            //暂停一会儿线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写完了" + key);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public Object get(String key) {
        rwLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读完了" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {


    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }

    }


}



```



# 9、BlockingQueueDemo阻塞队列

### 种类分析

```
ArrayBlockingQueue：由数组结构组成的有界阻塞队列。

LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为integer.MAX_VALUE）阻塞队列。

PriorityBlockingQueue：支持优先级排序的无界阻塞队列。

DelayQueue：使用优先级队列实现的延迟无界阻塞队列。

SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列。

LinkedTransferQueue：由链表组成的无界阻塞队列。

LinkedBlockingDeque：由链表组成的双向阻塞队列。
```



### BlockingQueue核心方法

![1584760255089](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/10.png)

![1584760308414](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/11.png)



### 生产者消费者问题

```java

//使用reentrantLock实现
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

```

```java
//使用blockingQueue实现

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductConsumerTest {
    private static BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(5);

    public static void Product(){
        try {
            blockingQueue.put("product..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("produce..");
    }

    public static void Consumer(){
        String product=null;
        try {
             product = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer..");
    }

    public static void main(String[] args) {
        ProductConsumerTest productConsumerTest = new ProductConsumerTest();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                productConsumerTest.Product();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                productConsumerTest.Consumer();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 2; i++) {
                productConsumerTest.Product();
            }
        },"C").start();
    }
}

```



# 10、ThreadPool线程池

### 1为什么用线程池

线程池的优势：
线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。

它的主要特点为：线程复用;控制最大并发数;管理线程。

第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的销耗。
第二：提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行。
第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会销耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

### 2程池如何使用

#### 架构说明

```java
Java中的线程池是通过Executor框架实现的，该框架中用到了Executor，Executors，ExecutorService，ThreadPoolExecutor这几个类
```

<img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/12.png" alt="1584760706315" style="zoom: 80%;" />

#### 编码实现

```java
Executors.newFixedThreadPool(int)
```

执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定线程数的线程

```java
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}

newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的是LinkedBlockingQueue
```



```java
Executors.newSingleThreadExecutor()
```

一个任务一个任务的执行，一池一线程

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

newSingleThreadExecutor 创建的线程池corePoolSize和maximumPoolSize值都是1，它使用的是LinkedBlockingQueue
```





```java
Executors.newCachedThreadPool()
```

执行很多短期异步任务，线程池根据需要创建新线程，但在先前构建的线程可用时将重用它们。可扩容，遇强则强

```java
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
newCachedThreadPool创建的线程池将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，它使用的是SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。
```



### 3线程池几个重要参数

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

```
1、corePoolSize：线程池中的常驻核心线程数
2、maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
3、keepAliveTime：多余的空闲线程的存活时间，当前池中线程数量超过corePoolSize时，当空闲时间达到	keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
4、unit：keepAliveTime的单位 
5、workQueue：任务队列，被提交但尚未被执行的任务
6、threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
7、handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）时如何来拒绝请求执行的runnable的策略
```



### 4线程池底层工作原理

![1584761280416](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/13.png)

![1584761299339](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/14.png)

```
1、在创建了线程池后，开始等待请求。
2、当调用execute()方法添加一个请求任务时，线程池会做出如下判断：
  2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；
  2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列；
  2.3如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
  2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
4、当一个线程无事可做超过一定的时间（keepAliveTime）时，线程会判断：
    如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。
    所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小。
```



### 5线程池的拒绝策略

```
等待队列已经排满了，再也塞不下新任务了，同时，线程池中的max线程也达到了，无法继续为新任务服务。

这个是时候我们就需要拒绝策略机制合理的处理这个问题。
```

#### JDK内置的拒绝策略

```
AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加人队列中尝试再次提交当前任务。
DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略。

```

以上内置拒绝策略均实现了RejectedExecutionHandle接口



### 6自定义线程池

```java
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

```



# 11、Java8之流式计算



### 函数式接口

#### java内置核心四大函数式接口

![1584761910972](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/15.png)

```java
//R apply(T t);函数型接口，一个参数，一个返回值
Function<String,Integer> function = t ->{return t.length();};
System.out.println(function.apply("abcd"));

//boolean test(T t);断定型接口，一个参数，返回boolean
Predicate<String> predicate = t->{return t.startsWith("a");};
System.out.println(predicate.test("a"));

// void accept(T t);消费型接口，一个参数，没有返回值
Consumer<String> consumer = t->{
    System.out.println(t);
};
consumer.accept("javaXXXX");

//T get(); 供给型接口，无参数，有返回值
Supplier<String> supplier =()->{return UUID.randomUUID().toString();};
System.out.println(supplier.get());
```



### 特点

```
Stream 自己不会存储元素

Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。

Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
```



### 阶段

```
创建一个Stream：一个数据源（数组、集合）

中间操作：一个中间操作，处理数据源数据

终止操作：一个终止操作，执行中间操作链，产生结果
```

```java

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User
{
    private Integer id;
    private String  userName;
    private int     age;
}

/**
 * @create 2019-02-26 22:24
 *
 * 题目：请按照给出数据，找出同时满足
 *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *      最后只输出一个用户名字
 */
public class StreamDemo
{
    public static void main(String[] args)
    {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream().filter(p -> {
            return p.getId() % 2 == 0;
        }).filter(p -> {
            return p.getAge() > 24;
        }).map(f -> {
            return f.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


        //    R apply(T t);
        Function<String,Integer> function = t -> {return t.length();};
        System.out.println(function.apply("abc"));

        // boolean test(T t);
        Predicate<String> predicate = t -> {return t.startsWith("a");};
        System.out.println(predicate.test("a"));

        //void accept(T t);
        Consumer<String> consumer = t -> {System.out.println(t);};
        consumer.accept("java1018");


        //    T get();
        Supplier<String> supplier =  () -> {return UUID.randomUUID().toString();};
        System.out.println(supplier.get());;

    }
}
 
 

```



# 12、分支合并框架

### 原理

Fork：把一个复杂任务进行分拆，大事化小
Join：把分拆任务的结果进行合并

![1584762324013](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/16.png)

![1584762312935](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/17.png)



```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end - begin)<=ADJUST_VALUE){
           for(int i =begin;i <= end;i++){
                result = result + i;
           }
        }else{
            int middle = (begin + end)/2;
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result =  task01.join() + task02.join();
        }


        return result;
    }
}


/**
 * 分支合并例子
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        System.out.println(forkJoinTask.get());

        forkJoinPool.shutdown();
    }
}
 

```



# 13、异步回调

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回,update mysql ok");
        });
        completableFuture.get();
        Integer integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回,insert mysql ok");
            return 1024;
        }).whenComplete((t,u)->{
            System.out.println("****t:"+t);
            System.out.println("****u:"+u);
        }).exceptionally(f->{
            System.out.println("****exception:"+f.getMessage());
            return 44444;
        }).get();

    }
}

```



# 14、谈谈你对volatile的理解

### 1.volatile是Java虚拟机提供的轻量级的同步机制

```
1.1保证可见性
1.2不保证原子性
1.3禁止指令重排
```

### 2.JMM你谈谈

JMM(Java内存模型Java Memory Model,简称JMM)本身是一种抽象的概念 并不真实存在,它描述的是一组规则或规范通过规范定制了程序中各个变量(包括实例字段,静态字段和构成数组对象的元素)的访问方式.
JMM关于同步规定:

```
1.线程解锁前,必须把共享变量的值刷新回主内存
2.线程加锁前,必须读取主内存的最新值到自己的工作内存
3.加锁解锁是同一把锁
```

由于JVM运行程序的实体是线程,而每个线程创建时JVM都会为其创建一个工作内存(有些地方成为栈空间),工作内存是每个线程的私有数据区域,而Java内存模型中规定所有变量都存储在主内存,主内存是共享内存区域,所有线程都可访问,但线程对变量的操作(读取赋值等)必须在工作内存中进行,首先要将变量从主内存拷贝到自己的工作空间,然后对变量进行操作,操作完成再将变量写回主内存,不能直接操作主内存中的变量,各个线程中的工作内存储存着主内存中的变量副本拷贝,因此不同的线程无法访问对方的工作内存,此案成间的通讯(传值) 必须通过主内存来完成,其简要访问过程如下图:

![1584762600847](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/18.png)

#### 2.1可见性

通过前面对JMM的介绍,我们知道各个线程对主内存中共享变量的操作都是各个线程各自拷贝到自己的工作内存操作后再写回主内存中的.这就可能存在一个线程AAA修改了共享变量X的值还未写回主内存中时 ,另外一个线程BBB又对内存中的一个共享变量X进行操作,但此时A线程工作内存中的共享比那里X对线程B来说并不不可见.这种工作内存与主内存同步延迟现象就造成了可见性问题.

#### 2.2原子性

number++在多线程下是非线程安全的,如何不加synchronized解决?

![1584762751320](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/19.png)

#### 2.3VolatileDemo代码演示可见性+原子性代码

```java

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
//    int num = 0;
    volatile int num = 0;

    public synchronized void addToSixty() {
        this.num = 60;
    }

    public  void addSelf(){
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void atomicAddSelf(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1验证volatile的可见性
 *  1.1 如果int num = 0，number变量没有添加volatile关键字修饰
 * 1.2 添加了volatile，可以解决可见性
 *
 * 2.验证volatile不保证原子性
 *  2.1 原子性指的是什么
 *      不可分割、完整性，即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整，要么同时成功，要么同时失败
 *  2.2 如何解决原子性
 *      2.2.1 方法加synchronized
 *      2.2.2 Atomic
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {
//        visibilityByVolatile();//验证volatile的可见性
        atomicByVolatile();//验证volatile不保证原子性
    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
     */
    public static void visibilityByVolatile() {
        MyData myData = new MyData();

        //第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                //线程暂停3s
                TimeUnit.SECONDS.sleep(3);
                myData.addToSixty();
                System.out.println(Thread.currentThread().getName() + "\t update value:" + myData.num);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "thread1").start();
        //第二个线程是main线程
        while (myData.num == 0) {
            //如果myData的num一直为零，main线程一直在这里循环
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, num value is " + myData.num);
    }

    /**
     * volatile不保证原子性
     * 以及使用Atomic保证原子性
     */
    public static void atomicByVolatile(){
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i++){
            new Thread(() ->{
                for(int j = 1; j <= 1000; j++){
                    myData.addSelf();
                  myData.atomicAddSelf();
                }
            },"Thread "+i).start();
        }
        //等待上面的线程都计算完成后，再用main线程取得最终结果值
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally num value is "+myData.num);
        System.out.println(Thread.currentThread().getName()+"\t finally atomicnum value is "+myData.atomicInteger);
    }
}



```

#### 2.4有序性

计算机在执行程序时,为了提高性能,编译器和处理器常常会做指令重排,一把分为以下3中

![1584762866510](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/20.png)


单线程环境里面确保程序最终执行结果和代码顺序执行的结果一致.
处理器在进行重新排序是必须要考虑指令之间的数据依赖性

多线程环境中线程交替执行,由于编译器优化重排的存在,两个线程使用的变量能否保持一致性是无法确定的,结果无法预测

![1584762923014](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/21.png)

![1584762945403](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/22.png)



### 3.你在哪些地方用到过volatile?

#### 3.1 单例模式DCL代码

```java
public class SingletonDemo {

    private static volatile SingletonDemo instance=null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 构造方法");
    }

    /**
     * 双重检测机制
     * @return
     */
    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <=10; i++) {
            new Thread(() ->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
 

```

#### 3.2代理模式volatile分析

```java
DCL(双端检锁) 机制不一定线程安全,原因是有指令重排的存在,加入volatile可以禁止指令重排
  原因在于某一个线程在执行到第一次检测,读取到的instance不为null时,instance的引用对象可能没有完成初始化.
instance=new SingletonDem(); 可以分为以下步骤(伪代码)
 
memory=allocate();//1.分配对象内存空间
instance(memory);//2.初始化对象
instance=memory;//3.设置instance的指向刚分配的内存地址,此时instance!=null 
 
步骤2和步骤3不存在数据依赖关系.而且无论重排前还是重排后程序执行的结果在单线程中并没有改变,因此这种重排优化是允许的.
memory=allocate();//1.分配对象内存空间
instance=memory;//3.设置instance的指向刚分配的内存地址,此时instance!=null 但对象还没有初始化完.
instance(memory);//2.初始化对象
但是指令重排只会保证串行语义的执行一致性(单线程) 并不会关心多线程间的语义一致性
所以当一条线程访问instance不为null时,由于instance实例未必完成初始化,也就造成了线程安全问题.
 
```



# 15、CAS你知道吗

### 1.比较并交换

```java
/**
 * Description
 *
 * @author veliger@163.com
 * @version 1.0
 * @date 2019-04-12 9:57
 * 1.什么是CAS ? ===> compareAndSet
 *  比较并交换
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014)+"\t current"+atomicInteger.get());
    }
}
 

```

### 2.CAS底层原理?如果知道,谈谈你对UnSafe的理解

#### atomicInteger.getAndIncrement();

```java
atomicInteger.getAndIncrement()方法的源代码:
/**
 * Atomically increments by one the current value.
 *
 * @return the previous value
 */
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
印出来一个问题:UnSafe类是什么?
```



#### UnSafe

![1584764632895](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/23.png)

1.UnSafe
 是CAS的核心类 由于Java 方法无法直接访问底层 ,需要通过本地(native)方法来访问,UnSafe相当于一个后面,基于该类可以直接操作特额定的内存数据.UnSafe类在于sun.misc包中,其内部方法操作可以向C的指针一样直接操作内存,因为Java中CAS操作的助兴依赖于UNSafe类的方法.
注意UnSafe类中所有的方法都是native修饰的,也就是说UnSafe类中的方法都是直接调用操作底层资源执行响应的任务
 2.变量ValueOffset,便是该变量在内存中的偏移地址,因为UnSafe就是根据内存偏移地址获取数据的

![1584764720867](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/24.png)

 3.变量value和volatile修饰,保证了多线程之间的可见性.



#### CAS是什么

CAS的全称为Compare-And-Swap ,它是一条CPU并发原语.
它的功能是判断内存某个位置的值是否为预期值,如果是则更新为新的值,这个过程是原子的.

CAS并发原语提现在Java语言中就是sun.miscUnSaffe类中的各个方法.调用UnSafe类中的CAS方法,JVM会帮我实现CAS汇编指令.这是一种完全依赖于硬件 功能,通过它实现了原子操作,再次强调,由于CAS是一种系统原语,原语属于操作系统用于范畴,是由若干条指令组成,用于完成某个功能的一个过程,并且原语的执行必须是连续的,在执行过程中不允许中断,也即是说CAS是一条原子指令,不会造成所谓的数据不一致的问题.

![1584764799939](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/25.png)

![1584764824000](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/26.png)

![1584764847929](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/27.png)

##### unSafe.getAndIncrement

```
var1 AtomicInteger对象本身.
var2 该对象值的引用地址
var4 需要变动的数值
var5 是用过var1 var2找出内存中绅士的值
用该对象当前的值与var5比较
如果相同,更新var5的值并且返回true
如果不同,继续取值然后比较,直到更新完成
```

![1584764924511](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/28.png)

```
  假设线程A和线程B两个线程同时执行getAndAddInt操作(分别在不同的CPU上):

1.AtomicInteger里面的value原始值为3,即主内存中AtomicInteger的value为3,根据JMM模型,线程A和线程B各自持有一份值为3的value的副本分别到各自的工作内存.

2.线程A通过getIntVolatile(var1,var2) 拿到value值3,这是线程A被挂起.

3.线程B也通过getIntVolatile(var1,var2) 拿到value值3,此时刚好线程B没有被挂起并执行compareAndSwapInt方法比较内存中的值也是3 成功修改内存的值为4 线程B打完收工 一切OK.

 4.这是线程A恢复,执行compareAndSwapInt方法比较,发现自己手里的数值和内存中的数字4不一致,说明该值已经被其他线程抢先一步修改了,那A线程修改失败,只能重新来一遍了.

 5.线程A重新获取value值,因为变量value是volatile修饰,所以其他线程对他的修改,线程A总是能够看到,线程A继续执行compareAndSwapInt方法进行比较替换,直到成功.
```



##### 底层汇编

![1584765008820](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/29.png)

##### 简单版小总结

![1584765047108](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/30.png)



### 3.CAS缺点

##### 循环时间长开销很大

![1584765120957](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/31.png)

##### 只能保证一个共享变量的原子性

![1584765171139](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/32.png)

##### 引出来ABA问题???

往下看。。。。。。。。



# 16、原子类AtomicInteger的ABA问题谈谈?原子更新引用知道吗

### ABA问题的产生

![1584765306896](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/33.png)

### 原子引用

```java
/**
 * Description:
 *
 * @author veliger@163.com
 * @date 2019-04-12 21:23
 **/
@Getter@Setter@AllArgsConstructor@ToString
class User{
    private String name;
    private int age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zs", 22);
        User ls = new User("ls", 22);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        System.out.println(userAtomicReference.compareAndSet(zs, ls)+"\t"+userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(zs, ls)+"\t"+userAtomicReference.get().toString());
    }
}
 

```



### 时间戳原子引用

```java
/**
 * Description: ABA问题的解决
 *
 * @author veliger@163.com
 * @date 2019-04-12 21:30
 **/
public class ABADemo {
    private static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("===以下是ABA问题的产生===");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            //先暂停1秒 保证完成ABA
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"t2").start();
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("===以下是ABA问题的解决===");

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp+"\t值是"+stampedReference.getReference());
            //暂停1秒钟t3线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第2次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第3次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
        },"t3").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp+"\t值是"+stampedReference.getReference());
            //保证线程3完成1次ABA
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t 修改成功否"+result+"\t最新版本号"+stampedReference.getStamp());
            System.out.println("最新的值\t"+stampedReference.getReference());
        },"t4").start();
    }
 

```



# 17、公平锁/非公平锁/可重入锁/递归锁/自旋锁谈谈你的理解?请手写一个自旋锁

### 公平锁和非公平锁

##### 是什么

```
公平锁
    是指多个线程按照申请锁的顺序来获取锁类似排队打饭 先来后到
非公平锁
    是指在多线程获取锁的顺序并不是按照申请锁的顺序,有可能后申请的线程比先申请的线程优先获取到锁,在高并发的情况下,有可能造成优先级反转或者饥饿现象
```

##### 两者的区别

公平锁/非公平锁
  并发包ReentrantLock的创建可以指定构造函数的boolean类型来得到公平锁或者非公平锁 默认是非公平锁

![1584766138604](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/34.png)



### 可重入锁(又名递归锁)

![1584766191673](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/35.png)

##### ReentrantLock/synchronized就是一个典型的可重入锁

可重入锁最大的作用就是避免死锁

```java

class Phone{
    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendSms");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendEmail");
    }

}
/**
 * Description:
 *  可重入锁(也叫做递归锁)
 *  指的是同一先生外层函数获得锁后,内层敌对函数任然能获取该锁的代码
 *  在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 *
 *  也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 *
 * @author veliger@163.com
 * @date 2019-04-12 23:36
 **/
public class ReenterLockDemo {
    /**
     * t1 sendSms
     * t1 sendEmail
     * t2 sendSms
     * t2 sendEmail
     * @param args
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
 
 

```

### 自旋锁

![1584766338692](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/36.png)

```java
package JUC.CAS;

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
 
 

```



### 独占锁(写)/共享锁(读)/互斥锁

![1584766524618](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/37.png)

```java
/**
 * 资源类
 */
class MyCaChe {
    /**
     * 保证可见性
     */
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 写
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入" + key);
            //模拟网络延时
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t正在完成");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读
     *
     * @param key
     */
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取");
            //模拟网络延时
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t正在完成" + result);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void clearCaChe() {
        map.clear();
    }

}

/**
 * Description:
 * 多个线程同时操作 一个资源类没有任何问题 所以为了满足并发量
 * 读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源来  就不应该有其他线程可以对资源进行读或写
 * <p>
 * 小总结:
 * 读 读能共存
 * 读 写不能共存
 * 写 写不能共存
 * 写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许被分割 被打断
 *
 * @author veliger@163.com
 * @date 2019-04-13 0:45
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCaChe myCaChe = new MyCaChe();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCaChe.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCaChe.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}
 

```



# 18、JUC强大的辅助类讲解

### CountDownLatch减少计数

 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。

 

```java

import java.util.concurrent.CountDownLatch;
 
 
/**
 * 
 * @Description:
 *  *让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * 
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 * 
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * 
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干 
 */
public class CountDownLatchDemo
{
   public static void main(String[] args) throws InterruptedException
   {
         CountDownLatch countDownLatch = new CountDownLatch(6);
       
       for (int i = 1; i <=6; i++) //6个上自习的同学，各自离开教室的时间不一致
       {
          new Thread(() -> {
              System.out.println(Thread.currentThread().getName()+"\t 号同学离开教室");
              countDownLatch.countDown();
          }, String.valueOf(i)).start();
       }
       countDownLatch.await();
       System.out.println(Thread.currentThread().getName()+"\t****** 班长关门走人，main线程是班长");
          
   }
 
 
}
 

```



### CyclicBarrier循环栅栏

 * CyclicBarrier的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。线程进入屏障通过CyclicBarrier的await()方法。

```java
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
 
/**
 * 
 *
 * CyclicBarrier
 * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过CyclicBarrier的await()方法。
 * 
 * 集齐7颗龙珠就可以召唤神龙
 */
public class CyclicBarrierDemo
{
  private static final int NUMBER = 7;
  
  public static void main(String[] args)
  {
     //CyclicBarrier(int parties, Runnable barrierAction) 
     
     CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, ()->{System.out.println("*****集齐7颗龙珠就可以召唤神龙");}) ;
     
     for (int i = 1; i <= 7; i++) {
       new Thread(() -> {
          try {
            System.out.println(Thread.currentThread().getName()+"\t 星龙珠被收集 ");
            cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
       
       }, String.valueOf(i)).start();
     }
     
 
  }
}
```



### Semaphore信号量

 在信号量上我们定义两种操作：
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。

```java
 import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
 
/**
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * 
 * 在信号量上我们定义两种操作：
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 *             要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * 
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 */
public class SemaphoreDemo
{
  public static void main(String[] args)
  {
     Semaphore semaphore = new Semaphore(3);//模拟3个停车位
     
     for (int i = 1; i <=6; i++) //模拟6部汽车
     {
       new Thread(() -> {
          try 
          {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
            System.out.println(Thread.currentThread().getName()+"\t------- 离开");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }finally {
            semaphore.release();
          }
       }, String.valueOf(i)).start();
     }
     
  }
}
 

```



# 19、死锁编码及定位分析

![1584767714068](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/38.png)

```java
//产生死锁代码
class HoldThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockA + "尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockB + "尝试获得" + lockA);
            }
        }
    }
}

/**
 * Description:
 * 死锁是指两个或者以上的进程在执行过程中,
 * 因争夺资源而造成的一种相互等待的现象,
 * 若无外力干涉那他们都将无法推进下去
 *
 * @author veliger@163.com
 * @date 2019-04-14 0:05
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldThread(lockB, lockA), "threadBBB").start();
    }
}
 

```

### 解决

##### jps命令定位进程编号

![1584767904166](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/39.png)

##### jstack找到死锁查看

![1584767969170](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/40.png)



# 20、同步器 AQS

AQS 的全称为：AbstractQueuedSynchronizer，这个类在 java.util.concurrent.locks 包下面。AQS 是一个用来构建锁和同步器的框架，使用 AQS 能简单且高效地构造出应用广泛的大量的同步器，比如：我们提到的 ReentrantLock，Semaphore，其他的诸如ReentrantReadWriteLock，SynchronousQueue，FutureTask 等等皆是基于 AQS 的。当然，我们自己也能利用 AQS 非常轻松容易地构造出符合我们自己需求的同步器。

### AQS 的原理是什么？

AQS 核心思想是：如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制 AQS 是用 CLH 队列锁实现的，即将暂时获取不到锁的线程加入到队列中。

![1584768448795](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/41.png)

```
CLH队列：

CLH(Craig, Landin, and Hagersten)队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。AQS 是将每条请求共享资源的线程封装成一个 CLH 锁队列的一个结点（Node）来实现锁的分配。
```

![1584769253419](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/42.png)

AQS的主要思想就是维护一个单一的状态信息state，通过getState()，setState()，compareAndSetState()方法来修改其值。对于AQS来说，线程同步的关键就是对状态值state进行操作，根据操作state的方式，又分为独占锁和共享锁；在独占方式下的获取和释放资源的方法为：acquire()和release()，在共享方式下的获取和释放资源的方法为：acquireShared()和releaseShared()。

 

使用独占方式获取的资源是与具体线程绑定的，就是如果一个线程获取了资源，就会标记是这个线程获取到了，其他线程再尝试操作state时会发现当前资源不是自己持有的，就会在获取失败后阻塞，然后该线程会被包装为一个Node节点，加入到AQS阻塞队列当中，直到持有state的线程释放资源，该线程会被唤醒继续争取资源。比如独占锁ReentrantLock的实现，当一个线程获取了ReentrantLock后，在AQS内部会使用CAS操作把state状态值从0改为1，然后设置当前锁的持有者为当前线程，当该线程再次获取锁的时候发现它就是锁的持有者，则会把状态值从1改为2，也就是设置了重入次数为2，当另一个线程获取锁时发现自己并不是锁的持有者就会被放入AQS阻塞队列后挂起。



# 21、谈谈对 ThreadLocal 的理解？

为共享变量在每个线程中创建一个副本，每个线程可以访问自己内部的副本变量

内部源码：

里面会维护一个ThreadLocalMap对象，在ThreadLocalMap中有Entry对象,其中key指的是当前ThreadLocal实例，value指的是ThreadLocal对应的值，其中Entry继承WeakReference，将ThreadLocal对象变成弱引用对象，这样做的好处是在线程销毁的时候，对应的实体就会被回收，不会存在内存泄漏，因为弱引用对象在垃圾回收机制一运行就会被销毁。

![1584770220163](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/JUC/43.png)

```
1.get() 方法是用来获取 ThreadLocal 在当前线程中保存的变量副本；

2.set() 用来设置当前线程中变量的副本；

3.remove() 用来移除当前线程中变量的副本；

4.initialValue() 是一个 protected 方法，一般是用来在使用时进行重写的，如果在没有 set 的时候就调用 get，会调用 initialValue 方法初始化内容。
```

### 在哪些场景下会使用到 ThreadLocal？

获取数据库连接，这是我们刚开始学习jdbc的用法，简单使用是没有问题的。但还是有两个问题无法解决：

高并发的情况下，可能多个线程同时获取到数据库连接，就会产生并发的问题。我们想到可以使用同步锁来处理，保证只有一个线程获取到数据库连接，但这样毫无疑问效率非常低。

如果有多条sql需要执行，需要用同一个connection对象。那就需要在多个方法中传递这个connection对象，方法传递会有点麻烦。
那么有没有更好的方法呢，就是本文讲到的ThreadLocal了。我们稍微修改一下：

```java
public class ConnectionManager {
    private static String url ="***";

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public static Connection openConnection() throws SQLException {
        Connection connect = threadLocal.get();
        if(connect == null){
            connect = DriverManager.getConnection(url);
            threadLocal.set(connect);
        }
        return connect;
    }
}
```

使用了ThreadLocal后，由于线程访问的都是自己的Connection对象，所以就不存在高并发的问题。同时还解决了事务的问题，同一个事务里，Connection对象不需要传来传去，直接用ThreadLocal获取就可以了。
所以我们总结一下ThreadLocal的两点好处：

1.每个线程有自己的ThreadLocalMap对象，线程各自访问各自的，提供了保存对象到线程的方法。

2.减少了线程间传递参数的麻烦。

基于这样两点好处，我们在获取数据库连接，获取session，获取token信息等场景下使用ThreadLocal会很方便。
