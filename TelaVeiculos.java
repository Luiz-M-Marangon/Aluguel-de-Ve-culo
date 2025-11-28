package com.mycompany.locadora.telas;

import com.mycompany.locadora.util.Validadores;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaVeiculos extends JPanel {
    private JTable tableVeiculos;
    private DefaultTableModel tableModel;
    private JTextField txtPlaca, txtMarca, txtModelo, txtAno, txtCor;
    private JButton btnSalvar, btnEditar, btnExcluir, btnLimpar;
    
    private List<Object[]> veiculos = new ArrayList<>();

    public TelaVeiculos() {
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));

        panelForm.add(new JLabel("Placa *:"));
        txtPlaca = new JTextField();
        panelForm.add(txtPlaca);

        panelForm.add(new JLabel("Marca *:"));
        txtMarca = new JTextField();
        panelForm.add(txtMarca);

        panelForm.add(new JLabel("Modelo *:"));
        txtModelo = new JTextField();
        panelForm.add(txtModelo);

        panelForm.add(new JLabel("Ano *:"));
        txtAno = new JTextField();
        panelForm.add(txtAno);

        panelForm.add(new JLabel("Cor *:"));
        txtCor = new JTextField();
        panelForm.add(txtCor);

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
        String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Cor"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableVeiculos = new JTable(tableModel);
        tableVeiculos.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tableVeiculos);

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
        btnSalvar.addActionListener(e -> salvarVeiculo());
        btnEditar.addActionListener(e -> editarVeiculo());
        btnExcluir.addActionListener(e -> excluirVeiculo());
        btnLimpar.addActionListener(e -> limparFormulario());

        tableVeiculos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarVeiculoTabela();
            }
        });
    }

    private void salvarVeiculo() {
        if (!validarFormulario()) return;

        String placa = Validadores.formatarPlaca(txtPlaca.getText().trim());
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String ano = txtAno.getText().trim();
        String cor = txtCor.getText().trim();

        // Verificar se placa já existe
        for (Object[] veiculo : veiculos) {
            if (veiculo[0].equals(placa)) {
                JOptionPane.showMessageDialog(this, "Placa já cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Adicionar à lista temporária
        Object[] veiculo = {placa, marca, modelo, ano, cor};
        veiculos.add(veiculo);

        JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
        limparFormulario();
        carregarDados();
    }

    private void editarVeiculo() {
        int selectedRow = tableVeiculos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para editar!");
            return;
        }

        if (!validarFormulario()) return;

        int modelRow = tableVeiculos.convertRowIndexToModel(selectedRow);
        String placa = tableModel.getValueAt(modelRow, 0).toString();
        
        // Atualizar na lista temporária
        for (Object[] veiculo : veiculos) {
            if (veiculo[0].equals(placa)) {
                veiculo[1] = txtMarca.getText().trim();
                veiculo[2] = txtModelo.getText().trim();
                veiculo[3] = txtAno.getText().trim();
                veiculo[4] = txtCor.getText().trim();
                break;
            }
        }

        JOptionPane.showMessageDialog(this, "Veículo atualizado com sucesso!");
        carregarDados();
    }

    private void excluirVeiculo() {
        int selectedRow = tableVeiculos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja excluir este veículo?", "Confirmação", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int modelRow = tableVeiculos.convertRowIndexToModel(selectedRow);
            String placa = tableModel.getValueAt(modelRow, 0).toString();
            
            // Remover da lista temporária
            veiculos.removeIf(veiculo -> veiculo[0].equals(placa));
            
            JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso!");
            limparFormulario();
            carregarDados();
        }
    }

    private void selecionarVeiculoTabela() {
        int selectedRow = tableVeiculos.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = tableVeiculos.convertRowIndexToModel(selectedRow);
            txtPlaca.setText(tableModel.getValueAt(modelRow, 0).toString());
            txtMarca.setText(tableModel.getValueAt(modelRow, 1).toString());
            txtModelo.setText(tableModel.getValueAt(modelRow, 2).toString());
            txtAno.setText(tableModel.getValueAt(modelRow, 3).toString());
            txtCor.setText(tableModel.getValueAt(modelRow, 4).toString());
        }
    }

    private void limparFormulario() {
        txtPlaca.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAno.setText("");
        txtCor.setText("");
        tableVeiculos.clearSelection();
    }

    private boolean validarFormulario() {
        // Validar placa usando o validador
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa é obrigatória!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtPlaca.requestFocus();
            return false;
        }
        
        if (!Validadores.validarPlaca(placa)) {
            JOptionPane.showMessageDialog(this, 
                "Placa inválida!\nFormatos aceitos:\nAAA-0A00 (Mercosul)\nAAA-0000 (Antigo)", 
                "Validação", JOptionPane.WARNING_MESSAGE);
            txtPlaca.requestFocus();
            return false;
        }

        // Validar marca
        String marca = txtMarca.getText().trim();
        if (marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Marca é obrigatória!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtMarca.requestFocus();
            return false;
        }

        // Validar modelo
        String modelo = txtModelo.getText().trim();
        if (modelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Modelo é obrigatório!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtModelo.requestFocus();
            return false;
        }

        // Validar ano usando o validador
        String ano = txtAno.getText().trim();
        if (ano.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ano é obrigatório!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtAno.requestFocus();
            return false;
        }
        
        if (!Validadores.validarAno(ano)) {
            JOptionPane.showMessageDialog(this, 
                "Ano inválido!\nDeve ser um número entre 1900 e 2030", 
                "Validação", JOptionPane.WARNING_MESSAGE);
            txtAno.requestFocus();
            return false;
        }

        // Validar cor
        String cor = txtCor.getText().trim();
        if (cor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cor é obrigatória!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtCor.requestFocus();
            return false;
        }

        return true;
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        for (Object[] veiculo : veiculos) {
            tableModel.addRow(veiculo);
        }
    }

    public List<Object[]> getVeiculos() {
        return veiculos;
    }
}