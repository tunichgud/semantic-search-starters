package de.vlyby.core.duplicates;

import de.vlyby.core.QueryFragments;

import java.util.List;
import java.util.stream.Collectors;

public class Tokens {
    private final QueryFragments queryFragments;
    List<Token> tokens;

    Tokens(List<Token> tokens, QueryFragments queryFragments) {
        this.tokens = tokens;
        this.queryFragments = queryFragments;
    }

    public static Tokens fromQueryFragments(QueryFragments queryFragments) {
        return new Tokens(queryFragments.asList().stream()
                .map(t -> new Token(t.getStemmed()))
                .sorted(Token::compareAlphabetically)
                .collect(Collectors.toList()), queryFragments);
    }

    public static int compareAlphanumerical(Tokens one, Tokens another) {
        String simpleOne = one.getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
        String simpleAnother = another.getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
        return simpleOne.compareTo(simpleAnother);
    }

    public QueryFragments getQueryFragments() {
        return queryFragments;
    }

    public List<Token> getTokens() {
        return tokens;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tokens tokens1 = (Tokens) o;
        return getTokens() != null ? getTokens().equals(tokens1.getTokens()) : tokens1.getTokens() == null;

    }

    @Override
    public int hashCode() {
        return getTokens() != null ? getTokens().hashCode() : 0;
    }
}
