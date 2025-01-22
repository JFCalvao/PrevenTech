/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dto;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.Encryption;

public class Remover {

    public boolean removerEquipamento(String nPatrimonio) throws EncryptationException {
        String encryptedPatrimonio = Encryption.encrypt(nPatrimonio);
        String sql = "DELETE FROM equipamentos WHERE n_patrimonio = '" + encryptedPatrimonio + "'";

        try (var conn = DatabaseManager.getConnection()) {
            try (var stmt = conn.prepareStatement(sql)) {
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
