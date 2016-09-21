public class myFarm {
	public static void main (String[]args) {
		Cow c1 = new Cow();
		c1.moo();
		Cow c2 = new Cow();
		c2.moo();
		Cow[]cows = new Cow[5];
		Cow.herd(cows);
	}
}