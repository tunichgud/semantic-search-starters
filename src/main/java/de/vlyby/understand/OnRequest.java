package de.vlyby.understand;

import de.vlyby.core.UserQuery;
import de.vlyby.understand.impl.QueryFragmentsAnnotate;
import de.vlyby.understand.impl.TokenizeAndStemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OnRequest {

    @Autowired
    QueryFragmentsAnnotate queryAnnotator ;

    @Autowired
    TokenizeAndStemQuery annotateQueryFragments;

    public void process(UserQuery userQuery){
        annotateQueryFragments.process(userQuery);
        queryAnnotator.process(userQuery);
    }
}
