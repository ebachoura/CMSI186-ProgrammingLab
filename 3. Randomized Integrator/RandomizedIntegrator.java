import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RandomizedIntegrator {	
	public static void main(String[]args) {
		if (args.length < 3) {
			throw new ArrayIndexOutOfBoundsException("Not enought imputs: set to default of sin(x) from 0 to pi.");
		}
		SimpleFunction function = new sin();
		double bound1 = 0.0;
		double bound2 = Math.PI;
		try {
            function = getFunction(Class.forName(args[0]), args);
			bound1 = checkPi(args[args.length - 2]);
			bound2 = checkPi(args[args.length - 1]);
        } catch (ClassNotFoundException e) {
            System.err.println(args[0] + " is not defined");
        } catch (NoSuchMethodException e) {
            System.err.println("Can't find a sensible constructor to make your function");
        } catch (ClassCastException e) {
            System.err.println(args[0] + " does not implement SimpleFunction");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println(args[0] + " is not public or is malformed");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        	System.err.println("Syntax: java BetterFunctionTablePrinterApp name a b delta args");
        }

		System.out.println(area(function, bound1, bound2));
	}

	private static SimpleFunction getFunction(Class<?> c, String[] args) throws
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            Constructor constructor = c.getConstructor(double[].class);
            double[] additionalArguments = new double[args.length-3];
            for (int i = 1; i < args.length - 2; i++) {
                additionalArguments[i-1] = Double.parseDouble(args[i]);
            }
            return (SimpleFunction)constructor.newInstance(additionalArguments);
        } catch (NoSuchMethodException e) {
            Constructor constructor = c.getConstructor();
            return (SimpleFunction)constructor.newInstance();
        }
    }

	public static double checkPi(String s) {
		if (s.equals("pi")) {
			return Math.PI;
		} else if (s.equals("-pi")) {
			return Math.PI * -1.0;
		} else {
			return Double.parseDouble(s);
		}
	}

	public static Rectangle boxHeight(SimpleFunction function, double bound1, double bound2) {
		SimpleFunctionEvaluator eval = new SimpleFunctionEvaluator();
		double start = bound1;
		double high = 0;

		while (start < bound2) {
			high = Math.abs(eval.calculate(function, start)) > high? Math.abs(eval.calculate(function, start)) : high;
			start = start + 0.01;
		}
		return new Rectangle(bound1, bound2, 0.0, Math.ceil(high));
	}

	public static double area (SimpleFunction function, double bound1, double bound2) {
		SimpleFunctionEvaluator eval = new SimpleFunctionEvaluator();
		double insidePoints = 0.0;
		double totalPoints = 1000000.0;
		Rectangle bound = boxHeight(function, bound1, bound2);

		for (int i = 0; i <= totalPoints; i++) {
			Point p = new Point(bound);
			if ((eval.calculate(function, p.getX())) > 0 && p.getY() < (eval.calculate(function, p.getX()))) {
				insidePoints++;
			} else if ((eval.calculate(function, p.getX())) < 0 && p.getY() < Math.abs((eval.calculate(function, p.getX())))) {
				insidePoints--;
			}
		}
		return insidePoints/totalPoints * bound.getArea();
	}

}