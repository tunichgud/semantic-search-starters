package de.vlyby.core;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AggregateOnDuplicate {

    public List<Tokens> apply(UserQueries userQueries) {
        List<Tokens> sortedTokens = userQueries.getUserQueries()
                .stream()
                .map(query ->
                        Tokens.fromQueryFragments(query.getQueryFragments())
                )
                .sorted(Tokens::compareAlpanumerical)
                .distinct()
                .collect(toList());

        return sortedTokens;
    }

    public final static class Tokens {
        public QueryFragments getQueryFragments() {
            return queryFragments;
        }

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

        public static int compareAlpanumerical(Tokens one, Tokens another) {
            String simpleOne = one.getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
            String simpleAnother = another.getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
            return simpleOne.compareTo(simpleAnother);
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

    public final static class Token {
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

}
