/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.dao.Equipamento;
import cefetmg.inf.preventech.dao.Historico;
import cefetmg.inf.preventech.dao.Mensagem;
import cefetmg.inf.preventech.dao.Requisicao;
import cefetmg.inf.preventech.dao.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import javax.crypto.SecretKey;

/**
 *
 * @author jfcalvao
 */
public class DataManager {
    
    public static String formatEquipamento(Equipamento equipamento) throws EncryptationException {
        String nome = equipamento.getNome();
        nome = Encryption.encrypt(nome);

        String n_patrimonio = equipamento.getN_patrimonio();
        n_patrimonio = Encryption.encrypt(n_patrimonio);

        String local = equipamento.getLocal();
        local = Encryption.encrypt(local);

        String estado = equipamento.getEstado();
        estado = Encryption.encrypt(estado);

        return "'" + nome + "'," + 
               "'" + n_patrimonio + "'," +
               "'" + local + "'," +
               "'" + estado + "'";
    }
        
    public static String formatRequisicao(Requisicao requisicao) throws EncryptationException {
        String requisitor = requisicao.getRequisitor_cpf();
        requisitor = Encryption.encrypt(requisitor);

        String responsavel = requisicao.getResponsavel_cpf();
        responsavel = Encryption.encrypt(responsavel);

        String data = requisicao.getData();
        data = Encryption.encrypt(data);

        int categoria = requisicao.getCategoria();

        String equipamentos = requisicao.getEquipamentos();
        equipamentos = Encryption.encrypt(equipamentos);

        String descricao = requisicao.getDescricao();
        descricao = Encryption.encrypt(descricao);

        return "'" + requisitor + "'," +
               "'" + responsavel + "'," +
               "'" + data + "'," +
               "'" + categoria + "'," +
               "'" + equipamentos + "'," +
               "'" + descricao + "'";
    }
        
    public static String formatHistorico(Historico historico) throws EncryptationException {
        String id = historico.getId();
        
        String requisitor = historico.getRequisitor_cpf();
        requisitor = Encryption.encrypt(requisitor);

        String responsavel = historico.getResponsavel_cpf();
        responsavel = Encryption.encrypt(responsavel);

        String data = historico.getData();
        data = Encryption.encrypt(data);

        String arquivo = historico.getNomeArquivo();
        arquivo = Encryption.encrypt(arquivo);

        return "" + id + "," +
               "'" + requisitor + "'," +
               "'" + responsavel + "'," +
               "'" + data + "'," +
               "'" + arquivo + "'";
    }
    
    public static String formatUsuario(User user) throws EncryptationException {
        String nome = user.getNome();
        nome = Encryption.encrypt(nome);
        
        String cpf = user.getCPF();
        cpf = Encryption.encrypt(cpf);
        
        String senha = user.getSenha();
        senha = Encryption.encrypt(senha);
        
        String email = user.getEmail();
        email = Encryption.encrypt(email);
        
        String profissao = user.getProfissao();
        profissao = Encryption.encrypt(profissao);
        
        return "'" + nome + "'," +
               "'" + cpf + "'," +
               "'" + senha + "'," +
               "'" + email + "'," +
               "'" + profissao + "'";
    }
    
    public static String formatMensagem(Mensagem msg) throws EncryptationException {
        int id = msg.getId();
        
        String user = msg.getUser();
        user = Encryption.encrypt(user);
        
        String mensagem = msg.getMensagem();
        mensagem = Encryption.encrypt(mensagem);
        
        return "'" + id + "'," +
               "'" + user + "'," +
               "'" + mensagem + "'";
    }
        
    public static SQLData format(String[] params) throws EncryptationException {
        SQLData formattedData = new SQLData();

        for(int i = 0; i < params.length; i++)
            formattedData.add(Encryption.encrypt(params[i]));

        return formattedData;
    }
    
    public static Equipamento unformatEquipamento(Equipamento equipamento) throws EncryptationException {
        String nome = Encryption.decrypt(equipamento.getNome());
        equipamento.setNome(nome);
        
        String n_patrimonio = Encryption.decrypt(equipamento.getN_patrimonio());
        equipamento.setN_patrimonio(n_patrimonio);
        
        String local = Encryption.decrypt(equipamento.getLocal());
        equipamento.setLocal(local);
        
        String estado = Encryption.decrypt(equipamento.getEstado());
        equipamento.setEstado(estado);
        
        return equipamento;
    }
    
    public static Requisicao unformatRequisicao(Requisicao requisicao) throws EncryptationException {
        String requisitor_cpf = Encryption.decrypt(requisicao.getRequisitor_cpf());
        requisicao.setRequisitor_cpf(requisitor_cpf);
        
        String responsavel_cpf = Encryption.decrypt(requisicao.getResponsavel_cpf());
        requisicao.setResponsavel_cpf(responsavel_cpf);
        
        String data = Encryption.decrypt(requisicao.getData());
        requisicao.setData(data);
        
        String equipamentos = Encryption.decrypt(requisicao.getEquipamentos());
        requisicao.setEquipamentos(equipamentos);
        
        String descricao = Encryption.decrypt(requisicao.getDescricao());
        requisicao.setDescricao(descricao);
        
        return requisicao;
    }
    
    public static Historico unformatHistorico(Historico historico) throws EncryptationException {
        String requisitor_cpf = Encryption.decrypt(historico.getRequisitor_cpf());
        historico.setRequisitor_cpf(requisitor_cpf);
        
        String responsavel_cpf = Encryption.decrypt(historico.getResponsavel_cpf());
        historico.setResponsavel_cpf(requisitor_cpf);
        
        String data = Encryption.decrypt(historico.getData());
        historico.setData(data);
        
        String arquivo = Encryption.decrypt(historico.getNomeArquivo());
        historico.setNomeArquivo(arquivo);
        
        return historico;
    }

    public static User unformatUser(User user) throws EncryptationException {
        String nome = Encryption.decrypt(user.getNome());
        user.setNome(nome);
        
        String cpf = Encryption.decrypt(user.getCPF());
        user.setCPF(cpf);
        
        String senha = Encryption.decrypt(user.getSenha());
        user.setSenha(senha);
        
        String email = Encryption.decrypt(user.getEmail());
        user.setEmail(email);
        
        String profissao = Encryption.decrypt(user.getProfissao());
        user.setProfissao(profissao);
        
        return user;
    }
    
    public static Mensagem unformatMensagem(Mensagem msg) throws EncryptationException {
        String user = Encryption.decrypt(msg.getUser());
        msg.setUser(user);
        
        String mensagem = Encryption.decrypt(msg.getMensagem());
        msg.setMensagem(mensagem);
        
        return msg;
    }
}
