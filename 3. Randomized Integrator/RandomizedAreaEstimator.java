import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RandomizedAreaEstimator {	
	public static void main(String[]args) {
		int count = 0;
		for (int i = 0; i < args.length; i++) {
			try {
				double a = (Double.parseDouble(args[i]));
			} catch (Exception e) {
				count++;
			}
		}
		Shape[] array = new Shape[count];
		int n = 0;

		for (int i = 0; i < count; i++) {
			if (args[n].equals("circle")) {
				array[i] = new circle(Double.parseDouble(args[n+1]), Double.parseDouble(args[n+2]), Double.parseDouble(args[n+3]));
				n += 4;
			} else if (args[n].equals("triangle")) {
				array[i] = new triangle(Double.parseDouble(args[n+1]), Double.parseDouble(args[n+2]), Double.parseDouble(args[n+3]), Double.parseDouble(args[n+4]), Double.parseDouble(args[n+5]), Double.parseDouble(args[n+6]));
				n += 7;
			}
		}
        System.out.println(area(array));
	}

    public static Rectangle boundingBox(Shape[] array) {
        double largestX = 0;
        double largestY = 0;
        double smallestX= 0;
        double smallestY= 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof circle) {
                circle c = (circle) array[i];
                Rectangle x = c.box();
                largestX = x.x1() > largestX? x.x1() : largestX;
                largestX = x.x2() > largestX? x.x2() : largestX;

                smallestX = x.x1() < smallestX? x.x1() : smallestX;
                smallestX = x.x2() < smallestX? x.x2() : smallestX;

                largestY = x.y1() > largestY? x.y1() : largestY;
                largestY = x.y2() > largestY? x.y2() : largestY;

                smallestY = x.y1() < smallestY? x.y1() : smallestY;
                smallestY = x.y2() < smallestY? x.y2() : smallestY;
            } else if (array[i] instanceof triangle) {
                triangle t = (triangle) array[i];
                Rectangle x = t.box();
                largestX = x.x1() > largestX? x.x1() : largestX;

                largestX = x.x2() > largestX? x.x2() : largestX;

                smallestX = x.x1() < smallestX? x.x1() : smallestX;
                smallestX = x.x2() < smallestX? x.x2() : smallestX;

                largestY = x.y1() > largestY? x.y1() : largestY;
                largestY = x.y2() > largestY? x.y2() : largestY;

                smallestY = x.y1() < smallestY? x.y1() : smallestY;
                smallestY = x.y2() < smallestY? x.y2() : smallestY;
            }
        }
        return new Rectangle(smallestX, largestX, smallestY, largestY);
    }

    public static double[] areaIndividual (Shape[] array) {
        double[] areas = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof circle) {
                circle c = (circle) array[i];
                areas[i] = c.getArea();
            } else if (array[i] instanceof triangle) {
                triangle t = (triangle) array[i];
                areas[i] = t.getArea();
            }
        }
        return areas;
    }

    public static boolean insideAll (Shape[] array, Point p) {
        boolean answer = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof circle) {
                circle c = (circle) array[i];
                answer = answer && c.inCircle(p);
            } else if (array[i] instanceof triangle) {
                triangle t = (triangle) array[i];
                answer = answer && t.inTriangle(p);
            }
        }
        return answer; 
    }

    public static boolean insideAny (Shape[] array, Point p) {
        boolean answer = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof circle) {
                circle c = (circle) array[i];
                answer = answer || c.inCircle(p);
            } else if (array[i] instanceof triangle) {
                triangle t = (triangle) array[i];
                answer = answer || t.inTriangle(p);
            }
        }
        return answer; 
    }

    public static boolean insideRest (Shape[] array, Point p) {
        boolean answer = false;
        int trueCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof circle) {
                circle c = (circle) array[i];
                if (c.inCircle(p)) {
                    trueCount++;
                }
            } else if (array[i] instanceof triangle) {
                triangle t = (triangle) array[i];
                if (t.inTriangle(p)) {
                    trueCount++;
                }
            }
        }
        if (trueCount < 2 && trueCount > 0) {
            answer = true;
        }
        return answer; 
    }


    public static String area(Shape[] array) {
        Rectangle box = boundingBox(array);
        double insideAny = 0.0;
        double insideAll = 0.0;
        double insideRest = 0.0;
        double totalPoints = 1000000.0;

        for (int i = 0; i <= totalPoints; i++) {
            Point p = new Point(box);
            if (insideAny(array, p)) {
                insideAny++;
            }
            if (insideAll(array, p)) {
                insideAll++;
            }
            if (insideRest(array, p)) {
            insideRest++;
            }
        }
        String a1 = Arrays.toString(areaIndividual(array));
        double a2 = insideAny/totalPoints * box.getArea();
        double a3 = insideAll/totalPoints * box.getArea();
        double a4 = insideRest/totalPoints *box.getArea();
        return "Areas of each individual shape: " + a1 + "\nArea of the union of all shapes: " + a2 + "\nArea of the intersection of all shapes: " + a3 + "\nAreas of the individual shapes that do not intersect any other shape: " + a4;
    }
}