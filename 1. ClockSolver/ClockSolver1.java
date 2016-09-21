public class ClockSolver1 {
	public static void main (String[]args) {
		double slice;
		try {
			slice = Double.parseDouble(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			slice = 60;
		} catch (Exception e) {
			System.out.println("Invaid argument, program expects a double. Slice changed to 60.");
			slice = 60;
		}
		if (slice >= 1800 || slice <= 0) {
			System.out.println("Your time slice input should be between 0 and 1800. Slice changed to 60.");
			slice = 60;
		}
		Clock C = new Clock();

		double angleSlice = Math.abs(slice / 120 - slice / 10);

		while (C.getHours() < 12) {
			C.tick(slice);
			if ((C.angleBetween() <= 180 + (angleSlice / 2)) && (C.angleBetween() >= 180 - (angleSlice / 2))) {
				System.out.println(C.toString());
			}	
		}
	}
}