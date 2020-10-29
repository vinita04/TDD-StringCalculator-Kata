
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.Arrays.asList;

class StringCalculator {
	private static final String PIPE_DELIMITER = "|";
    public static final int START_INDEX_OFFSET = 3;
    private Pattern customDelimitersValidator = Pattern.compile("//(.*)\n(.*)");
    private Matcher customDelimitersMatcher;
    private int startIndex;
	
    public int Add(String numbers) {
        if (numbers==null ||numbers.isEmpty()) {
            return 0;
        }
        List<String> numbersList = getNumbers(numbers);
        //System.out.println(sumArray(numbersList));
        return sumArray(numbersList);
    }

    private int sumArray(List<String> numbersList) {
        return numbersList.stream().mapToInt(Integer::parseInt).sum();
    }

	List<String> getNumbers(String string) {
        String delimiters = getDelimiters(string);
        return splitNumbers(string, delimiters);
    }

    private List<String> splitNumbers(String string, String delimiters) {
        if (areCustomDelimitersValid(string)) {
            string = string.substring(startIndex);
        }
        return asList(string.split(delimiters));
    }

    private String getDelimiters(String string) {
        String delimiters = ",|\n";
        if (areCustomDelimitersValid(string)) {
            delimiters += PIPE_DELIMITER + getCustomDelimiters();
        }
        return delimiters;
    }
	
	private String getCustomDelimiters() {
        String customDelimiters = customDelimitersMatcher.group(1);
        startIndex = customDelimiters.length() + START_INDEX_OFFSET;
        return customDelimiters;
    }

    private boolean areCustomDelimitersValid(String string) {
        customDelimitersMatcher=customDelimitersValidator.matcher(string);
        return customDelimitersMatcher.matches();
    }
}