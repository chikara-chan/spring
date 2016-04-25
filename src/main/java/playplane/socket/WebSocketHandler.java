package playplane.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import playplane.model.Product;

public class WebSocketHandler extends TextWebSocketHandler {
	private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();
	private Map<String, String> users = new ConcurrentHashMap<>();

	/**
	 * 连接成功时候，会触发UI上onopen方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connect to the websocket success......");
		clients.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		clients.remove(session.getId());
		users.remove(session.getId());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		clients.remove(session.getId());
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String data = message.getPayload();
		Gson g = new Gson();
		@SuppressWarnings("unchecked")
		Map<String, Object> datas = g.fromJson(data, Map.class);
		String type = datas.get("type").toString();
		if ("1".equals(type)) {
			datas.put("pcount", clients.keySet().size() + "");
			users.put(session.getId(), datas.get("username").toString());
		} else if ("3".equals(type)) {
			clients.remove(session.getId());
			users.remove(session.getId());
			datas.put("pcount", clients.keySet().size() + "");
		}
		List<String> list = new ArrayList<String>();
		for (String key : users.keySet()) {
			list.add((String)users.get(key));
		}
		datas.put("users",(String[])list.toArray(new String[0]));
		TextMessage tm = new TextMessage(g.toJson(datas));
		sendToAll(tm);
	}

	private void sendToAll(TextMessage tm) {
		try {
			for (WebSocketSession session : clients.values()) {
				if (session.isOpen()) {
					session.sendMessage(tm);
				} else {
					clients.remove(session.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
