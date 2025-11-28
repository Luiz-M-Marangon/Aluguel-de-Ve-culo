package com.mycompany.locadora.telas;

import com.mycompany.locadora.util.Validadores;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelaAlugueis extends JPanel {
    private JTable tableAlugueis;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboUsuarios, comboVeiculos;
    private JTextField txtDataInicio, txtDataFim, txtKmInicial, txtKmFinal;
    private JButton btnAbrir, btnFechar, btnLimpar, btnAtualizar;
    
    private List<Object[]> alugueis = new ArrayList<>();
    private Long nextId = 1L;

    public TelaAlugueis() {
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados do Aluguel"));

        panelForm.add(new JLabel("Usuario *:"));
        comboUsuarios = new JComboBox<>();
        panelForm.add(comboUsuarios);

        panelForm.add(new JLabel("Veículo *:"));
        comboVeiculos = new JComboBox<>();
        panelForm.add(comboVeiculos);

        panelForm.add(new JLabel("Data Início:"));
        txtDataInicio = new JTextField();
        txtDataInicio.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        panelForm.add(txtDataInicio);

        panelForm.add(new JLabel("Data Fim:"));
        txtDataFim = new JTextField();
        txtDataFim.setText(LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        panelForm.add(txtDataFim);

        panelForm.add(new JLabel("Km Inicial *:"));
        txtKmInicial = new JTextField();
        panelForm.add(txtKmInicial);

        panelForm.add(new JLabel("Km Final:"));
        txtKmFinal = new JTextField();
        txtKmFinal.setEnabled(false);
        panelForm.add(txtKmFinal);

        panelForm.add(new JLabel("")); // Espaço vazio
        btnAtualizar = new JButton("🔄 Atualizar Listas");
        panelForm.add(btnAtualizar);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        btnAbrir = new JButton("Abrir Aluguel");
        btnFechar = new JButton("Fechar Aluguel");
        btnLimpar = new JButton("Limpar");

        panelBotoes.add(btnAbrir);
        panelBotoes.add(btnFechar);
        panelBotoes.add(btnLimpar);

        // Tabela
        String[] colunas = {"ID", "Usuario", "Veículo", "Data Início", "Data Fim", "Km Inicial", "Km Final", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableAlugueis = new JTable(tableModel);
        tableAlugueis.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tableAlugueis);

        // Layout principal
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelForm, BorderLayout.CENTER);
        panelNorte.add(panelBotoes, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners
        configurarListeners();
        atualizarCombos();
    }

    private void configurarListeners() {
        btnAbrir.addActionListener(e -> abrirAluguel());
        btnFechar.addActionListener(e -> fecharAluguel());
        btnLimpar.addActionListener(e -> limparFormulario());
        btnAtualizar.addActionListener(e -> atualizarCombos());

        tableAlugueis.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarAluguelTabela();
            }
        });
    }

    private void atualizarCombos() {
        comboUsuarios.removeAllItems();
        comboVeiculos.removeAllItems();
        
        TelaUsuarios telaUsuarios = getTelaUsuarios();
        if (telaUsuarios != null) {
            for (Object[] usuario : telaUsuarios.getUsuarios()) {
                String nomeUsuario = usuario[1].toString();
                comboUsuarios.addItem(nomeUsuario);
            }
        }
        
        TelaVeiculos telaVeiculos = getTelaVeiculos();
        if (telaVeiculos != null) {
            for (Object[] veiculo : telaVeiculos.getVeiculos()) {
                String infoVeiculo = veiculo[1] + " " + veiculo[2] + " - " + veiculo[0];
                comboVeiculos.addItem(infoVeiculo);
            }
        }
        
        if (comboUsuarios.getItemCount() == 0) {
            comboUsuarios.addItem("Nenhum usuário cadastrado");
        }
        if (comboVeiculos.getItemCount() == 0) {
            comboVeiculos.addItem("Nenhum veículo cadastrado");
        }
    }

    private TelaUsuarios getTelaUsuarios() {
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof JTabbedPane) {
                JTabbedPane tabbedPane = (JTabbedPane) parent;
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    Component comp = tabbedPane.getComponentAt(i);
                    if (comp instanceof TelaUsuarios) {
                        return (TelaUsuarios) comp;
                    }
                }
            }
            parent = parent.getParent();
        }
        return null;
    }

    private TelaVeiculos getTelaVeiculos() {
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof JTabbedPane) {
                JTabbedPane tabbedPane = (JTabbedPane) parent;
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    Component comp = tabbedPane.getComponentAt(i);
                    if (comp instanceof TelaVeiculos) {
                        return (TelaVeiculos) comp;
                    }
                }
            }
            parent = parent.getParent();
        }
        return null;
    }

    private void abrirAluguel() {
        if (!validarFormularioAbertura()) return;

        String usuario = (String) comboUsuarios.getSelectedItem();
        String veiculo = (String) comboVeiculos.getSelectedItem();
        String dataInicio = txtDataInicio.getText();
        String dataFim = txtDataFim.getText();
        String kmInicial = txtKmInicial.getText();

        if ("Nenhum usuário cadastrado".equals(usuario)) {
            JOptionPane.showMessageDialog(this, "Cadastre usuários primeiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("Nenhum veículo cadastrado".equals(veiculo)) {
            JOptionPane.showMessageDialog(this, "Cadastre veículos primeiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] aluguel = {nextId++, usuario, veiculo, dataInicio, dataFim, kmInicial, "", "ABERTO"};
        alugueis.add(aluguel);

        JOptionPane.showMessageDialog(this, "Aluguel aberto com sucesso!");
        limparFormulario();
        carregarDados();
    }

    private void fecharAluguel() {
        int selectedRow = tableAlugueis.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluguel para fechar!");
            return;
        }

        int modelRow = tableAlugueis.convertRowIndexToModel(selectedRow);
        String status = tableModel.getValueAt(modelRow, 7).toString();
        
        if (!"ABERTO".equals(status)) {
            JOptionPane.showMessageDialog(this, "Este aluguel já está fechado!");
            return;
        }

        String kmFinalStr = JOptionPane.showInputDialog(this, "Informe a quilometragem final:");
        
        if (kmFinalStr != null && !kmFinalStr.trim().isEmpty()) {
            try {
                // Validar km final usando o validador
                if (!Validadores.validarNumeroPositivo(kmFinalStr)) {
                    JOptionPane.showMessageDialog(this, "Quilometragem final deve ser um número positivo!");
                    return;
                }
                
                int kmFinal = Integer.parseInt(kmFinalStr.trim());
                Long id = (Long) tableModel.getValueAt(modelRow, 0);
                
                String kmInicialStr = tableModel.getValueAt(modelRow, 5).toString();
                int kmInicial = Integer.parseInt(kmInicialStr);
                if (kmFinal < kmInicial) {
                    JOptionPane.showMessageDialog(this, "Km final não pode ser menor que km inicial!");
                    return;
                }
                
                for (Object[] aluguel : alugueis) {
                    if (aluguel[0].equals(id)) {
                        aluguel[6] = kmFinalStr;
                        aluguel[7] = "FECHADO";
                        break;
                    }
                }

                String usuario = tableModel.getValueAt(modelRow, 1).toString();
                String veiculo = tableModel.getValueAt(modelRow, 2).toString();
                
                String resumo = String.format(
                    "RESUMO DO ALUGUEL\n\n" +
                    "Usuario: %s\n" +
                    "Veículo: %s\n" +
                    "Km Inicial: %s\n" +
                    "Km Final: %s\n" +
                    "Km Percorrido: %d\n" +
                    "Status: FECHADO",
                    usuario, veiculo, kmInicialStr, kmFinalStr, 
                    kmFinal - kmInicial
                );
                
                JOptionPane.showMessageDialog(this, resumo, "Resumo do Aluguel", JOptionPane.INFORMATION_MESSAGE);
                carregarDados();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quilometragem final inválida!");
            }
        }
    }

    private void selecionarAluguelTabela() {
        int selectedRow = tableAlugueis.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = tableAlugueis.convertRowIndexToModel(selectedRow);
            String status = tableModel.getValueAt(modelRow, 7).toString();
            btnFechar.setEnabled("ABERTO".equals(status));
            
            Object kmFinalObj = tableModel.getValueAt(modelRow, 6);
            if (kmFinalObj != null && !kmFinalObj.toString().isEmpty()) {
                txtKmFinal.setText(kmFinalObj.toString());
            } else {
                txtKmFinal.setText("");
            }
        }
    }

    private void limparFormulario() {
        if (comboUsuarios.getItemCount() > 0) comboUsuarios.setSelectedIndex(0);
        if (comboVeiculos.getItemCount() > 0) comboVeiculos.setSelectedIndex(0);
        txtDataInicio.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtDataFim.setText(LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        txtKmInicial.setText("");
        txtKmFinal.setText("");
        tableAlugueis.clearSelection();
        btnFechar.setEnabled(false);
    }

    private boolean validarFormularioAbertura() {
        if (comboUsuarios.getSelectedItem() == null || 
            "Nenhum usuário cadastrado".equals(comboUsuarios.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Selecione um usuario válido!");
            return false;
        }

        if (comboVeiculos.getSelectedItem() == null || 
            "Nenhum veículo cadastrado".equals(comboVeiculos.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo válido!");
            return false;
        }

        // Validar km inicial usando o validador
        String kmInicial = txtKmInicial.getText().trim();
        if (kmInicial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quilometragem inicial é obrigatória!", "Validação", JOptionPane.WARNING_MESSAGE);
            txtKmInicial.requestFocus();
            return false;
        }
        
        if (!Validadores.validarNumeroPositivo(kmInicial)) {
            JOptionPane.showMessageDialog(this, 
                "Quilometragem inicial inválida!\nDeve ser um número positivo.", 
                "Validação", JOptionPane.WARNING_MESSAGE);
            txtKmInicial.requestFocus();
            return false;
        }

        return true;
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        for (Object[] aluguel : alugueis) {
            tableModel.addRow(aluguel);
        }
    }

    public List<Object[]> getAlugueis() {
        return alugueis;
    }
}