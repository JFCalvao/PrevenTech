package cefetmg.inf.preventech.dao;


import cefetmg.inf.preventech.services.ProfessorService;

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
    
    public static ProfessorService service = new ProfessorService();
    private static String initial_page = "professor.jsp";
    
    public String getInitialPage() { return this.initial_page; }
}
