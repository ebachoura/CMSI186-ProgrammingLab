import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class SudokuSolver {
	public static void main (String[]args) {
		int[][] box = new int[3][3];
		box = createPuzzle(box, 0);
		System.out.println(Arrays.deepToString(box));
	}

	public static int[][] createPuzzle(int[][] box, int index){
		new BufferedReader(new InputStreamReader(System.in))
		.lines()
		.forEach(line -> {
			String[] set = line.trim().split(",");
			for(int i = 0; i<3; i++){
				box[index][i] = set[i].equals("x") ? -1 : Integer.parseInt(set[i]);
			}
			index++;
		});
		return box;
	}
/*
	public static Box[][] readPuzzleFromStandardInput(Box[][] box) {
		int across = 0;
		int down = 0;
		int index = 0;

		new BufferedReader(new InputStreamReader(System.in))
		.lines()
		.forEach(line -> {

			String[] set = line.trim().split(",");
			for (int i = 0; i < 9; i++) {
				Integer z = set[i].equals("x") ? null : new Integer(Integer.parseInt(set[i]));
				if (i == 2 || i == 5 || i == 8) {
					across++;
				}
				box[across][down].changeValue(index%3, i%3, z);
			}

			index++;
			if (index == 2 || index == 5 || index == 8) {
				down++;
			}
		});
		return box;
	}
*/
}