import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    
    @Test
    public void negativeInputReturnsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Negatives not allowed: -1,-2");
        calculator.Add("-1,-2");
    }
    
    @Test
    public void numbersGreaterThan1000AreIgnored() {
        assertEquals(calculator.Add("5,12,1001"), 17);
        assertEquals(calculator.Add("1005,22\n4,1214"), 26);
    }
    
    @Test
    public void zCountAddFunctionCall() {
        assertEquals(10, calculator.getCalledCount());
    }
}