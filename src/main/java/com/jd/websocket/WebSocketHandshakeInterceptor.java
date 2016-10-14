package com.jd.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.Map;

/**
 * WebSocket握手拦截器用来拦截和处理客户端和服务器端分别在握手前和握手后的事件，
 * 我们这里添加这个拦截器是为了清晰的知道什么时候以及是否握手成功。
 */
@Component
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	/**
	 * 握手前拦截器
	 * @param request
	 * @param response
	 * @param wsHandler
	 * @param attributes
	 * @return
	 * @throws Exception
	 */
	public boolean beforeHandshake(ServerHttpRequest request,
								   ServerHttpResponse response, WebSocketHandler wsHandler,
								   Map<String, Object> attributes) throws Exception {

		System.out.println("连接前的拦截");

		return super.beforeHandshake(request, response, wsHandler, attributes);

	}

	/**
	 * 握手后拦截器
	 * @param request
	 * @param response
	 * @param wsHandler
	 * @param ex
	 */
	public void afterHandshake(ServerHttpRequest request,
							   ServerHttpResponse response, WebSocketHandler wsHandler,
							   Exception ex) {
		System.out.println("连接后的拦截");
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
