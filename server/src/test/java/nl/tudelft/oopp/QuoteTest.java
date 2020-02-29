package nl.tudelft.oopp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.entities.Quote;
import nl.tudelft.oopp.repositories.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuoteTest {
    @Autowired
    private QuoteRepository quoterep;

    @Test
    public void saveAndRetrieveQuote() {
        String quoteText = "Tell me and I forget. Teach me and I remember. Involve me and I learn.";
        String quoteAuthor = "Benjamin Franklin";
        Quote quote = new Quote(1, quoteText, quoteAuthor);
        quoterep.save(quote);

        Quote quote2 = quoterep.getOne((long) 1);
        assertEquals(quote, quote2);
    }
}