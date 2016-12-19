package de.vlyby.core;

import java.util.List;

public class QueryFragments {

    List<QueryFragment> queryFragments;

    public QueryFragments(List<QueryFragment> queryFragments) {
        this.queryFragments = queryFragments;
    }

    public List<QueryFragment> asList() {
        return queryFragments;
    }

}
