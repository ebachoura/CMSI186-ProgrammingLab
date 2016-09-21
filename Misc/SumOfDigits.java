public class SumOfDigits {
	public static void main(String[]args) {
		long n = Long.parseLong(args[0]);
		long result = 0;
		while (n != 0) {
			result = result + n%10;
			n = n/10;
		}
		System.out.println(result);
	}
}