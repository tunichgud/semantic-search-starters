package de.vlyby.core.duplicates;

import de.vlyby.core.UserQueries;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AggregateOnDuplicate {

    public Map<Tokens, List<Tokens>> apply(UserQueries userQueries) {
        Map<Tokens, List<Tokens>> sortedTokens = userQueries
                .getUserQueries()
                .stream()
                .map(query ->
                        Tokens.fromQueryFragments(query.getQueryFragments())
                )
                .sorted(Tokens::compareAlphanumerical)
                .collect(Collectors.groupingBy(Function.identity()));
        return sortedTokens;
    }

}