package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

import javax.annotation.Resource;

@RestController
public class UserController {
	private RestTemplate restTemplate;

	@Autowired
	public UserController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
    @Resource
    private UserService userservice;
    
    @RequestMapping("/hello")
    public String getString(String name){
        return userservice.getString(name);
    }
    @SentinelResource
    @RequestMapping("/fallback")
    public String fallback(){
        return "服务繁忙，请稍后重试";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
	public String echo() {
		return restTemplate.getForObject("http://nacos-provider/user", String.class);
	}
    
    @RequestMapping("/testx")
    public String test(){
        return "test";
    }
    @RequestMapping("/a/abc")
    public String testa(){
        return "a/test";
    }
    
    @RequestMapping("/b")
    public String b(){
        return "b";
    }
}