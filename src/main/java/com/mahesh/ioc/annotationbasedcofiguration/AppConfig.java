package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mahesh.ioc.annotationbasedcofiguration")
public class AppConfig {



}
