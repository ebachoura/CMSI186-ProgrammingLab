import java.util.Arrays;
public class GeneralizedChangemaker {

//TestHarness doesn't work for my program:

//I return the answer in a Tuple that is numerically ordered by coin denomination,
//while the test harness returns the answer in a Tuple that is in the same order
//that the denominations were given

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {
            int amount = Integer.parseInt(args[0]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.");
                System.out.println();
                printUsage();
                return;
            }

            String[] denominationStrings = args[1].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.");
                    System.out.println();
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.");
                        System.out.println();
                        printUsage();
                        return;
                    }
                }
            }

            Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            printUsage();
        }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
        Tuple[][] t = new Tuple[denominations.length][amount + 1];

        for (int down = 0; down < denominations.length; down++) {
            for (int across = 0; across <= amount; across++) {
                if (denominations[down] <= across) {
                    t[down][across] = new Tuple(denominations.length);
                    t[down][across].setElement(down, 1);

                    if ((across - denominations[down]) > 0 && across != 0 && !t[down][across - denominations[down]].equals(Tuple.IMPOSSIBLE)) {
                        t[down][across] = t[down][across].add(t[down][across - denominations[down]]);
                    } else if (t[down][across - denominations[down]].equals(Tuple.IMPOSSIBLE)) {
                        t[down][across] = Tuple.IMPOSSIBLE;
                    }
                } else if (across == 0) {
                    t[down][across] = new Tuple(denominations.length);
                } else {
                    t[down][across] = Tuple.IMPOSSIBLE;
                }

                if (down - 1 >= 0 && (t[down][across].total() > t[down-1][across].total() || t[down][across].equals(Tuple.IMPOSSIBLE)) && !t[down-1][across].equals(Tuple.IMPOSSIBLE)) {
                    t[down][across] = t[down - 1][across];
                }
            }
        }
        return t[denominations.length - 1][amount];
    }

    private static void printUsage() {
        System.out.println("Usage: java GeneralizedChangemaker <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}
