/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

/**
 *
 * @author jfcalvao
 */
public class Requisicao {
    public Requisicao(String requisicao_id, String requisitor_cpf,
                      String responsavel_cpf, String data_inicio,
                      int categoria, String equipamentos,
                      String descricao) {
        this.requisicao_id = requisicao_id;
        this.requisitor_cpf = requisitor_cpf;
        this.responsavel_cpf = responsavel_cpf;
        this.data_inicio = data_inicio;
        this.categoria = categoria;
        this.equipamentos = equipamentos;
        this.descricao = descricao;
    }
    
    String requisicao_id;
    String requisitor_cpf;
    String responsavel_cpf;
    String data_inicio;
    int categoria;
    String equipamentos;
    String descricao;
    
    public void setID(String id) { this.requisicao_id = id; }
    public void setRequisitor_cpf(String cpf) { this.requisitor_cpf = cpf; }
    public void setResponsavel_cpf(String cpf) { this.responsavel_cpf = cpf; }
    public void setData(String data) { this.data_inicio = data; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
    public void setEquipamentos(String equipamentos) { this.equipamentos = equipamentos; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getId() { return this.requisicao_id; }
    public String getRequisitor_cpf() { return this.requisitor_cpf; }
    public String getResponsavel_cpf() { return this.responsavel_cpf; }
    public String getData() { return this.data_inicio; }
    public int getCategoria() { return this.categoria; }
    public String getEquipamentos() { return this.equipamentos; }
    public String getDescricao() { return this.descricao; }
}
