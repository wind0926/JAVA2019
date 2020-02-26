package JUCProject.CAS;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public void put(String key,Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"\t正在读取：");
        TimeUnit.SECONDS.sleep(1);
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t读取完成"+result);
    }
}


public class ReadWriteLockTest {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 5; i++) {
            final int tempInt=i;
            new Thread(()->{
                try {
                    cache.put(tempInt+"",tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt=i;
            new Thread(()->{
                try {
                    cache.get(tempInt+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
