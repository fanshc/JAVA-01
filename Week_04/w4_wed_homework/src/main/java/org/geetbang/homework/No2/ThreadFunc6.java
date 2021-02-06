package org.geetbang.homework.No2;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 通过LockSupport工具方法的park、unpark工具方法实现等待已经唤醒的操作
 * @Auther: fanshc
 * @Date: 2021/02/06/2:39 下午
 */
public class ThreadFunc6 {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        MyThread myThread = new MyThread(thread);
        myThread.start();
        LockSupport.park();
        System.out.println(myThread.getValue());
    }
    static class MyThread extends Thread{
        private String value;
        private Thread thread;
        public MyThread(){}
        public MyThread(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run() {
            try{
                Thread.sleep(5000);
                value = "取到了";
                LockSupport.unpark(this.thread);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getValue() {
            return value;
        }
    }
}
