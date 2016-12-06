package de.vlyby.understand.impl;

import de.vlyby.core.QueryFragment;
import de.vlyby.core.UserQuery;
import de.vlyby.understand.AnnotateQueryFragmentsInterface;
import de.vlyby.utils.LuceneUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TokenizeAndStemQuery implements AnnotateQueryFragmentsInterface {

    public TokenizeAndStemQuery() {
    }

    @Override
    public void process(UserQuery userQuery) {

        StandardAnalyzer analyzer = new StandardAnalyzer();

        List<QueryFragment> fragments = LuceneUtil.tokenizeString(analyzer, userQuery.getOriginalQuery()).stream()
                .map(t -> {
                    QueryFragment.QueryFragmentBuilder builder = new QueryFragment.QueryFragmentBuilder();
                    builder.setFragment(t);
                    return builder.build();
                }
        ).collect(Collectors.toList());
        userQuery.setQueryFragments(fragments);
    }
}
