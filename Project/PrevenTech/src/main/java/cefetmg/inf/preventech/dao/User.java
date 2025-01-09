/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

/**
 *
 * @author jfcalvao
 */
public class User {
    public User(HttpSession serverSession, String nome, String cpf, String senha, String email, String profissao) {
        this.serverSession = serverSession;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.profissao = profissao;
    }
    
    public User(String nome, String cpf, String senha, String email, String profissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.profissao = profissao;
    }
    
    public User(HttpSession serverSession) {
        this.serverSession = serverSession;
    }
    
    private HttpSession serverSession;
    private Session chatSession;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String profissao;
    
    public void setNome(String nome) { this.nome = nome; }
    public void setCPF(String cpf) { this.cpf = cpf; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setEmail(String email) { this.email = email; }
    public void setProfissao(String profissao) { this.profissao = profissao; }
    public void setServer(HttpSession serverSession) { this.serverSession = serverSession; }
    public void setChat(Session chatSession) { this.chatSession = chatSession; }
    
    public String getNome() { return this.nome; }
    public String getCPF() { return this.cpf; }
    public String getSenha() { return this.senha; }
    public String getEmail() { return this.email; }
    public String getProfissao() { return this.profissao; }
    public HttpSession getServer() { return this.serverSession; }
    public Session getChat() { return this.chatSession; }
}
