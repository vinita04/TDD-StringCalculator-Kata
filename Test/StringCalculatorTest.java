import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StringCalculatorTest {
	private StringCalculator calculator;
	
	@Before
    public void initialize() {
        calculator = new StringCalculator();
    }
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Test
    public void emptyStringShouldReturn0() {
        assertEquals(calculator.Add(""), 0);
    }

	@Test
	public void numberStringShouldReturnSameNumber() {
        assertEquals(calculator.Add("1"), 1);
    }

    @Test
    public void twoNumbersShouldBeAdded() {
    	assertEquals(3, calculator.Add("1,2"));
    }
    
    @Test
    public void moreDigitsSupported() {
        assertEquals(45, calculator.Add("1,2,3,4,5,6,7,8,9"));
    }
    
    @Test
    public void numbersNewlineDelimitedShouldBeSummed() {
        assertEquals(calculator.Add("1\n2,3"), 6);
        assertEquals(calculator.Add("11\n13\n2"), 26);
    }
    
    @Test
    public void numbersDelimitedSeparatedShouldBeSummed() {
        assertEquals(3, calculator.Add("//;\n1;2"));
    }
    
}