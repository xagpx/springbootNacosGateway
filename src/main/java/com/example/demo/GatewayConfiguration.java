package com.example.demo;
 
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;

/**
  * @Title:NacosGatewayConfiguration.java
  * @Description:TODO
  * @Author:82322156@qq.com
  * @Date:2020年2月8日下午11:11:22
  * @Version:1.0
  * Copyright 2020  Internet  Products Co., Ltd.
  */
@Configuration
public class GatewayConfiguration {
 	@Autowired
  	RedisRateLimiter redisRateLimiter;
	
	
   private final List<ViewResolver> viewResolvers;
   private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit() {
        initCustomizedApis();
        initGatewayRules();
    }

    private void initCustomizedApis() {
//  Set<ApiDefinition> definitions = new HashSet<>();
//        ApiDefinition api1 = new ApiDefinition("some_customized_api")
//            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//                add(new ApiPathPredicateItem().setPattern("/ahas"));
//                add(new ApiPathPredicateItem().setPattern("/product/**")
//                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//            }});
      /*  ApiDefinition api2 = new ApiDefinition("another_customized_api")
            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                add(new ApiPathPredicateItem().setPattern("/**")
                    .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
            }});
//        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);*/
    }

    private void initGatewayRules() {
//    Set<GatewayFlowRule> rules = new HashSet<>();
    /* rules.add(new GatewayFlowRule("testb")
            .setCount(1)
            .setIntervalSec(1)
//            .setGrade(RuleConstant.FLOW_GRADE_QPS)
        );
        rules.add(new GatewayFlowRule("aliyun_route")
            .setCount(1)
            .setIntervalSec(1)
            .setBurst(2)
            .setParamItem(new GatewayParamFlowItem()
                .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
            )
        );*/
//        rules.add(new GatewayFlowRule("httpbin_route")
//            .setCount(10)
//            .setIntervalSec(1)
//            .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
//            .setMaxQueueingTimeoutMs(600)
//            .setParamItem(new GatewayParamFlowItem()
//                .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
//                .setFieldName("X-Sentinel-Flag")
//            )
//        );
//        rules.add(new GatewayFlowRule("httpbin_route")
//            .setCount(1)
//            .setIntervalSec(1)
//            .setParamItem(new GatewayParamFlowItem()
//                .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                .setFieldName("pa")
//            )
//        );
//        rules.add(new GatewayFlowRule("httpbin_route")
//            .setCount(2)
//            .setIntervalSec(30)
//            .setParamItem(new GatewayParamFlowItem()
//                .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                .setFieldName("type")
//                .setPattern("warn")
//                .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS)
//            )
//        );
//
        /* rules.add(new GatewayFlowRule("another_customized_api")
            .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
            .setCount(1)
            .setIntervalSec(1)
            .setParamItem(new GatewayParamFlowItem()
                .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
                .setFieldName("pn")
            )
        );
         GatewayRuleManager.loadRules(rules); */
    }
    
/*	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		//不显示限流head 
		 redisRateLimiter.setIncludeHeaders(false);
		return builder.routes()
			   .route(
				p -> p.path("/test")
				.filters(f -> 
				f.addRequestHeader("Hello", "World")
//				 .addRequestParameter("name", "zhangsan")
				 .requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter()))
				)
				.uri("lb://nacos-consumer"))
				.build();
	}*/
	//redis-rate-limiter.replenishRate ： 允许用户每秒处理多少个请求。这是令牌桶被填充的速率。
    //redis-rate-limiter.burstCapacity ： 用户在一秒钟内允许执行的最大请求数。这是令牌桶可以容纳的令牌数量。将此值设置为0将阻塞所有请求
    /*@Bean
	public RedisRateLimiter redisRateLimiter() {
         return new RedisRateLimiter(1,1);
     }*/
}