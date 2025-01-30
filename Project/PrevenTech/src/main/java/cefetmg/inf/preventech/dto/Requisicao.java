/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dto;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;

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
        this.categoriaString = null;
        this.responsavelString = null;
        this.requisitorString = null;
        this.status = null;
    }
    
    public Requisicao(String requisicao_id, String requisitor_cpf,
                      String responsavel_cpf, int categoria,
                      String equipamentos, String descricao) {
        this.requisicao_id = requisicao_id;
        this.requisitor_cpf = requisitor_cpf;
        this.responsavel_cpf = responsavel_cpf;;
        this.categoria = categoria;
        this.equipamentos = equipamentos;
        this.descricao = descricao;
        this.categoriaString = null;
        this.responsavelString = null;
        this.requisitorString = null;
        this.status = null;
        
        ZoneId fusoHorarioBrasil = ZoneId.of("America/Sao_Paulo"); 
        ZonedDateTime dataHoraBrasil = ZonedDateTime.now(fusoHorarioBrasil); 
        int dia = dataHoraBrasil.getDayOfMonth(); 
        int mes = dataHoraBrasil.getMonthValue(); 
        int ano = dataHoraBrasil.getYear(); 
        int horas = dataHoraBrasil.getHour(); 
        int minutos = dataHoraBrasil.getMinute(); 
        
        this.data_inicio = dia + "-" + mes + "-" + ano + "_" + horas + "-" + minutos;
    }
    
    public Requisicao(String id) {
        this(id, null, null, -1, null, null);
    }
    
    public Requisicao() {
        this(null, null, null, -1, null, null);
    }
    
    String requisicao_id;
    String requisitor_cpf;
    String responsavel_cpf;
    String data_inicio;
    int categoria;
    String equipamentos;
    String descricao;
    
    String responsavelString;
    String requisitorString;
    String categoriaString;
    String status;
    
    List<Equipamento> arrEquipamentos = new ArrayList<>();
    
    public void setID(String id) { 
        this.requisicao_id = id; 
    }
    
    public void setRequisitor_cpf(String cpf) { 
        this.requisitor_cpf = cpf; 
    }
    
    public void setResponsavel_cpf(String cpf) { 
        this.responsavel_cpf = cpf; 
    }
    
    public void setData(String data) { 
        this.data_inicio = data; 
    }
    
    public void setCategoria(int categoria) { 
        this.categoria = categoria; 
    }
    
    public void setEquipamentos(String equipamentos) {
        this.equipamentos = equipamentos; 
    }
    
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }
    
    public String getId() { 
        return this.requisicao_id; 
    }
    
    public String getRequisitor_cpf() { 
        return this.requisitor_cpf; 
    }
    
    public String getResponsavel_cpf() { 
        return this.responsavel_cpf; 
    }
    
    public String getData() { 
        return this.data_inicio; 
    }
    
    public int getCategoria() { 
        return this.categoria; 
    }
    
    public String getEquipamentos() { 
        return this.equipamentos; 
    }
    
    public String getDescricao() { 
        return this.descricao; 
    }
    
    public void setCategoriaString(String categoria) {
        categoriaString = categoria;
    }
       
    public String getCategoriaString() {
        return categoriaString;
    }
    
    public void setRequisitorString(String requisitor) {
        requisitorString = requisitor;
    }
    
    public String getRequisitorString() {
        return requisitorString;
    }
    
    public void setResponsavelString(String responsavel) {
        responsavelString = responsavel;
    }
    
    public String getResponsavelString() {
        return responsavelString;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setArrEquipamentos(List<Equipamento> arrEquipamentos) {
        this.arrEquipamentos = arrEquipamentos;
    }
    
    public List<Equipamento> getArrEquipamentos() {
        return arrEquipamentos;
    } 
}