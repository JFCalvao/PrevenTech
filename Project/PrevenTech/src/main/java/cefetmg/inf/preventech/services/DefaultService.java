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
public class DefaultService implements UserService {
    
    public DefaultService() {
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.profissao = "";
        this.serverSession = null;
        this.chatSession = null;
    }
    
    private final String initialPage = "index.jsp";
    
    @Override
    public String getInitialPage() {
        return this.initialPage;
    }
    
    @Override
    public boolean hasAccess(String page) {
        return false;
    }
    
    private final String nome;
    private final String cpf;
    private final String email;
    private final String profissao;
    private final HttpSession serverSession;
    private final Session chatSession;
    
    @Override
    public String getNome() {
        return this.nome;
    }
    
    @Override
    public String getCPF() {
        return this.cpf;
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }
    
    @Override
    public String getProfissao() {
        return this.profissao;
    }
    
    @Override
    public HttpSession getServerSession() {
        return this.serverSession;
    }
    
    @Override
    public Session getChatSession() {
        return this.chatSession;
    }
}
