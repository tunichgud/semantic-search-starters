package de.vlyby.core;

import de.vlyby.understand.OnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@SpringBootTest
public class AggregateOnDuplicateTest extends AbstractTestNGSpringContextTests {

    @Autowired
    OnRequest onRequest;


    @Test
    public void testPluralDuplicateIsAggregated() {
        // given
        UserQuery u1 = new UserQuery("ein dummer bär");
        onRequest.process(u1);
        UserQuery u2 = new UserQuery("dumme bären");
        onRequest.process(u2);
        UserQueries queries = new UserQueries(Arrays.asList(u1, u2));

        // when
        AggregateOnDuplicate aggregateOnDuplicate = new AggregateOnDuplicate();
        List<AggregateOnDuplicate.Tokens> result = aggregateOnDuplicate.apply(queries);
        // then
        assertEquals(result.size(), 1);
    }

    @Test
    public void testDuplicatesAreAggregated() {
        // given
        UserQuery u1 = new UserQuery("ein dummer bär");
        onRequest.process(u1);
        UserQuery u2 = new UserQuery("ein dummer bar");
        onRequest.process(u2);
        UserQueries queries = new UserQueries(Arrays.asList(u1, u2));

        // when
        AggregateOnDuplicate aggregateOnDuplicate = new AggregateOnDuplicate();
        List<AggregateOnDuplicate.Tokens> result = aggregateOnDuplicate.apply(queries);
        // then
        assertEquals(result.size(), 1);
    }

    @Test
    public void testNonDuplicatesAreNotAggregated() {
        // given
        UserQuery u1 = new UserQuery("ein dummer bär");
        onRequest.process(u1);
        UserQuery u2 = new UserQuery("zwei dummer bar");
        onRequest.process(u2);
        UserQueries queries = new UserQueries(Arrays.asList(u1, u2));

        // when
        AggregateOnDuplicate aggregateOnDuplicate = new AggregateOnDuplicate();
        List<AggregateOnDuplicate.Tokens> result = aggregateOnDuplicate.apply(queries);
        // then
        assertEquals(result.size(), 2);
    }

    @Test
    public void shouldAggregateIfNotCloseToOneAnother() {
        // given
        UserQuery u1 = new UserQuery("ein dummer bär");
        onRequest.process(u1);
        UserQuery u2 = new UserQuery("zwei dummer bar");
        onRequest.process(u2);
        UserQuery u3 = new UserQuery("ein dummer bären");
        onRequest.process(u3);
        UserQueries queries = new UserQueries(Arrays.asList(u1, u2, u3));

        // when
        AggregateOnDuplicate aggregateOnDuplicate = new AggregateOnDuplicate();
        List<AggregateOnDuplicate.Tokens> result = aggregateOnDuplicate.apply(queries);

        // then
        assertEquals(result.size(), 2);
    }

}