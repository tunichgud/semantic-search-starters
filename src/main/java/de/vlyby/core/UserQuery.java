package de.vlyby.core;

import de.vlyby.understand.impl.AnnotateTokens;

import java.util.List;

public class UserQuery {

    String originalQuery;
    QueryFragments queryFragments;
    List<AnnotateTokens> queryAnnotations;

    public UserQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public QueryFragments getQueryFragments() {
        return queryFragments;
    }

    public void setQueryFragments(QueryFragments queryFragments) {
        this.queryFragments = queryFragments;
    }

    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public List<AnnotateTokens> getQueryAnnotations() {
        return queryAnnotations;
    }

    public void setQueryAnnotations(List<AnnotateTokens> queryAnnotations) {
        this.queryAnnotations = queryAnnotations;
    }


}
