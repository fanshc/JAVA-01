package org.geetbang.homework.No2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description: 通过ExecutorService执行FutureTask里面的方法，然后通过FutureTask.get获取返回值
 * @Auther: fanshc
 * @Date: 2021/02/06/5:20 下午
 */
public class ThreadFunc9 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e ) {
                e.printStackTrace();
            }
            return "取到了";
        });
        executorService.submit(future);
        try{
            System.out.println(future.get());
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
