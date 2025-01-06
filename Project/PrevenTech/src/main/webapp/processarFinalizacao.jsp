<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
    String dbURL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    String dbUser = "seu_usuario";
    String dbPass = "sua_senha";

    String solicitacaoId = request.getParameter("solicitacaoId");
    Part filePart = request.getPart("anexo");

    
    if (filePart == null || filePart.getSize() == 0) {
        out.println("<h3>Erro: Nenhum arquivo enviado. Tente novamente.</h3>");
        return;
    }

    String fileName = filePart.getSubmittedFileName();
    
    
    String filePath = getServletContext().getRealPath("/") + "uploads/" + fileName;

    
    try (InputStream fileContent = filePart.getInputStream();
         FileOutputStream fos = new FileOutputStream(filePath)) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }
    } catch (IOException e) {
        e.printStackTrace();
        out.println("<h3>Erro ao salvar o arquivo. Tente novamente.</h3>");
        return;
    }

    
    try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
        String sql = "UPDATE solicitacoes SET arquivo_relatorio = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, fileName);
            statement.setString(2, solicitacaoId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<h3>Solicitação finalizada com sucesso!</h3>");
            } else {
                out.println("<h3>Erro: Solicitação não encontrada.</h3>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("<h3>Erro ao atualizar a solicitação. Tente novamente.</h3>");
    }
%>

<a href='index.jsp'>Voltar para o início</a>