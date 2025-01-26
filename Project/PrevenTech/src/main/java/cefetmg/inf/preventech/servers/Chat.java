/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.dto.User;
import org.json.JSONObject;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.*;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import org.json.JSONArray;

@ServerEndpoint("/chat")
public class Chat {
    private static Set<User> users = new HashSet<>();

    @OnMessage
    public String onMessage(Session userSession, String message) {
        return null;
    }
    
    @OnOpen
    public void onOpen(Session session) {
        User newUser = new User(session);
        users.add(newUser);
        
        JSONArray jsonMessages = new JSONArray();
        try {
            session.getBasicRemote().sendText(jsonMessages.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        
    }
    
}
