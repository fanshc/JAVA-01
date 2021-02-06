package org.geetbang.homework.No2;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 通过CyclicBarrier实现CyclicBarrier里面的计数为0的时候，继续执行后续的方法
 * @Auther: fanshc
 * @Date: 2021/02/06/2:06 下午
 */
public class ThreadFunc5 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        MyThread5 myThread5 = new MyThread5(cyclicBarrier);
        myThread5.start();
        try{
            cyclicBarrier.await();
            System.out.println(myThread5.getValue());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class MyThread5 extends Thread{
    CyclicBarrier cyclicBarrier;
    public MyThread5(){}
    public MyThread5(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    private String value;

    public String getValue() {
        return value;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            value = "取到了";
            cyclicBarrier.await();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
