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

	/**���û����ӳɹ�����õķ���
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		System.out.println("ConnectionEstablished" + "=>��ǰ�����û���������:" + users.size());
	}

	/**
	 * ÿ���ͻ��˷�����Ϣ����������������������ղ�����
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		System.out.println(message.getPayload());
		TextMessage returnMessage = new TextMessage("��Һã�����"+message.getPayload());
		//�������û�������Ϣ
		sendMessageToUsers(returnMessage);
	}

	/**
	 * �ͻ��˺ͷ��������ӷ����쳣���õķ���
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
	 * ���û��Ͽ����ӵ��õķ���
	 * @param session
	 * @param closeStatus
	 * @throws Exception
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
		System.out.println("ConnectionClosed" + "=>��ǰ�����û���������:" + users.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}


	/**
	 * �����������û�������Ϣ
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
