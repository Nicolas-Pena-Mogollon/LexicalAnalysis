package co.edu.unbosque.model;

public class Token {
    private int tokenCode;
    private String lexeme;
    private int start;
    private int end;

    public Token(int tokenCode, String lexeme, int start, int end) {
        this.tokenCode = tokenCode;
        this.lexeme = lexeme;
        this.start = start;
        this.end = end;
    }

    public int getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(int tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
