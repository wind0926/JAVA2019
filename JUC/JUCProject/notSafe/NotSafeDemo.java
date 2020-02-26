package JUCProject.notSafe;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
* 题目：请举例说明集合是不安全的
* */
/*
1.故障原因
* java.util.ConcurrentModificationException
* 2.导致原因
* arraylist线程不安全，底层没有加同步锁
*
* 3.解决方案
* 3.1 Vector 线程安全
* 3.2  Collections.synchronizedList(new ArrayList<>())
*3.3  new CopyOnWriteArrayList<>();
*
* 4.优化建议(同样的错误，不出现第二次)
* */
/*
*
*
/**
 * Appends the specified element to the end of this list.
 *
 * @param e element to be appended to this list
 * @return {@code true} (as specified by {@link Collection#add})
 */
/*
public boolean add(E e) {
final ReentrantLock lock = this.lock;
        lock.lock();
        try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
        } finally {
        lock.unlock();
        }
        }



        CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
        而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，然后向新的容器Object[] newElements里添加元素。
        添加元素后，再将原容器的引用指向新的容器setArray(newElements)。
        这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
        所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
*/
/*

HashMap中数组中存储node节点，

* */
public class NotSafeDemo {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
//        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
//        HashSet<Object> set = new HashSet<>();
//        HashMap<Object, Object> map = new HashMap<>();
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();

        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
             map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
