/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime; 
import java.time.ZoneId;
import java.util.Base64;

/**
 *
 * @author jfcalvao
 */
public class Historico {
    public Historico(String id, String requisitor_cpf, String responsavel_cpf, 
                     String data_fim, String nomeArquivo) {
        this.id = id;
        this.requisitor_cpf = requisitor_cpf;
        this.responsavel_cpf = responsavel_cpf;
        this.data = data_fim;
        this.nomeArquivo = nomeArquivo;
    }
    
    public Historico(String id, String conteudoArquivo) {
        this.id = id;
        if(conteudoArquivo != "") {
            ZoneId fusoHorarioBrasil = ZoneId.of("America/Sao_Paulo"); 
            ZonedDateTime dataHoraBrasil = ZonedDateTime.now(fusoHorarioBrasil); 
            int dia = dataHoraBrasil.getDayOfMonth(); 
            int mes = dataHoraBrasil.getMonthValue(); 
            int ano = dataHoraBrasil.getYear(); 
            int horas = dataHoraBrasil.getHour(); 
            int minutos = dataHoraBrasil.getMinute(); 

            this.data = dia + "-" + mes + "-" + ano + "_" + horas + "-" + minutos;

            this.conteudoArquivo = conteudoArquivo.split(",")[1];

            this.nomeArquivo = "RelatorioFinal(" + id + ")_" + this.data;
        }
    }
    
    public Historico() {}
    
    private String id;
    private String requisitor_cpf;
    private String responsavel_cpf;
    private String data;
    private String conteudoArquivo;
    private String nomeArquivo;
    private File arquivo;
    private final String extension = ".pdf";
    
    public void setId(String id) { this.id = id; }
    public void setRequisitor_cpf(String cpf) { this.requisitor_cpf = cpf; }
    public void setResponsavel_cpf(String cpf) { this.responsavel_cpf = cpf; }
    public void setData(String data) { this.data = data; }
    public void setConteudoArquivo(String conteudo) { this.conteudoArquivo = conteudo; }
    public void setNomeArquivo(String nome) { this.nomeArquivo = nome; }
    
    public void uploadFile(String path) throws IOException {
        File uploadDir = new File(path);
        
        byte[] fileBytes = Base64.getDecoder().decode(conteudoArquivo);
        
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        if(!uploadDir.exists()) {
            System.out.print("-- nao existe --\n");
        }
        
        this.arquivo = new File(path + File.separator + this.nomeArquivo + this.extension);
        
        try(FileOutputStream    outputStream = new FileOutputStream(this.arquivo)) {
            outputStream.write(fileBytes);
            System.out.println("Arquivo salvo em: " + this.arquivo.getAbsolutePath());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getId() { return this.id; }
    public String getRequisitor_cpf() { return this.requisitor_cpf; }
    public String getResponsavel_cpf() { return this.responsavel_cpf; }
    public String getData() { return this.data; }
    public String getNomeArquivo() { return this.nomeArquivo; }
    public String getConteudoArquivo() { return this.conteudoArquivo; }
    public File getFile(String path) {
        String filePath = path + File.separator + this.nomeArquivo + this.extension;
        
        this.arquivo = new File(filePath);
        
        return this.arquivo; 
    }
}
