package co.edu.unbosque.controller;

import co.edu.unbosque.model.Tokenizer;
import co.edu.unbosque.model.exception.LexerException;
import co.edu.unbosque.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller implements ActionListener {

    private View view;
    private Tokenizer tokenizer;

    public Controller() {
        this.view = new View(this);
        this.tokenizer = new Tokenizer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(view.getFindFilePanel().getCOMMAND_FIND_TOKEN_FILE())) {
            this.view.getFindFilePanel().manageTokenFileBrowse();
        } else if (e.getActionCommand().equals(view.getFindFilePanel().getCOMMAND_FIND_SOURCE_CODE_FILE())) {
            this.view.getFindFilePanel().manageSourceFileBrowse();
        } else if (e.getActionCommand().equals(view.getLexemTablePane().getCOMMAND_TOKENIZE())) {
            this.manageTokenizerAction();
        }
    }

    private void manageTokenizerAction() {
        String tokensFile = this.view.getFindFilePanel().getTokenTxt().getText();
        String sourceFile = this.view.getFindFilePanel().getSourceCodeTxt().getText();
        if (!tokensFile.isEmpty() && !sourceFile.isEmpty()) {
            String tokenizerMessage = this.tokenizer.createTokens(tokensFile);
            if (!tokenizerMessage.isEmpty()) {
                this.view.displayMessageError(tokenizerMessage);
                return;
            }
            try {
                this.tokenizer.tokenize(this.view.getFindFilePanel().getSourceCodeTxt().getText());
                this.view.getLexemTablePane().actualizarTabla(this.tokenizer.generateTokensList());
            } catch (IOException e) {
                this.view.displayMessageError("Ha ocurrido un error al leer el código fuente");
            } catch (LexerException le) {
                this.view.displayMessageError(le.getMessage());
            }
        } else {
            this.view.displayMessageError("Debe seleccionar ambos archivos para poder tokenizar");
        }
    }
}
