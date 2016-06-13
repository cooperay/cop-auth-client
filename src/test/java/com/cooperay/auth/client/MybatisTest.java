package com.cooperay.auth.client;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cooperay.auth.client.service.impl.AuthServiceImpl;

public class MybatisTest {

    
    public static void main(String[] args) throws IOException {
       ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
       AuthServiceImpl service = context.getBean(AuthServiceImpl.class);
       service.test();
    }
    
    
}
