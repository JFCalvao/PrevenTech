/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.services;

import cefetmg.inf.preventech.dto.Professor;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.List;

/**
 *
 * @author jfcalvao
 */
public class ProfessorService implements UserService {

    public ProfessorService(Professor professor) {
        this.nome = professor.getNome();
        this.cpf = professor.getCPF();
        this.email = professor.getEmail();
        this.profissao = professor.getProfissao();
        this.serverSession = professor.getServer();
        this.chatSession = professor.getChat();
    }
    
    private final String initialPage = "professor.jsp";
    
    private final List<String> access = List.of(
        "professor.jsp", "estados.jsp", "solicitacao.jsp",
        "meus-dados.jsp", "historicos.jsp", "requisicoes.jsp",
        "minhasRequisicoes.jsp", "chat.jsp"
    );
    
    @Override
    public String getInitialPage() {
        return this.initialPage;
    }
    
    @Override
    public boolean hasAccess(String page) {
        return access.contains(page);
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
