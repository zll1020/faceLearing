package com.example.face.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-03-29
 * Time: 21:43
 */
public class Demo1 implements Runnable {

    static Demo1 demo = new Demo1();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        // 执行完线程，再执行后面的流程
        t1.join();
        t2.join();
        System.out.println("finished");
    }
    @Override
    public void run() {
        method();
    }

    public synchronized void method(){
        System.out.println("当前线程："+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }
}
