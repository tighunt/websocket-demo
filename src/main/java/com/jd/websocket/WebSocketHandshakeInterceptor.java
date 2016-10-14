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
 * WebSocket�����������������غʹ���ͻ��˺ͷ������˷ֱ�������ǰ�����ֺ���¼���
 * ����������������������Ϊ��������֪��ʲôʱ���Լ��Ƿ����ֳɹ���
 */
@Component
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	/**
	 * ����ǰ������
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

		System.out.println("����ǰ������");

		return super.beforeHandshake(request, response, wsHandler, attributes);

	}

	/**
	 * ���ֺ�������
	 * @param request
	 * @param response
	 * @param wsHandler
	 * @param ex
	 */
	public void afterHandshake(ServerHttpRequest request,
							   ServerHttpResponse response, WebSocketHandler wsHandler,
							   Exception ex) {
		System.out.println("���Ӻ������");
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
