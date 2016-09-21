public class GeneralizedChangemakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_USA();
        // Add more!

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        int[] usaDenominations = new int[] { 25, 10, 5, 1 };
        int[] coins1 = new int[] { 1210, 2433, 4924, 2 };
        int[] coins2 = new int[] { 37, 63, 3, 7 };
        int[] coins3 = new int[] { 19, 23 };

        Tuple result1 = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 99);
        Tuple result2 = GeneralizedChangemaker.makeChangeWithDynamicProgramming(coins1, 1257782);
        Tuple result3 = GeneralizedChangemaker.makeChangeWithDynamicProgramming(coins2, 221);
        Tuple result4 = GeneralizedChangemaker.makeChangeWithDynamicProgramming(coins3, 100);
        try {
            displaySuccessIfTrue(result1.equals(new Tuple(new int[]{3,2,0,4})));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(result2.equals(new Tuple(new int[]{6,6,251,0})));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(result3.equals(new Tuple(new int[]{2,2,0,3})));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(result4.equals(Tuple.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}
