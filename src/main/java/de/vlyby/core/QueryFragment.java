package de.vlyby.core;

import org.tartarus.snowball.ext.German2Stemmer;

import java.util.Optional;

public class QueryFragment {

    public static final String DEFAULT_FRAGMENT = "";
    String fragment;
    String stemmed;

    private QueryFragment(String fragment, String stemmed) {
        this.fragment = fragment;
        this.stemmed = stemmed;
    }

    public String getFragment() {
        return fragment;
    }

    public String getStemmed() {
        return stemmed;
    }

    public static class QueryFragmentBuilder {
        String fragment;

        public QueryFragment build() {
            Optional<String> fragment = Optional.ofNullable(this.fragment);
            German2Stemmer stemmer = new German2Stemmer();
            stemmer.setCurrent(fragment.orElse(DEFAULT_FRAGMENT));
            stemmer.stem();
            return new QueryFragment(this.fragment, stemmer.getCurrent());


        }

        public String getFragment() {
            return fragment;
        }

        public void setFragment(String fragment) {
            this.fragment = fragment;
        }
    }


}
