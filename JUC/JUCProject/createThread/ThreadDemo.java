package JUCProject.createThread;

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
