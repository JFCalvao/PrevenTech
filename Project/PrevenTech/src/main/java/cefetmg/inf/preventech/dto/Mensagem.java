/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dto;

/**
 *
 * @author jfcalvao
 */
public class Mensagem {
    public Mensagem(int id, String user, String mensagem) {
        this.id = id;
        this.user = user;
        this.mensagem = mensagem;
    }
    
    private int id;
    private String user;
    private String mensagem;
    
    public void setId(int id) { this.id = id; }
    public void setUser(String user) { this.user = user; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public int getId() { return this.id; }
    public String getUser() { return this.user; }
    public String getMensagem() { return this.mensagem; }
}
