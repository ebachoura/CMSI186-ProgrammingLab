public class sin2 implements SimpleFunction {
	@Override public double eval(double x) {
		return Math.sin(x) * Math.sin(x);
	}
}