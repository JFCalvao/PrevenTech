/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.LinkedList;
import java.util.List;
import cefetmg.inf.preventech.dao.User;

/**
 *
 * @author jfcalvao
 */
public class UsersList {
    public static List<User> Users = new LinkedList();
    
    public static void add(User user) {
        Users.add(user);
    }
    
    public static User get(HttpSession session) {
        for(User user : Users) {
            if(user.getServer() == session) {
                return user;
            }
        }
        return null;
    }
    
    public static User get(Session session) {
        for(User user : Users) {
            if(user.getChat() == session) {
                return user;
            }
        }
        return null;
    }
    
    public static void remove(HttpSession session) {
        for(User user : Users) {
            if(user.getServer() == session) {
                Users.remove(user);
            }
        }
    }
    
    public static void remove(Session session) {
        for(User user : Users) {
            if(user.getChat() == session) {
                Users.remove(user);
            }
        }
    }
    
}
