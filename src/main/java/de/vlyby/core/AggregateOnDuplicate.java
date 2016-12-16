package de.vlyby.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AggregateOnDuplicate {

    public List<OnlyTokens> apply(UserQueries userQueries) {
        List<OnlyTokens> sortedTokens = userQueries.getUserQueries()
                .stream()
                .map(query ->
                        OnlyTokens.fromQueryFragments(query.getQueryFragments())
                )
                .sorted(OnlyTokens::compareAlpanumerical)
                .distinct()
                .collect(toList());

        return sortedTokens;
    }

    public final static class OnlyTokens {
        List<Token> tokens;

        OnlyTokens(List<Token> tokens) {
            this.tokens = tokens;
        }

        public static OnlyTokens fromQueryFragments(QueryFragments queryFragments) {
            return new OnlyTokens(queryFragments.asList().stream()
                    .map(t -> new Token(t.getStemmed()))
                    .sorted(Token::compareAlphabetically)
                    .collect(Collectors.toList()));
        }

        public static int compareAlpanumerical(OnlyTokens one, OnlyTokens another) {
            String simpleOne = one          .getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
            String simpleAnother = another  .getTokens().stream().map(t -> t.getToken()).collect(Collectors.joining(" "));
            return simpleOne.compareTo(simpleAnother);
        }

        public void changeOrderUsing(Comparator<Token> comparator) {
            Collections.sort(this.getTokens(), comparator);
        }

        public List<Token> getTokens() {
            return tokens;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OnlyTokens tokens1 = (OnlyTokens) o;

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
