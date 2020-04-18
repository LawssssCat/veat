package test;

import java.util.ArrayList;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/16 23:14
 */
public class SomeThread {

    private static Object o = new Object();

    private volatile static Integer num = 0;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + ++num);
                }
            });
            t1.start();
            list.add(t1);
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + --num);
                }
            });
            t2.start();
            list.add(t2);
        }
        for (Thread t : list) {
            t.join();
        }
        System.out.println(num);

    }
}
