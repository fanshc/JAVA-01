package org.geetbang.homework.No2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 通过可重入锁实现，在新线程的run方法中进行赋值，锁定操作，然后调用Condition的signal方法唤醒主线程获取数据<br>
 *     改方式有弊端， 新线程的run方法与getValue方法不知道谁先谁后执行，导致如果condition还没执行await方法时，run方法里面调用了signal方法<br>
 *         就会出现异常，所以验证此方法之前，是先让新线程的run方法主动sleep了5秒， 让主线程先执行getValue方法；
 * @Auther: fanshc
 * @Date: 2021/02/06/3:01 下午
 */
public class ThreadFunc7 {
    final Lock lock = new ReentrantLock(true);
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(myThread.getValue());
    }

    static class MyThread extends Thread{
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();

        private String value;

        @Override
        public void run() {
            try{
                Thread.sleep(5000);
                lock.tryLock();
                System.out.println("run已经速定");
                value = "取到了";
                condition.signal();
                lock.unlock();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public String getValue() {
            try{
                System.out.println("getValue即将获得锁");
                lock.tryLock(50000,TimeUnit.SECONDS);
                System.out.println("获得锁成功");
                condition.await();
                lock.unlock();
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
