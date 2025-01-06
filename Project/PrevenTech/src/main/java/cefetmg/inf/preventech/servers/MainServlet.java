/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.DataManager;
import cefetmg.inf.preventech.util.SQLData;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    private static Map<String, Integer> Users = new HashMap();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JSONObject json = new JSONObject(request.getParameter("json"));
        
        String type = json.getString("type");
        String operation = json.getString("operation");
        JSONObject content = json.getJSONObject("content");
        String tableName = getTableName(type);
        
        JSONObject jsonResponse = new JSONObject();
        
        try {
            if(operation.equals("INSERT")) {
                String[] params = getParams(0, type, tableName, content);
                
                SQLData formattedData = DataManager.format(params);
                DatabaseManager.insert(tableName, formattedData);
                
                List<SQLData> list = DatabaseManager.getAll(tableName);
                for(int i = 0; i < list.size(); i++)
                    jsonResponse.put(String.valueOf(i), DataManager.unformat(list.get(i)));

            } else if(operation.equals("GET")) {
                String[] params = getParams(1, type, tableName, content);
                
                List<SQLData> list = DatabaseManager.getAll(tableName);
                
                JSONArray data = new JSONArray();
                for(int i = 0; i < list.size(); i++) {
                    JSONObject obj = new JSONObject();
                    String[] arrData = DataManager.unformat(list.get(i));
                    String[] columns = DatabaseManager.getColumns(tableName);
                    
                    for(int j = 0; j < arrData.length; j++) 
                        obj.put(columns[j], arrData[j]);
                    
                    data.put(obj);
                }
                
                jsonResponse.put("status", "OK");
                jsonResponse.put("error", "NOERROR");
                jsonResponse.put("content",data);
            } else if(operation.equals("REMOVE")) {
                
            } else {

            }
        } catch(Exception e) {
           e.printStackTrace();
        } finally {
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                System.out.println("Response: " + jsonResponse);
                out.print(jsonResponse);
            }    
        }
    }
    
    private String getTableName(String type) {
        switch(type) {
            case "EQ": return "equipamentos";
            case "RQ": return "requisicoes";
            case "HS": return "historico";
            case "US": return "users";
            case "CH": return "chats";
            default: break;
        }
        return "";
    }
    
    private String[] getParams(int operation, String type, String tableName, JSONObject content) 
                     throws NoSuchTableException {
        String[] params = null;
        String[] columns = DatabaseManager.getColumns(tableName);

        if(operation == 0)         /*============[INSERT Operation]===========*/
            switch(type) {
                case "EQ":         /* * * * * * * * * * * * * * * * * * * * * */ 
                case "RQ":         /*------[All are treated the same way]-----*/
                case "HS":         /*------[Because to insert in Database]----*/
                case "US":         /*--------[you need all columns info]------*/
                case "CH":         /* * * * * * * * * * * * * * * * * * * * * */
                    params = new String[columns.length];
                    for(int i = 0; i < columns.length; i++) {
                        params[i] = content.getString(columns[i]);
                    }
                default: break;
            }
        else if(operation == 1)    /*==============[GET Operation]============*/
            switch(type) {         
                case "US":         /*------------[Login operation]------------*/     
                break;
                case "CH":         /*-----------[Getting chat msgs]-----------*/     
                break;
                case "EQ": break;  /* * * * * * * * * * * * * * * * * * * * * */                                
                case "RQ": break;  /*-------------[No Use at all]-------------*/
                case "HS": break;  /* * * * * * * * * * * * * * * * * * * * * */
                default: break;
            }
        else
            switch(type) {
                case "EQ":         /* * * * * * * * * * * * * * * * * * * * * */ 
                case "RQ":         /*------[All are treated the same way]-----*/
                case "HS":         /*------[Because to remove in Database]----*/
                case "US":         /*--------[you need all columns info]------*/
                case "CH":         /* * * * * * * * * * * * * * * * * * * * * */
                    params = new String[columns.length];
                    for(int i = 0; i < columns.length; i++) {
                        params[i] = content.getString(columns[i]);
                    }
                default: break;
            }
        return params;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
