/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.services;

import cefetmg.inf.preventech.dao.Professor;
import cefetmg.inf.preventech.util.UsersList;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author jfcalvao
 */
public class ProfessorService {
    public ProfessorService(HttpSession session) {
        this.professor = new Professor(UsersList.get(session));
    }
    
    private Professor professor;
    
    public String getInitialPage() { 
        return professor.getInitialPage(); 
    }
    
    public boolean hasAccess(String page) { 
        return professor.hasAccess(page); 
    }
    
    public List<String> getMenuOptionsNames() {
        return professor.getMenuOptionsNames();
    }
        
    public List<String> getMenuOptionsLinks() {
        return professor.getMenuOptionsLinks();
    }
}
