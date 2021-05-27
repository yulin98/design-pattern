/*
package com.example.designpattern.listener;

import com.bitanswer.library.BitAnswer;
import com.langyangtech.iot.bitauthorization.authorization.CloudAuthorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class ApplicationEventListener implements ApplicationListener {

    @Value("${authorization.sn}")
    private String sn ;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 在这里可以监听到Spring Boot的生命周期
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            // 初始化环境变量

        } else if (applicationEvent instanceof ApplicationPreparedEvent) {
            // 初始化完成

        } else if (applicationEvent instanceof ContextRefreshedEvent) {
            // 应用刷新

        } else if (applicationEvent instanceof ApplicationReadyEvent) {
            // 应用已启动完成
            try {
                //登录比特云云端授权环境
                CloudAuthorization.login(sn);
            } catch (BitAnswer.BitAnswerException e) {
                if (e.getErrorCode() == 284) {
                    throw new RuntimeException("授权码输入错误，请检查授权码拼写是否正确");
                } else if (e.getErrorCode() == 1793) {
                    throw new RuntimeException("授权码已经过期 ");
                }else if (e.getErrorCode() == 1794) {
                    throw new RuntimeException("在线用户数超过限制 ");
                }
                throw new RuntimeException("授权码无效");
            }
        } else if (applicationEvent instanceof ContextStartedEvent) {
            //应用启动，需要在代码动态添加监听器才可捕获

        } else if (applicationEvent instanceof ContextStoppedEvent) {
            // 应用停止

        } else if (applicationEvent instanceof ContextClosedEvent) {
            // 应用关闭
            try {
                //退出比特云云端环境
                CloudAuthorization.logout();
            } catch (BitAnswer.BitAnswerException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

}
*/
