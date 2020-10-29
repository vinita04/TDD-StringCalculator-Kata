public class StringCalculator {
	public static int Add(String numbers){
		if(numbers==null || numbers.isEmpty()){
			return 0;
		}
		else{
			String[] chuncks = numbers.split(",");
			int total = 0;
			for (String n : chuncks) {
				int num = Integer.parseInt(n);
				total += num;            
			}
			return total;
		}
	}
}