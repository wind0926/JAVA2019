package JUCProject.createThread;

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
