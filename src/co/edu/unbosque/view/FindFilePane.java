package co.edu.unbosque.view;

import java.awt.GridLayout;
import java.io.File;
import javax.swing.*;


public class FindFilePane extends JPanel {

    private static final long serialVersionUID = 1L;
    private final String COMMAND_FIND_TOKEN_FILE = "FIND_TOKEN_FILE";
    private final String COMMAND_FIND_SOURCE_CODE_FILE = "FIND_SOURCE_CODE_FILE";
    private JLabel tokenLabel;
    private JLabel sourceCodeLabel;
    private JButton findTokenFileBtn;
    private JButton findSourceFileBtn;
    private JTextField tokenTxt;
    private JTextField sourceCodeTxt;

    /**
     * Constructor de la clase
     */
    public FindFilePane() {
        setLayout(new GridLayout(2, 3));
        this.tokenLabel = new JLabel("Archivo de tokens");
        this.sourceCodeLabel = new JLabel("Código fuente");

        this.findTokenFileBtn = new JButton("Browse");
        this.findSourceFileBtn = new JButton("Browse");
        this.findTokenFileBtn.setActionCommand(this.COMMAND_FIND_TOKEN_FILE);
        this.findSourceFileBtn.setActionCommand(this.COMMAND_FIND_SOURCE_CODE_FILE);

        this.tokenTxt = new JTextField();
        this.sourceCodeTxt = new JTextField();
        this.tokenTxt.setEditable(false);
        this.sourceCodeTxt.setEditable(false);

        this.addItems();
    }

    public void manageTokenFileBrowse() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (filePath.toLowerCase().endsWith(".txt")) {
                this.tokenTxt.setText(filePath);
            } else {
                JOptionPane.showMessageDialog(
                        null, "Por favor, selecciona un archivo con extensión .txt.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void manageSourceFileBrowse() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (filePath.toLowerCase().endsWith(".txt")) {
                this.sourceCodeTxt.setText(filePath);
            } else {
                JOptionPane.showMessageDialog(
                        null, "Por favor, selecciona un archivo con extensión .txt.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Agrega los items al Panel
     */
    public void addItems() {
        this.add(tokenLabel);
        this.add(findTokenFileBtn);
        this.add(tokenTxt);
        this.add(sourceCodeLabel);
        this.add(findSourceFileBtn);
        this.add(sourceCodeTxt);
    }

    public String getCOMMAND_FIND_TOKEN_FILE() {
        return COMMAND_FIND_TOKEN_FILE;
    }

    public String getCOMMAND_FIND_SOURCE_CODE_FILE() {
        return COMMAND_FIND_SOURCE_CODE_FILE;
    }

    public JLabel getTokenLabel() {
        return tokenLabel;
    }

    public void setTokenLabel(JLabel tokenLabel) {
        this.tokenLabel = tokenLabel;
    }

    public JLabel getSourceCodeLabel() {
        return sourceCodeLabel;
    }

    public void setSourceCodeLabel(JLabel sourceCodeLabel) {
        this.sourceCodeLabel = sourceCodeLabel;
    }

    public JButton getFindTokenFileBtn() {
        return findTokenFileBtn;
    }

    public void setFindTokenFileBtn(JButton findTokenFileBtn) {
        this.findTokenFileBtn = findTokenFileBtn;
    }

    public JButton getFindSourceFileBtn() {
        return findSourceFileBtn;
    }

    public void setFindSourceFileBtn(JButton findSourceFileBtn) {
        this.findSourceFileBtn = findSourceFileBtn;
    }

    public JTextField getTokenTxt() {
        return tokenTxt;
    }

    public void setTokenTxt(JTextField tokenTxt) {
        this.tokenTxt = tokenTxt;
    }

    public JTextField getSourceCodeTxt() {
        return sourceCodeTxt;
    }

    public void setSourceCodeTxt(JTextField sourceCodeTxt) {
        this.sourceCodeTxt = sourceCodeTxt;
    }
}
