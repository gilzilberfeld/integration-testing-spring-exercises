package atd.spring.server.integrationtests.exercises.e1;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.server.configuration.AppConfiguration;
import atd.spring.server.configuration.CheeseControllerConfiguration;
import atd.spring.server.exchange.Exchange;
import atd.spring.server.exchange.RateParser;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {E1_configuration.class })
public class E1_tests {

	@Autowired RateParser parser;
	@Autowired Exchange mockExchange;
	
	final String templateWithSpaces = "^(...)\\s=\\s(\\d+\\.?\\d*)$";
	final String templateEndingWithComma ="^(...)=(\\d+\\.?\\d*)\\,$";
	final String templateEndingWithSemicolon ="^(...)=(\\d+\\.?\\d*)\\;$";
	
	List<String> rates;
	
	@Before
	public void setup( ) {
		rates = new ArrayList<String>();
		Mockito.reset(mockExchange);
	}
	
	@Test
	public void rateWithnoSpaces_areParsedCorrectly() {
		rates.add("ILS=2.5");
		assertExchangeWasCalledWith("ILS", 2.5);
	}

	@Test
	public void rateWithSpaces_areParsedCorrectly() {
		parser.addLineTemplate(templateWithSpaces);
		rates.add("ILS = 2.5");
		assertExchangeWasCalledWith("ILS", 2.5);
	}
	
	@Test
	public void rateEndingWithComma_areParsedCorrectly() {
		parser.addLineTemplate(templateEndingWithComma);
		rates.add("ILS=2.5,");
		assertExchangeWasCalledWith("ILS", 2.5);
	}
	
	@Test
	public void rateEndingWithSemicolon_areParsedCorrectly() {
		parser.addLineTemplate(templateEndingWithSemicolon);
		rates.add("ILS=2.5;");
		assertExchangeWasCalledWith("ILS", 2.5);
	}

	private void assertExchangeWasCalledWith(String currency, double value) {
		parser.parse(rates);
		verify(mockExchange).setRate(currency, BigDecimal.valueOf(value));
	}
	
	
}
