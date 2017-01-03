package org.apache.lucene.analysis.de;

import com.lucidworks.analysis.AutoPhrasingTokenFilter;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.analysis.util.WordlistLoader;
import org.apache.lucene.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public final class CustomAnalyzer extends StopwordAnalyzerBase {

    public final static String DEFAULT_STOPWORD_FILE = "german_stop.txt";
    public static final String DEFAULT_SYNONYMS_TXT = "synonyms.txt";
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomAnalyzer.class);

    private CharArraySet exclusionSet;

    public CustomAnalyzer() {
        super(CustomAnalyzer.DefaultSetHolder.DEFAULT_SET);
        List<String> exclude = new ArrayList<String>();
        exclusionSet = CharArraySet.unmodifiableSet(CharArraySet.copy(new CharArraySet(exclude, true)));
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new StandardFilter(source);
        result = new LowerCaseFilter(result);

        result = autoPhrasingTokenizer(result);

        result = new SetKeywordMarkerFilter(result, exclusionSet);
        result = new StopFilter(result, stopwords);
        result = new GermanNormalizationFilter(result);
        result = new GermanLightStemFilter(result);
        return new TokenStreamComponents(source, result);
    }

    private TokenStream autoPhrasingTokenizer(TokenStream result) {
        URL in = CustomAnalyzer.class.getClassLoader().getResource(DEFAULT_SYNONYMS_TXT);
        File file = new File(in.getFile());
        try {
            List<String> lines = null;
            lines = FileUtils.readLines(file);
            final CharArraySet phraseSets = new CharArraySet(lines, false);
            result = new AutoPhrasingTokenFilter(result, phraseSets, false);
        } catch (IOException e) {
            LOGGER.error("Unable to load synonyms file that should be shipped with this jar.", e);
        }
        return result;
    }

    private static class DefaultSetHolder {
        private static final CharArraySet DEFAULT_SET;

        static {
            try {
                DEFAULT_SET = WordlistLoader.getSnowballWordSet(IOUtils.getDecodingReader(SnowballFilter.class,
                        DEFAULT_STOPWORD_FILE, StandardCharsets.UTF_8));
            } catch (IOException ex) {
                // default set should always be present as it is part of the
                // distribution (JAR)
                throw new RuntimeException("Unable to load default stopword set");
            }
        }
    }


}
