/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.locadora;

import com.formdev.flatlaf.FlatDarkLaf;
import com.mycompany.locadora.telas.TelaPrincipal;
import javax.swing.SwingUtilities;
/**
 *
 * @author LuizM
 */
public class Locadora {

     public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
