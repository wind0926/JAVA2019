package JUCProject.Volatile;
/**/
public class SingletonDemo {
    private volatile static SingletonDemo instance=null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }

    public static SingletonDemo getInstance(){
        /**
         * 双重检测机制
         */
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        //构造方法只会被执行一次
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());

        //构造方法会在一些情况下执行多次
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, "Thread " + i).start();
        }

    }
}
