package de.vlyby.core;

import java.util.List;

public class QueryFragments {

    public List<QueryFragment> asList() {
        return queryFragments;
    }

    List<QueryFragment> queryFragments;

    public QueryFragments(List<QueryFragment> queryFragments) {
        this.queryFragments = queryFragments;
    }

}
