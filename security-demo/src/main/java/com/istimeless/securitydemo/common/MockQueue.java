package com.istimeless.securitydemo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lijiayin
 */
@Component
@Slf4j
public class MockQueue {
    
    private String placeOrder;
    
    private String colpleteOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            log.info("接到下单请求：" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.colpleteOrder = placeOrder;
            log.info("下单请求处理完毕：" + placeOrder);
        }).start();
        
    }

    public String getColpleteOrder() {
        return colpleteOrder;
    }

    public void setColpleteOrder(String colpleteOrder) {
        this.colpleteOrder = colpleteOrder;
    }
}
