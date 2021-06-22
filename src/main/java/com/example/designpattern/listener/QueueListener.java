package com.example.designpattern.listener;

import com.example.designpattern.entity.Task;
import com.example.designpattern.until.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while (true){
                try {
                    Task<String> task = mockQueue.get();
                    log.info("监听器获取到成功的数据。task；{}",task);
                    task.getResult().setResult(task.getMessage());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
