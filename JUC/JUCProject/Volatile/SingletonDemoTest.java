package JUCProject.Volatile;

public class SingletonDemoTest {
    private static volatile SingletonDemoTest instance=null;

    private SingletonDemoTest() {
        System.out.println(Thread.currentThread().getName()+"\t我是构造方法");
    }
    private static SingletonDemoTest getInstance(){
        if (instance==null){
            synchronized (SingletonDemoTest.class){
                if (instance==null){
                     instance=new SingletonDemoTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemoTest.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
