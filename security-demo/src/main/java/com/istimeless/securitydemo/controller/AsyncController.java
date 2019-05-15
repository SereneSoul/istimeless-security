package com.istimeless.securitydemo.controller;

import com.istimeless.securitydemo.common.DeferredResultHolder;
import com.istimeless.securitydemo.common.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @author lijiayin
 */
@RestController
@Slf4j
public class AsyncController {
    
    @Autowired
    private MockQueue mockQueue;
    
    @Resource
    private DeferredResultHolder deferredResultHolder;
    
    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        log.info("主线程开始");
        
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
//        Callable<String> result = () -> {
//            log.info("副线程开始");
//            Thread.sleep(1000);
//            log.info("副线程结束");
//            return "success";
//        };
        log.info("主线程结束");
        return result;
    }
}
