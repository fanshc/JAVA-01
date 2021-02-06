package org.geetbang.homework.No2;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description: 通过FutureTask的get方法获取到新线程里面的值
 * @Auther: fanshc
 * @Date: 2021/02/06/5:01 下午
 */
public class ThreadFunc8 {

    public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<String>(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e ) {
                e.printStackTrace();
            }
            return "取到了";
        });
        Thread thread = new Thread(future);
        thread.start();
        try{
            System.out.println(future.get());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
