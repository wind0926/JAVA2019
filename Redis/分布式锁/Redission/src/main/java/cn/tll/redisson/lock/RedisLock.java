package cn.tll.redisson.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author tanglilei
 * @since 2020/7/20 14:15
 */
public interface RedisLock {//单例

    Boolean tryLock(String key, Long timeout, TimeUnit unit);

    void releaseLock(String key);


}
