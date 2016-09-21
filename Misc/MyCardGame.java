import java.util.Arrays;
public class MyCardGame{
	public static void main (String[]args) {
		Card c = new Card(5, 1);
		System.out.println(c.getRank());
		System.out.println(c.getSuit());
		System.out.println(c.toString());
		Card[]test = new Card[3];
		test[0] = new Card(10,2);
		test[1] = new Card(1,4);
		test[2] = new Card(9,3);
		System.out.println(Arrays.toString(test));
		Deck d = new Deck();
		System.out.println(d);
	}
}