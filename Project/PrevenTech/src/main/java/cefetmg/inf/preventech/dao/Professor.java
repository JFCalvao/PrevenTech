package cefetmg.inf.preventech.dao;


import cefetmg.inf.preventech.services.ProfessorService;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */
public class Professor extends User {
    public Professor(User user) {
        super(user);
    }
    
    private final String initialPage = "professor.jsp";
    private final List<String> basicPages = 
    List.of("meus-dados.jsp","estados.jsp","solicitacao.jsp");
    private final List<String> basicPagesNames = 
    List.of("Meus Dados","Ver equipamentos","Fazer Solicitação");
    private final List<String> hasAccessPages = 
    List.of("meus-dados.jsp","estados.jsp","solicitacao.jsp",
            "cadastrar-maq.jsp", "remover.jsp","finalizarSolicitacao");
    
    public String getInitialPage() { return this.initialPage; }
    public List<String> getMenuOptionsLinks() { return this.basicPages; }
    public List<String> getMenuOptionsNames() { return this.basicPagesNames; }
    public boolean hasAccess(String page) { return hasAccessPages.contains(page); }
}
