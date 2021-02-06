package org.geetbang.homework.No2;

/**
 * @Description: 此为第二种方式，通过wait、notify的方式<br>
 *     <li>1、主线程将一个对象同步修饰</li>
 *     <li>2、主线程调用1中的对象的wait方法</li>
 *     <li>3、主线程开启新线程，将第一个参数传递给新线程</li>
 *     <li>4、新线程操作赋值之后，重新同步、调用传入1中的对象notify方法，让主线程继续执行</li>
 *     <li>5、调用新线程中的getValue方法，获取新线程中的值</li>
 * @Auther: fanshc
 * @Date: 2021/02/02/4:01 下午
 */
public class ThreadFunc2 {

    public static void main(String[] args) throws InterruptedException {
        ThreadFunc2 threadFunc2 = new ThreadFunc2();

        MyThread thread = new MyThread(threadFunc2);
        thread.start();
        synchronized (threadFunc2){
            threadFunc2.wait();
        }
        System.out.println(thread.getValue());
    }

}
class MyThread extends Thread {

    private ThreadFunc2 threadFunc2;

    private String value;

    public MyThread(){}

    public MyThread(ThreadFunc2 threadFunc2){
        this.threadFunc2 = threadFunc2;
    }

    @Override
    public void run() {
        try{
            //为了确保主线程在等待，此线程主动sleep5秒
            Thread.sleep(5000);
            value  = "取到了";
            synchronized (this.threadFunc2) {
                this.threadFunc2.notify();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValue() {
        return value;
    }
}
