package org.geetbang.homework.No2;

/**
 * @Description:通过线程的join方式，让其他的线程在另一个线程里面集结<br>
 * @Auther: fanshc
 * @Date: 2021/02/06/1:40 下午
 */
public class ThreadFunc3 {

    private static String value;

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try{
                //为了避免随机性，让新线程sleep5
                Thread.sleep(5000);
                value = "取到了";
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
        try{
            //让新线程在当前线程中集结，集结完成后执行下面的代码
            thread.join();
            System.out.println(value);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
