/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import java.util.List;

/**
 *
 * @author jfcalvao
 */
public class Tecnico extends User {
    public Tecnico(User user) {
        super(user);
    }
    
    public boolean canRecieveRequisicao(Requisicao requisicao) throws NoSuchCategoriaException {
        List<String> categorias = Categorias.acceptedCategoriesFor(this.getProfissao());
        if(categorias == null) return false;
        
        int idCategorias = requisicao.getCategoria();
        return categorias.contains(Categorias.getCategoriaString(idCategorias));
    }
}
