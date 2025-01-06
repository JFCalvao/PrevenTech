/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

import java.util.ArrayList;

/**
 *
 * @author jfcalvao
 */
public class SQLData extends ArrayList<String> {
    @Override
    public String toString() {
        String data = "";
        for(int i = 0; i < this.size(); i++) {
            data += "'";
            data += this.get(i);
            data += "'";
            if(i + 1 != this.size())
                data += ",";
        }
        return data;
    }
}
