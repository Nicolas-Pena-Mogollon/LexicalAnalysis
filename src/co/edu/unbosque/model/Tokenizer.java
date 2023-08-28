package co.edu.unbosque.model;

import co.edu.unbosque.model.exception.LexerException;
import co.edu.unbosque.model.persistence.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Tokenizer {

    private List<TokenInfo> tokenInfos;
    private List<Token> tokens;
    private ReadFile readFile;

    public Tokenizer() {
        this.tokens = new ArrayList<>();
        this.readFile = new ReadFile();
    }

    public void createTokens(String tokensPath) throws NumberFormatException, IOException, LexerException, PatternSyntaxException {

        this.tokenInfos = new ArrayList<>();
        ArrayList<String[]> tokens = this.readFile.obtainFileTokensData(tokensPath);
        if (tokens.isEmpty()) throw new LexerException("El formato de tokens es incorrecto");
        for (String[] token : tokens) {
            int code = Integer.parseInt(token[2]);
            if (token[1].startsWith("TEXTO_L1TER4L")) {
                this.tokenInfos.add(new TokenInfo(
                        token[0],
                        Pattern.compile("^(" + Pattern.quote(token[1].replaceAll("TEXTO_L1TER4L", "")) + ")"),
                        code)
                );
            } else {
                this.tokenInfos.add(
                        new TokenInfo(token[0], Pattern.compile("^(" + token[1] + ")"), code));
            }
        }
    }

    public void tokenize(String sourcePath) throws IOException {
        String sourceCode = this.readFile.obtainFileData(sourcePath).trim();
        int totalLength = sourceCode.length();
        tokens.clear();
        while (!sourceCode.isEmpty()) {
            int remaining = sourceCode.length();
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.getRegex().matcher(sourceCode);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    sourceCode = m.replaceFirst("").trim();
                    if (info.getName().contains("COMMENT")) {
                        break;
                    }
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
