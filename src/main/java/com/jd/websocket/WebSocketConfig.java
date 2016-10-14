package com.jd.websocket;

/**
 * Created by sunpengwei on 2016/9/17.
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
ע��������Ҫ����SpringMVCɨ��·����
@Configuration     ָ������ΪSpring ������
@EnableWebSocket  ��������֧��WebSocket
*/
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements  WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor());
		registry.addHandler(myHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor());
	}

	@Bean
	public WebSocketHandler myHandler() {

		return new SystemWebSocketHandler();
	}
}







