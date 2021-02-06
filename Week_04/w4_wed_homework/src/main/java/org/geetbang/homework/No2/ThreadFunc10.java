package org.geetbang.homework.No2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description:通过CompletableFuture实现
 * @Auther: fanshc
 * @Date: 2021/02/06/5:25 下午
 */
public class ThreadFunc10 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String result = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return "取到了 ";
        }).get();
        System.out.println(result);
        System.out.println(Thread.currentThread().getName());

    }

}
