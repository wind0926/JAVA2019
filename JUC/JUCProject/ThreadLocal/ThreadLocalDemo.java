package JUCProject.ThreadLocal;

/*
* 为共享变量在每个线程中创建一个副本，每个线程可以访问自己内部的副本变量
*
*
* */
public class ThreadLocalDemo {
    private static Integer num=0;
    private static ThreadLocal<Integer> numLocal=new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
//                num+=5;
                int x=numLocal.get().intValue();
                x+=5;
                numLocal.set(x);
                System.out.println(Thread.currentThread().getName()+":"+numLocal.get());
            },"Thread-"+i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
