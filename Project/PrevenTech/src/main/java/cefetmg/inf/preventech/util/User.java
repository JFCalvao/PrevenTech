/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.util;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

public class User {
    String name;
    String cpf;
    HttpSession httpSession;
    Session sSession;
    
    public User() {}
    
    public User(HttpSession session) {
        this.httpSession = session;
    }
    
    public User(Session session) {
        this.sSession = session;
    }
}
