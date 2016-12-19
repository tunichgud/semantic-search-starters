package de.vlyby.core.duplicates;

public class Token {
    String token;

    public Token(String token) {
        this.token = token;
    }

    public static int compareAlphabetically(Token o1, Token o2) {
        return o1.getToken().compareTo(o2.getToken());
    }

    public String getToken() {
        return token;
    }

    public String toString() {
        return this.token;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        return getToken() != null ? getToken().equals(token1.getToken()) : token1.getToken() == null;

    }

    @Override
    public int hashCode() {
        return getToken() != null ? getToken().hashCode() : 0;
    }
}
