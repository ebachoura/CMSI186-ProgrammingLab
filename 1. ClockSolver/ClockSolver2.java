public class ClockSolver2 {
	public static void main (String[]args) {
		double slice;
		double angle;
		try {
			slice = Double.parseDouble(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			slice = 60;
		} catch (Exception e) {
			System.out.println("Invaid argument for slice, program expects a double. Slice changed to 60.");
			slice = 60;
		}
		if (slice >= 1800 || slice <= 0) {
			System.out.println("Your time slice input should be between 0 and 1800. Slice changed to 60.");
			slice = 60;
		}
		try {
			angle = Double.parseDouble(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			angle = 180;;
		} catch (Exception e) {
			System.out.println("Invaid argument for angle, program expects a double. Slice changed to 180.");
			angle = 180;
		}
		if (slice >= 1800 || slice <= 0) {
			System.out.println("Your angle input should be between 0 and 360. Angle changed to 180.");
			angle = 180;
		}
		Clock C = new Clock();

		double angleSlice = Math.abs(slice / 120 - slice / 10);

		while (C.getHours() < 12) {
			C.tick(slice);
			if ((C.angleBetween() <= angle + (angleSlice / 2)) && (C.angleBetween() >= angle - (angleSlice / 2))) {
				System.out.println(C.toString());
			}	
		}
	}
}