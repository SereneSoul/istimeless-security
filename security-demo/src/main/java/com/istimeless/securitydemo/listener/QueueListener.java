package com.istimeless.securitydemo.listener;

import com.istimeless.securitydemo.common.DeferredResultHolder;
import com.istimeless.securitydemo.common.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author lijiayin
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;
    
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while(true){
                if(StringUtils.isNoneBlank(mockQueue.getColpleteOrder())){
                    String orderNumber = mockQueue.getColpleteOrder();
                    log.info("返回订单处理结果：" + orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("success");
                    mockQueue.setColpleteOrder(null);
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
