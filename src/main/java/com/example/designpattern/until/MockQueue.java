package com.example.designpattern.until;

import com.example.designpattern.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.NewThreadAction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@Slf4j
public class MockQueue {

    /**
     * A项目的收到请求后,将请求提交的数据放入消息队列.
     *
     * B项目从消息队列取出数据后进行处理,处理完成后,将返回消息放回消息队列.
     *
     * A项目的另一个线程一直从消息队列中获取数据,如果获取到了,就返回给之前的请求者.
     *
     * 那么,就可以使用DeferredResult对象来进行异步处理.
     */


    //接受队列
    private BlockingQueue<Task<String>> receiveQueue = new LinkedBlockingDeque<>();

    //完成队列
    private BlockingQueue<Task<String>> completeQueue = new LinkedBlockingDeque<>();

    //接受任务
    public void put(Task<String> task) throws InterruptedException{
        receiveQueue.put(task);
    }

    //获取完成任务
    public Task<String> get() throws InterruptedException{
        return completeQueue.take();
    }

    //处理任务
    private void execute(){
        new Thread(()->{
            while (true){
                try{
                    //从接受队列中取出任务，处理，然后放入成功队列
                    Task<String> task = receiveQueue.take();
                    log.info("队列收到数据，处理中");
                    Thread.sleep(2000L);
                    task.setMessage("success");

                    //如果超时了，中断该任务->此处应该加锁
                    if(task.getIsTimeout()){
                        log.info("任务超时，处理线程中断该任务");
                        continue;
                    }

                    log.info("队列处理完成");
                    completeQueue.put(task);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //开启处理任务
    public MockQueue(){
        execute();
    }

}
