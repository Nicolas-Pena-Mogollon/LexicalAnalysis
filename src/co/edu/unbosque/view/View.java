/**
 * @author Nicolás Peña Mogollón
 * Esta clase contiene Las vistas de la aplicación
 */

package co.edu.unbosque.view;

import javax.swing.*;

import co.edu.unbosque.controller.Controller;

import java.awt.*;

public class View extends JFrame {

    private FindFilePane findFilePanel;
    private LexemTablePane lexemTablePane;

    /**
     * Constructor de la clase
     *
     * @param control listener de los elementos
     */
    public View(Controller control) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setTitle("Tokenizer");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        this.findFilePanel = new FindFilePane();
        this.lexemTablePane = new LexemTablePane();
        JPanel containerPane = new JPanel(new BorderLayout());
        containerPane.add(this.findFilePanel, BorderLayout.PAGE_START);
        containerPane.add(this.lexemTablePane, BorderLayout.CENTER);

        getContentPane().add(containerPane);
        revalidate();
        repaint();

        this.findFilePanel.getFindTokenFileBtn().addActionListener(control);
        this.findFilePanel.getFindSourceFileBtn().addActionListener(control);
        this.lexemTablePane.getTokenizeBtn().addActionListener(control);
    }

    public void displayMessageError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public FindFilePane getFindFilePanel() {
        return findFilePanel;
    }

    public void setFindFilePanel(FindFilePane findFilePanel) {
        this.findFilePanel = findFilePanel;
    }

    public LexemTablePane getLexemTablePane() {
        return lexemTablePane;
    }

    public void setLexemTablePane(LexemTablePane lexemTablePane) {
        this.lexemTablePane = lexemTablePane;
    }
}
