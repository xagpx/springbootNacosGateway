package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
	RestTemplate restTemplate;
   
   /*
    * eureka-provider 注册服务名
    */
	public String getString(String name) {
		String data = restTemplate.getForObject("http://eureka-provider/hello?name=" + name, String.class);
		return data;
	}
    
}