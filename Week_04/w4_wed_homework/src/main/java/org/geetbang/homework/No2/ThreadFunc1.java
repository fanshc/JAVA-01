package org.geetbang.homework.No2;

/**
 * @Description: 此为第一种方法，通过主线程sleep的方式
 * @Auther: fanshc
 * @Date: 2021/02/02/3:50 下午
 */
public class ThreadFunc1 {

    private volatile static String value;

    /**
     * 通过主线程循环+sleep的方式，判断子线程是否给类属性赋值，如果赋值，则跳出循环，退出主线程
     * 不知道这种方式是不是符合题意，暂时写上吧
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            ThreadFunc1.value = "获取到了";
        });
        thread.start();
        while(true){
            if (value != null) {
                System.out.println(value);
                break;
            }
            Thread.sleep(1000);
        }
    }
}
