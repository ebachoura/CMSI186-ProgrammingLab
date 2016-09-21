import java.util.Arrays;
public class Deck {
	private Card[]cards;
	public Deck() {
		this.cards = new Card[52];
		int index = 0;
		for (int i = 1; i < 5; i++){
			for (int j = 1; j < 14; j++) {
				this.cards[index] = new Card(j, i);
				index++;
			}
		}
	}
	public String toString() {
		return Arrays.toString(this.cards);
	} 
}