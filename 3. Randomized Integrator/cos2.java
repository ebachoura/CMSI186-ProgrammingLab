public class cos2 implements SimpleFunction {
	@Override public double eval(double x) {
		return Math.cos(x) * Math.cos(x);
	}
}