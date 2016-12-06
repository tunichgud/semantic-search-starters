package de.vlyby.core;

import org.tartarus.snowball.ext.German2Stemmer;

public class QueryFragment {

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
            if (this.fragment == null) {
                throw new NullPointerException("fragment should not be null");
            } else {
                German2Stemmer stemmer = new German2Stemmer();
                stemmer.setCurrent(this.getFragment());
                stemmer.stem();
                return new QueryFragment(this.fragment, stemmer.getCurrent());
            }
        }

        public String getFragment() {
            return fragment;
        }

        public void setFragment(String fragment) {
            this.fragment = fragment;
        }
    }


}
