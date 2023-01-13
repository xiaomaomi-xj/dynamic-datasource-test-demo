package com.yuansheng;

import com.yuansheng.service.impl.ReadTransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    ReadTransactionServiceImpl readTransactionService;

    @Test
    public void test1(){
        readTransactionService.getContextOnTransactionOnAnnotation();
    }

    @Test
    public void test2(){
        readTransactionService.getContextOnTransactionOnMethod();
    }
}
