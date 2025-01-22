/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dto;

/**
 *
 * @author jfcalvao
 */
public class Equipamento {
    public Equipamento(String nome, String n_patrimonio, String local, String estado) {
        this.nome = nome;
        this.n_patrimonio = n_patrimonio;
        this.local = local;
        this.estado = estado;
    }
    
    public Equipamento() {
        this.nome = "";
        this.n_patrimonio = "";
        this.local = "";
        this.estado = "";
    }
    
    private String nome;
    private String n_patrimonio;
    private String local;
    private String estado;
    
    public void setNome(String nome) { this.nome = nome; }
    public void setN_patrimonio(String n_patrimonio) { this.n_patrimonio = n_patrimonio; }
    public void setLocal(String local) { this.local = local; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getNome() { return this.nome; }
    public String getN_patrimonio() { return this.n_patrimonio; }
    public String getLocal() { return this.local; }
    public String getEstado() { return this.estado; }
}
