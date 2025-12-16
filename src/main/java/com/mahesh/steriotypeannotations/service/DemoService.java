package com.mahesh.steriotypeannotations.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String hello(){
        return "Hello Service";
    }

}
