/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.ChatInvalidoException;
import cefetmg.inf.preventech.Exceptions.UsuarioNaoLogadoException;
import cefetmg.inf.preventech.dao.MensagemDAO;
import cefetmg.inf.preventech.dao.RequisicaoDAO;
import cefetmg.inf.preventech.dto.Mensagem;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.UsersList;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

@ServerEndpoint("/chat")
public class Chat {
    private static Map<Session, User> users = new HashMap<>();

    @OnMessage
    public String onMessage(Session userSession, String message) {
        JSONObject response = new JSONObject();
        try {
            JSONObject json = new JSONObject(message);
            JSONObject content = json.getJSONObject("content");
            
            String type = json.getString("type");
            
            
            switch(type) {
                case "INIT": {
                    response.put("type", "INIT");
                    String cpf = content.getString("cpf");
                    Integer id = content.getInt("id");
                    User user = UsersList.get(cpf);
                    
                    if(user == null)
                        throw new UsuarioNaoLogadoException();
                    
                    RequisicaoDAO<String> Rdao = new RequisicaoDAO<>();
                    if(Rdao.has( new Requisicao(String.valueOf(id)) )) {
                        Requisicao rq = Rdao.search(String.valueOf(id));
                        
                        String requisitor = rq.getRequisitor_cpf();
                        String responsavel = rq.getResponsavel_cpf();
                        
                        if(!requisitor.equals(cpf) && !responsavel.equals(cpf))
                            throw new ChatInvalidoException();
                    } else {
                        throw new ChatInvalidoException();
                    }
                    
                    user.setChat(userSession);
                    users.put(userSession, user);

                    MensagemDAO dao = new MensagemDAO();
                    List<Mensagem> list = dao.getAll();
                    JSONArray filtrado = new JSONArray();

                    for(Mensagem m : list) {
                        if(m.getId() == id) {
                            JSONObject obj = new JSONObject();
                            User mUser = DatabaseManager.searchUsuario(m.getUser());
                            if(mUser == null) continue;
                            obj.put("nome", mUser.getNome());
                            obj.put("msg", m.getMensagem());
                            filtrado.put(obj);
                        }
                    }
                    
                    response.put("content", filtrado);
                }
                break;

                case "MSG": {
                    String id = content.getString("id");
                    String text = content.getString("msg");
                    
                    RequisicaoDAO<String> Rdao = new RequisicaoDAO<>();
                    
                    if(Rdao.has( new Requisicao(String.valueOf(id)) )) {
                        Requisicao rq = Rdao.search(id);
                        MensagemDAO Mdao = new MensagemDAO();
                        
                        String requisitor = rq.getRequisitor_cpf();
                        String responsavel = rq.getResponsavel_cpf();
                        
                        User user = users.get(userSession);
                        
                        if(user == null)
                            throw new UsuarioNaoLogadoException();
                        
                        if(requisitor.equals(user.getCPF()) || responsavel.equals(user.getCPF())) {
                            Mensagem msg = new Mensagem(Integer.valueOf(id), user.getCPF(), text);
                            Mdao.create(msg);
                        } else {
                            throw new ChatInvalidoException();
                        }
                        
                        response.put("content", "");
                        
                        String receiverCPF = (user.getCPF().equals(requisitor)) ? responsavel : requisitor;
                        User receiver = UsersList.get(receiverCPF);
                        
                        if(receiver != null) {
                            JSONObject toRec = new JSONObject();
                            toRec.put("type", "MSG");
                            
                            JSONObject obj = new JSONObject();
                            obj.put("nome", receiver.getNome());
                            obj.put("msg", text);
                            
                            toRec.put("content", obj);
                            
                            toRec.put("status", "OK");
                            toRec.put("error", "NOERROR");
                            try {
                                receiver.getChat().getBasicRemote().sendText(toRec.toString());
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                        response.put("type", "RESPONSE");
                    }
                    
                }
                break;

                default: break;
            }
            
            response.put("status", "OK");
            response.put("error", "NOERROR");
        } catch(NullPointerException e) {
            response.put("status", "ERRO");
            response.put("error", "Você não está logado, por favor, tente logar primeoro.");
        } catch(Exception e) {
            response.put("status", "ERRO");
            response.put("error", e.getMessage());
        } finally {
            try {
                userSession.getBasicRemote().sendText(response.toString());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    @OnOpen
    public void onOpen(Session session) {
        
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        users.remove(session);
    }
    
}
