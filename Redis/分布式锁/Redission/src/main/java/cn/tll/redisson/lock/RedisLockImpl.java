package cn.tll.redisson.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author tanglilei
 * @since 2020/7/20 14:16
 */
@Service
public class RedisLockImpl implements RedisLock {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    private static ThreadLocal<Map<String, Integer>> LOCKS = ThreadLocal.withInitial(HashMap::new);


    private ThreadLocal<String> threadLocal=new ThreadLocal<>();

    @Override
    public Boolean tryLock(String key, Long timeout, TimeUnit unit) {
        String threadName=Thread.currentThread().getName();
        Map<String, Integer> counts = LOCKS.get();
        Boolean lock=false;
        String uuid="";
        if (threadLocal.get()==null){
            uuid= UUID.randomUUID().toString();
            threadLocal.set(uuid);
            lock = stringRedisTemplate.opsForValue().setIfAbsent(key, uuid,30,TimeUnit.SECONDS);
            if (!lock){
                while (true){
                    lock=stringRedisTemplate.opsForValue().setIfAbsent(key, uuid,30,TimeUnit.SECONDS);
                    if (lock) break;
                }
            }
            if (counts.containsKey(threadName)){
                counts.put(threadName,counts.get(threadName)+1);
                System.out.println("可重入锁2："+counts.get(threadName));

            }else {
                System.out.println("可重入锁1："+counts.get(threadName));
                counts.put(threadName,1);
            }

            //异步实现续命操作
            new Thread(()->{
                while (true){
                    while (true) {
                        if (stringRedisTemplate.opsForValue().get(key)!=null){
                            stringRedisTemplate.expire(key,30,TimeUnit.SECONDS);
                            System.out.println("续命成功");
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }else {
            if (counts.containsKey(threadName)){
                counts.put(threadName,counts.get(threadName)+1);
                System.out.println("可重入锁2："+counts.get(threadName));

            }else {
                System.out.println("可重入锁1："+counts.get(threadName));
                counts.put(threadName,1);
            }
            lock=true;
        }


        return lock;

    }



    @Override
    public void releaseLock(String key) {
        if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){
            Map<String, Integer> counts = LOCKS.get();
            String threadName=Thread.currentThread().getName();
            if (counts.getOrDefault(threadName,0)<=1){
                counts.remove(threadName);
                stringRedisTemplate.delete(key);
            }else {
                counts.put(threadName,counts.get(threadName)-1);
            }
        }
    }
}