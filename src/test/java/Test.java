import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(1,1,1,
                    TimeUnit.SECONDS,new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        //thread first
        THREAD_POOL_EXECUTOR.submit(()->{
            //do nothing,just sleep 100s
            try {
            Thread.sleep(10000);
                System.out.println("任务1已经完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
        System.out.println("已提交第一个任务..");

    //thread second
        THREAD_POOL_EXECUTOR.submit(()->{
        //do nothing,just sleep 100s
            try {
        Thread.sleep(10000);
                System.out.println("任务2执行完成");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    });
        System.out.println("已提交第二个任务..");

        System.out.println("线程池资源即将耗尽..");

        long startTime = System.currentTimeMillis();
        //thread third
        THREAD_POOL_EXECUTOR.submit(()->{
        //do nothing,just sleep 100s
            try {
        System.out.println("触发拒绝策略，该方法将由主线程同步执行");
//                System.out.println("触发拒绝策略，等待最久的一个任务将被丢弃,当前任务数量：" + THREAD_POOL_EXECUTOR.getTaskCount());//                System.out.println("触发拒绝策略，将直接报错");//                System.out.println("触发拒绝策略，该线程将被直接丢弃");
        Thread.sleep(20000);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        });
        System.out.println("主线程已经被阻塞，但线程三已执行完，共计耗时：" + (System.currentTimeMillis() - startTime));
//        System.out.println("线程池已将等待最久一个任务丢弃，我会被立马执行" + (System.currentTimeMillis() - startTime));//        System.out.println("线程池会报错，我将无法执行");//        System.out.println("线程池将直接丢弃任务三，我会立马被执行" + (System.currentTimeMillis() - startTime));
        }
}
