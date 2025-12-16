package com.mahesh.steriotypeannotations;

import com.mahesh.steriotypeannotations.controller.DemoController;
import com.mahesh.steriotypeannotations.repository.DemoRepository;
import com.mahesh.steriotypeannotations.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoClient {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DemoController demoController = applicationContext.getBean(DemoController.class);
        System.out.println(demoController.hello());
        DemoRepository demoRepository = applicationContext.getBean(DemoRepository.class);
        System.out.println(demoRepository.hello());
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.hello());
    }
}
