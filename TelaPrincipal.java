package com.mycompany.locadora.telas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private JTabbedPane tabbedPane;
    private JButton btnAlternarTema;
    private boolean temaEscuro = false;

    public TelaPrincipal() {
        initComponents();
        setupFrame();
    }

    private void initComponents() {
        setTitle("Locadora de Automóveis - Sistema de Gestão");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));

        // Botão para alternar tema
        btnAlternarTema = new JButton("🌙 Tema Escuro");
        btnAlternarTema.addActionListener(e -> alternarTema());

        // Painel superior com o botão de tema
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.add(btnAlternarTema);

        tabbedPane = new JTabbedPane();
        
        // Adicionar as telas como abas
        tabbedPane.addTab("Usuarios", new TelaUsuarios());
        tabbedPane.addTab("Veículos", new TelaVeiculos());
        tabbedPane.addTab("Aluguéis", new TelaAlugueis());
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }

    private void alternarTema() {
        try {
            if (temaEscuro) {
                // Mudar para tema claro
                UIManager.setLookAndFeel(new FlatLightLaf());
                btnAlternarTema.setText("🌙 Tema Escuro");
            } else {
                // Mudar para tema escuro
                UIManager.setLookAndFeel(new FlatDarkLaf());
                btnAlternarTema.setText("☀️ Tema Claro");
            }
            
            // Atualizar o tema em todas as janelas
            SwingUtilities.updateComponentTreeUI(this);
            
            temaEscuro = !temaEscuro;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao alterar tema: " + ex.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupFrame() {
        // Configurações adicionais do frame
    }
}