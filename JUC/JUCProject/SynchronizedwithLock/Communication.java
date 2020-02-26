package JUCProject.SynchronizedwithLock;

/*
* 运用notify和wait实现线程的通信
* */

class Number implements Runnable{
    private int n=1;
    @Override
    public void run() {
        while (true){
            synchronized(this){
                notify();
                if (n<=100){
                    System.out.println(Thread.currentThread().getName()+":"+n);
                    n++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}

public class Communication {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread01 = new Thread(number);
        Thread thread02 = new Thread(number);
        thread01.start();
        thread02.start();

    }
}
