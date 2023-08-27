package co.edu.unbosque.model;

import java.util.regex.Pattern;

public class TokenInfo {
    private String name;
    private final Pattern regex;
    private int code;

    public TokenInfo(String name, Pattern regex, int code) {
        this.name = name;
        this.regex = regex;
        this.code = code;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "name='" + name + '\'' +
                ", regex=" + regex +
                ", code=" + code +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pattern getRegex() {
        return regex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
