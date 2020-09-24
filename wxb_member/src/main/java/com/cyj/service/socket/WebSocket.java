package com.cyj.service.socket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author:aizhishang
 * time:2020/9/22
 */
@Component
@ServerEndpoint("/WebSocket/{orderId}")
public class WebSocket {
    private static ConcurrentHashMap<String , Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void open(@PathParam("orderId")String orderId,Session session){
        sessionMap.put(orderId, session);
    }

    @OnClose
    public void close(@PathParam("orderId")String orderId){
        sessionMap.remove(orderId);
    }

    public static void sendMsg(String orderId,String msg){
        try {
            Session session = sessionMap.get(orderId);
            if (session!=null){
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
