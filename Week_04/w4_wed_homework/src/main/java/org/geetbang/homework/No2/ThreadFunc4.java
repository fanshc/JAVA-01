package org.geetbang.homework.No2;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 通过CountDownLatch实现CountDownLatch里面的计数为0的时候，主动唤醒主线程后继续执行主线程下面的代码
 * @Auther: fanshc
 * @Date: 2021/02/06/1:48 下午
 */
public class ThreadFunc4 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyThread4 myThread4 = new MyThread4(countDownLatch);
        myThread4.start();
        try{
            countDownLatch.await();
            System.out.println(myThread4.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class MyThread4 extends Thread{

    private String value;
    private CountDownLatch countDownLatch;
    public MyThread4(){
    }
    public MyThread4(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            value = "取到了";
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(countDownLatch !=  null){
                countDownLatch.countDown();
            }
        }
    }

    public String getValue() {
        return value;
    }
}
