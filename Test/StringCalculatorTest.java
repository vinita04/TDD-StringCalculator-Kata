import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StringCalculatorTest {
	private StringCalculator calculator;
	@Before
    public void initialize() {
        calculator = new StringCalculator();
    }

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
    
    


}