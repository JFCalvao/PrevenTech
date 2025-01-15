/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author jfcalvao
 */
public class Categorias {
    private final static List<String> falhasEletronicas = List.of(
        "Desgaste mecânico", "Circuitos danificados",
        "Tela com defeito", "Botões quebrados",
        "Computador não liga"
    );

    private final static List<String> falhasInformatica = List.of(
        "Problemas de software", "Falhas na rede",
        "Atualizações faltando", "Configurações erradas",
        "Manutenção de hardware"
    );
    
    public static List<String> acceptedCategoriesFor(String profissao) {
        switch(profissao) {
            case "Tecnico Informatica": return falhasInformatica;
            case "Tecnico Eletronica": return falhasEletronicas;
            default: return null;
        }
    }
    
    public static int getCategoriaCode(String categoria) throws NoSuchCategoriaException {
        int code = 0;
        
        if(falhasEletronicas.indexOf(categoria) != -1) {
            code = falhasEletronicas.indexOf(categoria);
        } else if(falhasInformatica.indexOf(categoria) != -1) {
            code = falhasEletronicas.size() + falhasInformatica.indexOf(categoria);
        } else {
            throw new NoSuchCategoriaException();
        }
        
        return code;
    }
    
    public static String getCategoriaString(int code) throws NoSuchCategoriaException {
        String categoria = "";
        
        if(code < falhasEletronicas.size()) {
            categoria = falhasEletronicas.get(code);
        } else if((code - falhasEletronicas.size()) < falhasInformatica.size()) {
            categoria = falhasInformatica.get((code - falhasEletronicas.size()));
        } else {
            throw new NoSuchCategoriaException();
        }
        
        return categoria;
    }
    
    public static JSONArray getCategorias() {
        JSONArray categorias = new JSONArray();
        
        for(String categoria : falhasEletronicas)
            categorias.put(categoria);
        
        for(String categoria : falhasInformatica)
            categorias.put(categoria);
        
        return categorias;
    }
}
