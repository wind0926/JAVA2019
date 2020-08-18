
package cn.tll.redisson;

/**
 * @author tanglilei
 * @since 2020/7/20 16:13
 */
public class synchronizedTest {
        public static synchronized void doSomething(){
            System.out.println("方法1执行");
            doOthers();
        }
        public static  void doOthers(){
            System.out.println("方法2执行");
        }

    public static void main(String[] args) {
        doSomething();
    }

}
