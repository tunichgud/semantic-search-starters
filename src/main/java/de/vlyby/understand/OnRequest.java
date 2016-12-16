package de.vlyby.understand;

import de.vlyby.core.UserQuery;
import de.vlyby.understand.impl.AnnotateTokens;
import de.vlyby.understand.impl.TokenizeAndStemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class OnRequest {

    @Autowired
    AnnotateTokens queryAnnotator ;

    @Autowired
    TokenizeAndStemQuery tokenizeAndStemQuery;

    public void process(UserQuery userQuery){
        tokenizeAndStemQuery.helpUnderstand(userQuery);
        queryAnnotator.helpUnderstand(userQuery);
    }
}
