package de.vlyby;

import de.vlyby.core.SearchResults;
import de.vlyby.core.UserQuery;
import de.vlyby.understand.OnRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SearchController {

    @Autowired
    OnRequest onRequest;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public SearchResults search(@RequestParam(value = "phrase", required = true) String phrase, Model model) throws IOException, SolrServerException {
        return new SearchResults();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/process")
    public UserQuery process(@RequestParam(value = "phrase", required = true) String phrase, Model model) throws IOException, SolrServerException {
        UserQuery userQuery = new UserQuery(phrase);
        onRequest.process(userQuery);
        return userQuery;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/duplicateReduce")
    public UserQuery onDuplicateReduce(@RequestParam(value = "phrase", required = true) String phrase, Model model) throws IOException, SolrServerException {
        UserQuery userQuery = new UserQuery(phrase);
        onRequest.process(userQuery);
        return userQuery;
    }


}
