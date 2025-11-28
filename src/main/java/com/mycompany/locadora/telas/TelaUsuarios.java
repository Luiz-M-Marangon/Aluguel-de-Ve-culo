package com.mycompany.locadora.telas;

import com.mycompany.locadora.util.Validadores;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaUsuarios extends JPanel {
    private JTable tableUsuarios;
    private DefaultTableModel tableModel;
    private JTextField txtNome, txtEmail, txtTelefone;
    private JButton btnSalvar, btnEditar, btnExcluir, btnLimpar;
    
    private List<Object[]> usuarios = new ArrayList<>();
    private Long nextId = 1L;

    public TelaUsuarios() {
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados do Usuário"));

        panelForm.add(new JLabel("Nome *:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("Email *:"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);

        panelForm.add(new JLabel("Telefone *:"));
        txtTelefone = new JTextField();
        panelForm.add(txtTelefone);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnLimpar);

        // Tabela
        String[] colunas = {"ID", "Nome", "Email", "Telefone"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableUsuarios = new JTable(tableModel);
        tableUsuarios.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);

        // Layout principal
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelForm, BorderLayout.CENTER);
        panelNorte.add(panelBotoes, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners
        configurarListeners();
    }

    private void configurarListeners() {
        btnSalvar.addActionListener(e -> salvarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnExcluir.addActionListener(e -> excluirUsuario());
        btnLimpar.addActionListener(e -> limparFormulario());

        tableUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarUsuarioTabela();
            }
        });
    }

    private void salvarUsuario() {
        if (!validarFormulario()) return;

        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        // Adicionar à lista temporária
        Object[] usuario = {nextId++, nome, email, telefone};
        usuarios.add(usuario);

        JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
        limparFormulario();
        carregarDados();
    }

    private void editarUsuario() {
        int selectedRow = tableUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar!");
            return;
        }

        if (!validarFormulario()) return;

        int modelRow = tableUsuarios.convertRowIndexToModel(selectedRow);
        Long id = (Long) tableModel.getValueAt(modelRow, 0);
        
        // Atualizar na lista temporária
        for (Object[] usuario : usuarios) {
            if (usuario[0].equals(id)) {
                usuario[1] = txtNome.getText().trim();
                usuario[2] = txtEmail.getText().trim();
                usuario[3] = txtTelefone.getText().trim();
                break;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
        carregarDados();
    }

    private void excluirUsuario() {
        int selectedRow = tableUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja excluir este usuário?", "Confirmação", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = tableUsuarios.convertRowIndexToModel(selectedRow);
            Long id = (Long) tableModel.getValueAt(modelRow, 0);
            
            // Remover da lista temporária
            usuarios.removeIf(usuario -> usuario[0].equals(id));
            
            JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
            limparFormulario();
            carregarDados();
        }
    }

    private void selecionarUsuarioTabela() {
        int selectedRow = tableUsuarios.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = tableUsuarios.convertRowIndexToModel(selectedRow);
            txtNome.setText(tableModel.getValueAt(modelRow, 1).toString());
            txtEmail.setText(tableModel.getValueAt(modelRow, 2).toString());
            txtTelefone.setText(tableModel.getValueAt(modelRow, 3).toString());
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        tableUsuarios.clearSelection();
    }

    private boolean validarFormulario() {
        // Validar nome
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return false;
        }

        // Validar email usando o validador
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email é obrigatório!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        
        if (!Validadores.validarEmail(email)) {
            JOptionPane.showMessageDialog(this, 
                "Email inválido!\nFormato esperado: usuario@dominio.com", 
                "Validação", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        // Validar telefone usando o validador
        String telefone = txtTelefone.getText().trim();
        if (telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telefone é obrigatório!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtTelefone.requestFocus();
            return false;
        }
        
        if (!Validadores.validarTelefone(telefone)) {
            JOptionPane.showMessageDialog(this, 
                "Telefone inválido!\nFormatos aceitos:\n(51) 99999-9999\n51 999999999\n51999999999", 
                "Validação", JOptionPane.WARNING_MESSAGE);
            txtTelefone.requestFocus();
            return false;
        }

        return true;
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        for (Object[] usuario : usuarios) {
            tableModel.addRow(usuario);
        }
    }

    public List<Object[]> getUsuarios() {
        return usuarios;
    }
}