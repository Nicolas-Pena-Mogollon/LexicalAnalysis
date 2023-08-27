package co.edu.unbosque.model;

import co.edu.unbosque.model.exception.LexerException;
import co.edu.unbosque.model.persistence.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private List<TokenInfo> tokenInfos;
    private List<Token> tokens;
    private ReadFile readFile;

    public Tokenizer() {
        this.tokens = new ArrayList<>();
        this.readFile = new ReadFile();
    }

    public String createTokens(String tokensPath) {
        try {
            this.tokenInfos = new ArrayList<>();
            ArrayList<String[]> tokens = this.readFile.obtainFileTokensData(tokensPath);
            if (tokens.isEmpty()) return "La estructura del archivo es incorrecta";
            for (String[] token : tokens) {
                try {
                    Integer.parseInt(token[2]);
                } catch (NumberFormatException e) {
                    return "El archivo no contiene el formato permitido";
                }
                this.tokenInfos.add(
                        new TokenInfo(token[0], Pattern.compile("^(" + Pattern.quote(token[1]) + ")"), Integer.parseInt(token[2])));
            }
            return "";
        } catch (IOException e) {
            return "Ha ocurrido un error al leer el archivo";
        }
    }

    public void tokenize(String sourcePath) throws IOException {
        String sourceCode = "";
        sourceCode = this.readFile.obtainFileData(sourcePath);
        sourceCode = this.removeComments(sourceCode).trim();
        int totalLength = sourceCode.length();
        tokens.clear();
        while (!sourceCode.isEmpty()) {
            System.out.println(sourceCode);
            int remaining = sourceCode.length();
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.getRegex().matcher(sourceCode);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    sourceCode = m.replaceFirst("").trim();
                    int startPos = totalLength - remaining;
                    tokens.add(new Token(info.getCode(), tok, startPos, startPos + tok.length()));
                    break;
                }
            }
            if (!match) {
                throw new LexerException("Unexpected character in input: " + sourceCode);
            }
        }
    }

    public String removeComments(String text) {
        for (TokenInfo ti : this.tokenInfos) {
            if (!ti.getName().toUpperCase().contains("COMMENT")) {
                break;
            }
            Matcher matcher = ti.getRegex().matcher(text);
            text = matcher.replaceAll("");
        }
        return text;
    }

    public String[][] generateTokensList() {
        String[][] outputList = new String[this.tokens.size()][4];
        for (int i = 0; i < this.tokens.size(); i++) {
            outputList[i][0] = String.valueOf(this.tokens.get(i).getTokenCode());
            outputList[i][1] = this.tokens.get(i).getLexeme();
            outputList[i][2] = String.valueOf(this.tokens.get(i).getStart());
            outputList[i][3] = String.valueOf(this.tokens.get(i).getEnd());
        }
        return outputList;
    }

}
