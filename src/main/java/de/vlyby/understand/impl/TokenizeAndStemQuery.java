package de.vlyby.understand.impl;

import de.vlyby.core.QueryFragment;
import de.vlyby.core.QueryFragments;
import de.vlyby.core.UserQuery;
import de.vlyby.understand.QueryUnderstandingInterface;
import de.vlyby.utils.LuceneUtil;
import org.apache.lucene.analysis.de.CustomAnalyzer;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TokenizeAndStemQuery implements QueryUnderstandingInterface {

    public TokenizeAndStemQuery() {
    }

    @Override
    public void helpUnderstand(UserQuery userQuery) {

        CustomAnalyzer analyzer = new CustomAnalyzer();

        List<QueryFragment> fragments = LuceneUtil.tokenizeString(analyzer, userQuery.getOriginalQuery()).stream()
                .map(t -> {
                            QueryFragment.QueryFragmentBuilder builder = new QueryFragment.QueryFragmentBuilder();
                            builder.setFragment(t);
                            return builder.build();
                        }
                ).collect(Collectors.toList());
        userQuery.setQueryFragments(new QueryFragments(fragments));
    }
}

