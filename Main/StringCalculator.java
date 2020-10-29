import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

class StringCalculator {
	private static final String PIPE_DELIMITER = "|";
    public static final int START_INDEX_OFFSET = 3;
    private Pattern customDelimitersValidator = Pattern.compile("//(.*)\n(.*)");
    private Matcher customDelimitersMatcher;
    private int startIndex;
	public static int countAddMethodCall=0;
	
    public int Add(String numbers) {
    	countAddMethodCall++;
    	System.out.println(countAddMethodCall);
        if (numbers==null || numbers.isEmpty()) {
            return 0;
        }
        List<String> numbersList = getNumbers(numbers);
        checkForNegativeNumbers(numbersList);
        //System.out.println(sumArray(numbersList) + " "+numbersList);
        return sumArray(numbersList);
    }
    public int getCalledCount(){
    	return countAddMethodCall;
    }
    private void checkForNegativeNumbers(List<String> numbersList) {
        String negatives = numbersList.stream()
                .filter(s -> s.contains("-"))
                .collect(joining(","));
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
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