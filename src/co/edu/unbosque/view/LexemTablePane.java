package co.edu.unbosque.view;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class LexemTablePane extends JPanel {

    private static final long serialVersionUID = 1L;
    private final String COMMAND_TOKENIZE = "TOKENIZE";
    private JButton tokenizeBtn;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JScrollPane scroll;

    /**
     * Constructor de la clase
     */
    public LexemTablePane() {
        this.setBorder(new TitledBorder("Resultados"));
        this.setLayout(new BorderLayout());
        this.tokenizeBtn = new JButton("Tokenizar");
        this.tokenizeBtn.setActionCommand(this.COMMAND_TOKENIZE);

        this.modeloTabla = new DefaultTableModel();
        this.modeloTabla.fireTableStructureChanged();
        this.tabla = new JTable(modeloTabla);
        this.tabla.setEnabled(false);

        this.modeloTabla.addColumn("Token Code");
        this.modeloTabla.addColumn("Lexeme");
        this.modeloTabla.addColumn("Start");
        this.modeloTabla.addColumn("End");

        this.scroll = new JScrollPane(this.tabla);
        this.add(tokenizeBtn, BorderLayout.PAGE_START);
        this.add(scroll);

    }

    /**
     * Actualiza los datos de la tabla
     *
     * @param data información a colocar
     */
    public void actualizarTabla(String[][] data) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < data.length; i++) {
            modeloTabla.addRow(data[i]);
        }
    }

    public String getCOMMAND_TOKENIZE() {
        return COMMAND_TOKENIZE;
    }

    public JButton getTokenizeBtn() {
        return tokenizeBtn;
    }

    public void setTokenizeBtn(JButton tokenizeBtn) {
        this.tokenizeBtn = tokenizeBtn;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }
}
