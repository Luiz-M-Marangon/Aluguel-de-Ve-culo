package com.mycompany.locadora.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Map;

public class Renderers extends DefaultTableCellRenderer {
    private final Map<String, Color> palette;

    public Renderers(Map<String, Color> palette) {
        this.palette = palette;
        setOpaque(true);
    }

    public Renderers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Color bg = table.getBackground();
        if (value instanceof Integer argb) {
            bg = new Color(argb, true);
        } else if (value instanceof String colorName) {
            bg = palette.getOrDefault(colorName.toLowerCase(), table.getBackground());
        }
        
        setBackground(isSelected ? table.getSelectionBackground() : bg);
        setText(""); // cor como chip
        return this;
    }
}

// Renderer para status com ícone
class StatusIconRenderer extends DefaultTableCellRenderer {
    private final Icon iconAberto;
    private final Icon iconFechado;
    private final Icon iconAtraso;

    public StatusIconRenderer() {
        // Ícones simples (podem ser substituídos por imagens reais)
        this.iconAberto = criarIcon(Color.GREEN);
        this.iconFechado = criarIcon(Color.BLUE);
        this.iconAtraso = criarIcon(Color.RED);
        setHorizontalAlignment(CENTER);
    }

    private Icon criarIcon(Color color) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(color);
                g.fillOval(x, y, 12, 12);
            }

            @Override
            public int getIconWidth() { return 12; }

            @Override
            public int getIconHeight() { return 12; }
        };
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        setText(null);
        setHorizontalAlignment(CENTER);
        
        switch (String.valueOf(value).toUpperCase()) {
            case "ABERTO" -> setIcon(iconAberto);
            case "FECHADO" -> setIcon(iconFechado);
            case "ATRASADO" -> setIcon(iconAtraso);
            default -> setIcon(null);
        }
        return this;
    }
}