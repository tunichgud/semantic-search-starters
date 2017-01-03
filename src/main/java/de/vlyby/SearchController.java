package de.vlyby;

import de.vlyby.core.SearchResults;
import de.vlyby.core.UserQueries;
import de.vlyby.core.UserQuery;
import de.vlyby.core.duplicates.AggregateOnDuplicate;
import de.vlyby.core.duplicates.Tokens;
import de.vlyby.understand.OnRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Tokens, List<Tokens>> onDuplicateReduce(@RequestBody String phrase, Model model) throws IOException, SolrServerException {
        UserQueries queries = new UserQueries(Arrays.stream(phrase.split("\n")).map(s -> new UserQuery(s)).peek(q -> onRequest.process(q)).collect(Collectors.toList()));
        AggregateOnDuplicate aggregateOnDuplicate = new AggregateOnDuplicate();
        Map<Tokens, List<Tokens>> result = aggregateOnDuplicate.apply(queries);
        return result;
    }


}
