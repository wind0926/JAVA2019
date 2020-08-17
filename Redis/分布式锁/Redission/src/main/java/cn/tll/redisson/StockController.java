package cn.tll.redisson;

//import org.redisson.Redisson;
import cn.tll.redisson.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.concurrent.TimeUnit;

/**
 * @author tanglilei
 * @since 2020/7/17 11:53
 */
@Controller
@RequestMapping("/stock")
public class StockController {

/*    @Autowired
    private Redisson redisson;*/

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    protected static final String product = "product";

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @RequestMapping("test")
    public String tets() {
        //如果键不存在则新增,存在则不改变已经有的值。
        boolean absentBoolean = stringRedisTemplate.opsForValue().setIfAbsent("absentKey", "fff", 10, TimeUnit.SECONDS);

        System.out.println("通过setIfAbsent(K key, V value)方法判断变量值absentValue是否存在:" + absentBoolean);

        if (absentBoolean) {

            String absentValue = stringRedisTemplate.opsForValue().get("absentKey") + "";

            System.out.print(",不存在，则新增后的值是:" + absentValue);

            boolean existBoolean = stringRedisTemplate.opsForValue().setIfAbsent("absentKey", "eee");

            System.out.print(",再次调用setIfAbsent(K key, V value)判断absentValue是否存在并重新赋值:" + existBoolean);
            absentValue = stringRedisTemplate.opsForValue().get("absentKey") + "";
            System.out.println("111111111" + absentValue);

            if (!existBoolean) {

                absentValue = stringRedisTemplate.opsForValue().get("absentKey") + "";

                System.out.print("如果存在,则重新赋值后的absentValue变量的值是:" + absentValue);
            }
        }
        return "false";
    }




/*    @RequestMapping("/reduce_stock")
    public String reduceStock(){
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }

        return "end";
    }*/




/*    @RequestMapping("/reduce_stock")
    public String reduceStock(){
        synchronized (product){
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }
        }

        return "end";
    }*/




/*    @RequestMapping("/reduce_stock")
    public String reduceStock(){
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "tll");//setnx
        if (!lock){
            return "error";
        }
        int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock>0){
            stock=stock-1;
            //todo handler exception
            *//*
     * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁
     *
     * *//*
            //更新库存
            stringRedisTemplate.opsForValue().set("stock",stock+"");
            System.out.println("扣减成功，库存stock："+stock);

        }else {
            System.out.println("扣减失败，库存不足");//下单失败
        }
        stringRedisTemplate.delete(product);
        //可以保证原子性
        return "end";
    }*/



/*    @RequestMapping("/reduce_stock")
    public String reduceStock(){
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "tll");//setnx
        if (!lock){
            return "error";
        }
        try {
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //todo handler exception

             *//*    * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁，加上tryfinally，就可以解决
     *
     * *//*
                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }
        } finally {
            stringRedisTemplate.delete(product);

        }
        //可以保证原子性
        return "end";
    }*/



/*    @RequestMapping("/reduce_stock")
    public String reduceStock(){
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "lock",30,TimeUnit.SECONDS);//setnx
//        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
        *//*
        * 如果在执行到这里因为服务器宕机，依然释放不了，可以设置过期时间来解决,即使停电了，在过期时间后，依然会释放锁
        *
        * 但是。。。。。。。。。
        *  Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "tll");//setnx
        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
        * 这两条命令不保证原子性
        *
        * 可以写在一起
        *
        *         Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "lock",30,TimeUnit.SECONDS);//setnx
        * 但是。。。。。。。。
        * 需要考虑业务逻辑处理如果大于过期设置时间怎么办？
        * 假如第一个线程拿到锁并且业务逻辑处理大于设置过期时间，这个时候第二个线程拿到锁，在第二个线程执行业务逻辑的时候，第一个线程把锁释放了，这个时候第三个线程又可以拿到锁了，
        * 这样又不能保证原子性
        *
        * *//*
        if (!lock){
            return "error";
        }
        try {
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //todo handler exception
                *//*
     * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁，加上tryfinally，就可以解决
     *
     * *//*
                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }
        } finally {
            stringRedisTemplate.delete(product);

        }
        //可以保证原子性
        return "end";
    }*/



  /*  @RequestMapping("/reduce_stock")
    public String reduceStock(){
        String uuid= UUID.randomUUID().toString();//32位16进制的数
//        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid,30,TimeUnit.SECONDS);//setnx
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid);//setnx
        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
        *//*
        * 如果在执行到这里因为服务器宕机，依然释放不了，可以设置过期时间来解决,即使停电了，在过期时间后，依然会释放锁
        *
        * 但是。。。。。。。。。
        *  Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "tll");//setnx
        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
        * 这两条命令不保证原子性
        *
        * 可以写在一起
        *
        *         Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(product, "lock",30,TimeUnit.SECONDS);//setnx
        * 但是。。。。。。。。
        * 需要考虑业务逻辑处理如果大于过期设置时间怎么办？
        * 假如第一个线程拿到锁并且业务逻辑处理大于设置过期时间，这个时候第二个线程拿到锁，在第二个线程执行业务逻辑的时候，第一个线程把锁释放了，这个时候第三个线程又可以拿到锁了，
        * 这样又不能保证原子性
        *
        * 可以设置lock的value值为uuid
        *
        * //但是 这个uuid还是共享的，当第一个线程执行超过过去时间，第二个线程就会拿到锁，会导致错误删除第一个线程的锁,
        * 需要设置ThreadLocal,为线程创建一个变量副本
        *
        * *//*
        if (!lock){
            return "err";
        }
        try {
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //todo handler exception
                *//*
     * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁，加上tryfinally，就可以解决
     *
     * *//*
                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }
        } finally {
            if (uuid.equals(stringRedisTemplate.opsForValue().get(product))){
                stringRedisTemplate.delete(product);
            }

        }
        //可以保证原子性
        return "end";
    }*/



/*
    @RequestMapping("/reduce_stock")
    public String reduceStock(){
        Boolean lock=false;
        String uuid="";
        if (threadLocal.get()==null){
             uuid= UUID.randomUUID().toString();//32位16进制的数
            threadLocal.set(uuid);
             lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid,20,TimeUnit.SECONDS);//setnx
//             lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid);//setnx
//            stringRedisTemplate.expire(product,10, TimeUnit.SECONDS);
        }

//        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);

   */
    /*     *
     *
     * //但是 这个uuid还是共享的，当第一个线程执行超过过去时间，第二个线程就会拿到锁，会导致错误删除第一个线程的锁,
     * 需要设置ThreadLocal,为线程创建一个变量副本
     *
     * *//*

        if (!lock){
            return "err";
        }
        try {
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //todo handler exception
         */
    /*
     * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁，加上tryfinally，就可以解决
     *
     * *//*

                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("扣减成功，库存stock："+stock);

            }else {
                System.out.println("扣减失败，库存不足");//下单失败
            }
        } finally {
            System.out.println("threadlocal的值"+threadLocal.get());
            System.out.println("redis中product的value"+stringRedisTemplate.opsForValue().get(product));
            if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(product))){
                stringRedisTemplate.delete(product);
            }

        }
        //可以保证原子性
        return "end";
    }
*/

    @RequestMapping("thread")
    public String thread() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    reduceStock();
//                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        return "tll";
    }

    /*    @RequestMapping("/reduce_stock")
        public String reduceStock() throws InterruptedException {
            Boolean lock=false;
            String uuid="";
    //        System.out.println("threadlocal"+threadLocal.get());
            if (threadLocal.get()==null){
                uuid= UUID.randomUUID().toString();//32位16进制的数
                threadLocal.set(uuid);
                lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid,40,TimeUnit.SECONDS);//setnx
    *//*            lock = stringRedisTemplate.opsForValue().setIfAbsent(product, uuid);//setnx
            stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);*//*
            System.out.println(uuid+":"+lock);
        }

//        stringRedisTemplate.expire(product,30, TimeUnit.SECONDS);
   *//*      *
     *
     * //但是 这个uuid还是共享的，当第一个线程执行超过过去时间，第二个线程就会拿到锁，会导致错误删除第一个线程的锁,
     * 需要设置ThreadLocal,为线程创建一个变量副本
     *
     *
     * 还是没解决执行业务超时的问题，因此需要续命操作
     *因此开启一个异步线程，每隔一段时间检测当前任务是否执行完毕，如果没有执行完毕，就进行续命操作
     * stringRedisTemplate.expire(product,30,TimeUnit.SECONDS);
     *
     * *//*

        if (!lock){
            System.out.println(uuid+":reduceStock");
            do{
                Thread.sleep(1000);
                lock=stringRedisTemplate.opsForValue().setIfAbsent(product, uuid,40,TimeUnit.SECONDS);//setnx
            }while (!lock);
        }
        try {
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock=stock-1;
                //todo handler exception
                *//* * 在这里如果抛出异常，就不会delete，导致下一个线程获取不到锁，加上tryfinally，就可以解决
     *
     * *//*

                //更新库存
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println(uuid+":扣减成功，库存stock："+stock);

            }else {
                System.out.println(uuid+":扣减失败，库存不足");//下单失败
            }
        } finally {
            if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(product))){
                stringRedisTemplate.delete(product);
                System.out.println(uuid+":删除product");
            }

        }
        //可以保证原子性
        return "end";
    }*/
    private static int num = 0;

    @Autowired(required = true)
    private RedisLock redisLock;

    @RequestMapping("/reduce_stock")
    public String reduceStock() throws InterruptedException {
     /*   RLock lock = redisson.getLock(product);
        lock.tryLock(10000, TimeUnit.SECONDS);*/
        redisLock.tryLock(product,0L,TimeUnit.SECONDS);
        redisLock.tryLock(product,0L,TimeUnit.SECONDS);
        Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
        try {
            if (stock > 0) {
                stock = stock - 1;
                //更新库存
                stringRedisTemplate.opsForValue().set("stock", stock + "");
                System.out.println(":扣减成功，库存stock：" + stock);

            } else {
                System.out.println(":扣减失败，库存不足");//下单失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
            redisLock.releaseLock(product);
            redisLock.releaseLock(product);
        }
        //可以保证原子性
        return"end";
    }










    public static Thread findThread(long threadID){
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group!=null){
            Thread[] threads = new Thread[(int) (group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for (int i = 0; i < count; i++) {
                if (threadID==threads[i].getId()){
                    return threads[i];
                }
            }
        }
        return null;
    }


}