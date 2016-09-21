public class SumOfDigits1 {
	public static void main(String[]args) {
		long n = Long.parseLong(args[0]);
		System.out.println(digSum(n));
	}
	public static long digSum(long number) {
		long result = 0;
		while(number != 0) {
			result = result + number%10;
			number = number/10;
		}
		return result;
	}
}