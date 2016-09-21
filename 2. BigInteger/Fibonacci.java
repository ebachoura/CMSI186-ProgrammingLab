public class Fibonacci {
	public static void main (String[]args) {
		long term = Long.parseLong(args[0]);
		BigInteger previousTerm = ONE;
		BigInteger twoBack = ZERO;
		if (term == 0) {
			previousTerm = ZERO;
		}

		for (int i = 2; i <= term; i++) {
			BigInteger sum = previousTerm.add(twoBack);
			twoBack = previousTerm;
			previousTerm = sum; 
		}
		System.out.println(previousTerm);
	}
	public static final BigInteger ONE = new BigInteger("1");
	public static final BigInteger ZERO = new BigInteger();
}