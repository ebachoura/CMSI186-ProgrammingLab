import java.util.Arrays;

public class BigInteger {
	private boolean sign = true;
	//true = postive
	//false = negative
	private int[] digits;
	private int length;
	
	public static void main (String[]args) {
		
	}
	public BigInteger (String number) {
		int index = 0;
		if (!(isNumber(number))) {
			throw new IllegalArgumentException("Invalid Input!");
		}
		if(number.charAt(index) == '-'){
			this.sign = false;
			index++;
		}

		while (number.charAt(index) == '0'){
			index++;
		}
		String newString = number.substring(index, number.length());

		index = 0;
		int length = newString.length();
		this.digits = new int[length];
		for (int i = length - 1; i >= 0; i--) {

			char c = newString.charAt(i);

			this.digits[index] = Character.getNumericValue(c);
			index++;
		}
		this.length = this.digits.length;
	}

	public BigInteger () {
		this.digits = new int[]{0};
		this.length = 1;
		this.sign = true;
	}

	public BigInteger (boolean sign, int[] numbers) {
		this.digits = numbers;
		this.sign = sign;
		this.length = this.digits.length;
	}

	public static final BigInteger ZERO = new BigInteger();
	public static final BigInteger ONE = new BigInteger("1");
	public static final BigInteger TEN = new BigInteger("10");
	
	public String display() {
		return this.sign + " " + Arrays.toString(this.digits);
	}

	public String toString() {
		String result = "";
		for (int i = this.digits.length-1; i >= 0; i--) {
			result = result + Integer.toString(this.digits[i]);
		}
		return (this.sign == true) ? result : "-" + result;
	}

	public BigInteger positive() {
		this.sign = true;
		return this;
	}
	public BigInteger negative() {
		this.sign = false;
		return this;
	}

	public boolean getSign() {
		return this.sign;
	}
	public int[] getDigits() {
		return this.digits;
	}
	public int getLength() {
		return this.length;
	}

	public boolean isNumber (String s) {
		return s.matches("[-]?\\d+");
	}

	public int compareTo (BigInteger value) {
		if (this.equals(value)) {
			return 0;
		}
		if (this.sign == true && value.getSign() == false) {
			return 1;
		} else if (this.sign == false && value.getSign() == true) {
			return -1;
		}
		if (this.digits.length > value.getLength()) {
			if (this.sign == false && value.getSign() == false) {
				return -1;
			}
			return 1;
		} else if (this.digits.length < value.getLength()) {
			if (this.sign == false && value.getSign() == false) {
				return 1;
			}
			return -1;
		} else {
			for (int i = this.digits.length-1; i >= 0; i--) {
				if (this.digits[i] > value.getDigits()[i]) {
					if (this.sign == false && value.getSign() == false) {
						return -1;
					}
					return 1;
				} else if (this.digits[i] < value.getDigits()[i]) {
					if (this.sign == false && value.getSign() == false) {
						return 1;
					}
					return -1;
				}
			}
		}
		return 3;
	}

	public boolean equals (Object x) {
		if (!(x instanceof BigInteger)) {
			return false;
		}
		return this.toString().equals(x.toString());
	}

	public static BigInteger valueOf (long value) {
		String s = Long.toString(value);
		return new BigInteger(s);
	}

	public BigInteger addition (BigInteger value) {
		int remainder = 0;
		int longLength = (this.length > value.getLength()) ? this.length : value.getLength();
		String stringResult = "";
		for (int i = 0; i < longLength + 1; i++) {
			int total;
			int first = 0;
			int second = 0;

			if (i <= this.length - 1) {
				first = this.digits[i];
			}
			if (i <= value.getLength() - 1) {
				second = value.getDigits()[i];
			}

			total = first + second + remainder;
			remainder = total >= 10 ? 1 : 0;
			first = 0;
			second = 0;
			total = total%10;

			String additions = Integer.toString(total);
			stringResult = additions + stringResult;
		}
		BigInteger result = new BigInteger(stringResult);
		return result;
	}

	public BigInteger add (BigInteger value) {
		BigInteger answer = ZERO;
		if (this.sign == false && value.getSign() == true) {
			BigInteger a = new BigInteger(true, this.digits);
			answer = value.subtract(a);
		}
		if (this.sign == true && value.getSign() == false) {
			BigInteger a = new BigInteger(true, value.getDigits());
			answer = this.subtract(a);
		}
		if (this.sign == false && value.getSign() == false) {
			answer = this.addition(value);
			answer.sign = false;
		}
		if (this.sign == true && value.getSign() == true) {
			answer = this.addition(value);
		}
		return answer;
	}

	public BigInteger subtraction (BigInteger value) {
		int borrow = 0;
		String stringResult = "";
		for (int i = 0; i <= this.length - 1; i++) {
			int total;
			int first = 0;
			int second = 0;

			if (i <= value.getLength() - 1) {
				second = value.getDigits()[i];
			}
			if (i <= this.length - 1) {
				if (this.digits[i] - borrow < second) {
					first = this.digits[i] + 10 - borrow;
					borrow = 1;
				} else {
					first = this.digits[i] - borrow;
					borrow = 0;
				}
			}

			total = first - second;
			first = 0;
			second = 0;

			String subtractions = Integer.toString(total);
			stringResult = subtractions + stringResult;
		}
		BigInteger result = new BigInteger(stringResult);
		return result;
	}

	public BigInteger subtract (BigInteger value) {
		BigInteger answer = ZERO;
		if (this.sign == true && value.getSign() == true && this.compareTo(value) == 1) {
			answer = this.subtraction(value);
		}
		if (this.sign == true && value.getSign() == true && this.compareTo(value) == -1) {
			answer = value.subtraction(this);
			answer.sign = false;
		}
		if (this.sign == false && value.getSign() == true) {
			answer = this.addition(value);
			answer.sign = false;
		}
		if (this.sign == true && value.getSign() == false) {
			answer = this.addition(value);
		}
		if (this.sign == false && value.getSign() == false && this.compareTo(value) == -1) {
			BigInteger a = new BigInteger(true, value.getDigits());
			BigInteger b = new BigInteger(true, this.digits);
			answer = b.subtraction(a);
			answer.sign = false;
		}
		if (this.sign == false && value.getSign() == false && this.compareTo(value) == 1) {
			BigInteger a = new BigInteger(true, value.getDigits());
			BigInteger b = new BigInteger(true, this.digits);
			answer = a.subtraction(b);
		}
		if (this.compareTo(value) == 0) {
			answer = ZERO;
		}
		return answer;
	}

	public BigInteger twice () {
		return this.add(this);
	}

	
	public BigInteger halve() {
		int carry = 0;
		String stringResult = "";
		if (this.compareTo(ONE) == 0) {
			return ZERO;
		}
		for (int i = this.length - 1; i >= 0; i--) {
			int total;
			int half;
			
			half = this.digits[i] / 2;
			total = half + carry;
			if ((((double)this.digits[i])/2)%1 == 0.5) {
				carry = 5;
			} else {
				carry = 0;
			}
			String s = Integer.toString(total);
			stringResult = stringResult + s;
		}
		BigInteger result = new BigInteger(stringResult);
		return result;
	}
	
	public BigInteger multiply (BigInteger value) {
		boolean theSign = true;
		if (this.sign == false && value.getSign() == true || this.sign == true && value.getSign() == false) {
			theSign = false;
		}
		this.sign = true;
		BigInteger a = this;
		BigInteger b = value.positive();
		BigInteger big;
		BigInteger small;
		if (a.compareTo(b) == 1 || a.compareTo(b) == 0) {
			big = a;
			small = b;
		} else {
			big = b;
			small = a;
		}
		BigInteger total = ZERO;

		if (small.getDigits()[0] % 2 != 0) {
			total = total.add(big);
		}

		while(small.compareTo(ONE) != 0) {
			big = big.twice();
			small = small.halve();
			if (small.getDigits()[0] % 2 != 0) {
				total = total.add(big);
			}
		}
		if (theSign == false) {
			total.negative();
		}
		return total;
	}

	public BigInteger divide (BigInteger value) {
		if (value == ZERO) {
			throw new IllegalArgumentException("DNE: Can't divide by 0.");
		}
		boolean theSign = true;
		if (this.sign == false && value.getSign() == true || this.sign == true && value.getSign() == false) {
			theSign = false;
		}
		BigInteger a = this.positive();
		BigInteger b = value.positive();
		BigInteger c = b;
		BigInteger answer = ONE;

		if (b.compareTo(a) == 1) {
			return ZERO;
		} else if (a.compareTo(b) == 0) {
			answer.sign = theSign;
			return answer; 
		} else if (a.halve().compareTo(b) == -1) {
			answer.sign = theSign;
			return answer;
		} else {
			while (c.multiply(TEN).compareTo(a) == -1) {
				answer = answer.multiply(TEN);
				c = c.multiply(TEN);
			}
			return theSign? answer.add((a.subtract(c)).divide(b)): answer.add((a.subtract(c)).divide(b)).negative();
		}
	}

	public BigInteger remainder (BigInteger value) {
		BigInteger a = this.divide(value);
		if (this.subtract(value.multiply(a)).compareTo(value) == 0) {
			return ZERO;
		}
		return this.subtract(value.multiply(a));
	}
}