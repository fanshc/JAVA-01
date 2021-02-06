package org.geetbang.homework.No2;

import java.util.concurrent.Exchanger;

/**
 * @Description: 通过定义一个传递Exchanger的新线程， 调用exchanger的方法，让两个线程里面的数据发生变化
 * @Auther: fanshc
 * @Date: 2021/02/06/10:26 下午
 */
public class ThreadFunc11 {
    public static void main(String[] args) {
        String value = "Exchanger";
        Exchanger<String> exchanger = new Exchanger<>();

        try{
            MyThread myThread = new MyThread(exchanger);
            myThread.start();
            System.out.println(Thread.currentThread().getName()+"---->"+exchanger.exchange(value));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static class MyThread extends Thread{
        private String value;
        Exchanger<String> exchanger;
        public MyThread(){}
        public MyThread(Exchanger<String> exchanger){
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(5000);
                value = "取到了";
                System.out.println(Thread.currentThread().getName()+"---->"+this.exchanger.exchange(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
