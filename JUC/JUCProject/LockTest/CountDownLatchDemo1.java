package JUCProject.LockTest;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "国,灭亡");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println("秦统一");

    }
}
