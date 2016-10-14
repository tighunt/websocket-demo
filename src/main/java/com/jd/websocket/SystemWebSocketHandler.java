package com.jd.websocket;


import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	;

	/**当用户连接成功后调用的方法
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		System.out.println("ConnectionEstablished" + "=>当前在线用户的数量是:" + users.size());
	}

	/**
	 * 每当客户端发送信息过来，都会由这个函数接收并处理。
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		System.out.println(message.getPayload());
		TextMessage returnMessage = new TextMessage("大家好，我是"+message.getPayload());
		//给所有用户发送消息
		sendMessageToUsers(returnMessage);
	}

	/**
	 * 客户端和服务器连接发生异常调用的方法
	 * @param session
	 * @param exception
	 * @throws Exception
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	/**
	 * 当用户断开连接调用的方法
	 * @param session
	 * @param closeStatus
	 * @throws Exception
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
		System.out.println("ConnectionClosed" + "=>当前在线用户的数量是:" + users.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}


	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.isOpen()) {
				try {
					for (int i = 0; i < 5; i++) {
						user.sendMessage(message);
					}
				} catch (IOException  e) {
					e.printStackTrace();
				}
			}
		}
	}
}
