/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.services;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

/**
 *
 * @author jfcalvao
 */
public interface UserService {
    public String getInitialPage();
    public boolean hasAccess(String page);
    
    public String getNome();
    public String getCPF();
    public String getEmail();
    public String getProfissao();
    public HttpSession getServerSession();
    public Session getChatSession();
}
