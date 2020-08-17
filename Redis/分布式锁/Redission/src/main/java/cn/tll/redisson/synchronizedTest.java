/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
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