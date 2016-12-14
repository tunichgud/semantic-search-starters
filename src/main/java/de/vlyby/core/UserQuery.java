package de.vlyby.core;

import de.vlyby.understand.impl.AnnotateTokens;

import java.util.List;

public class UserQuery {

    String originalQuery;
    List<QueryFragment> queryFragments;
    List<AnnotateTokens> queryAnnotations;

    public UserQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public List<QueryFragment> getQueryFragments() {
        return queryFragments;
    }

    public void setQueryFragments(List<QueryFragment> queryFragments) {
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
