package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Cleanup implements IMethods{
    static boolean col = false;
    static boolean block = false;

    public static ArrayList<ArrayList<Object>> solve(ArrayList<ArrayList<Object>> puzzle){
        puzzle = cleanRow(puzzle);
        puzzle = cleanColumn(puzzle);
        puzzle = cleanBlock(puzzle);
        return puzzle;
    }

    /**
     * Removes unnecessary "notes" from each row in the puzzle
     *
     * @param puzzle the sudoku puzzle
     * @return the cleaned sudoku puzzle
     */
    private static ArrayList<ArrayList<Object>> cleanRow(ArrayList<ArrayList<Object>> puzzle) {
        ArrayList<ArrayList<Object>> r = new ArrayList<>();

        for (ArrayList<Object> line : puzzle) {
            ArrayList<Object> l = new ArrayList<>();
            for (Object item : line) {
                HashSet<Object> possibilities = new HashSet<>(Arrays.asList(item.toString().split(",")));
                if (possibilities.size() == 1){
                    l.add(item);
                    continue;
                }

                StringBuilder result = new StringBuilder();
                for (Object num : possibilities) {
                    if (!line.contains(num)) {
                        result.append(num).append(",");
                    }
                }

                l.add(result.toString());
            }
            r.add(l);
        }

        return r;
    }

    /**
     * Converts the set-up of the puzzle array from row based to column based.
     * Then it cleans out the "notes"
     * Then it returns the puzzle array back to being row based.
     *
     * @param puzzle the sudoku puzzle
     * @return the cleaned sudoku puzzle
     */
    private static ArrayList<ArrayList<Object>> cleanColumn(ArrayList<ArrayList<Object>> puzzle) {
        boolean done = col;
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        for (int i = 1; i<=9; i++){
            result.add(new ArrayList<>());
        }

        for (ArrayList<Object> objects : puzzle) {
            for (int j = 0; j < 9; j++) {
                result.get(j).add(objects.get(j));                      //fills the array with the column data from the given puzzle
            }
        }

        result = cleanRow(result);                                      //uses the cleanRow func to clean the columns

        if(done){
            col = false;
            return result;                                              //if it already cleaned the column then break out of the recursion
        }
        col = true;
        return cleanColumn(result);                                     //returns the array back to being row based using recursion
    }

    /**
     * Converts the set-up of the puzzle array from row based to block based.
     * Then it cleans out the "notes"
     * Then it returns the puzzle array back to being row based.
     *
     * @param puzzle the sudoku puzzle
     * @return the cleaned sudoku puzzle
     */
    private static ArrayList<ArrayList<Object>> cleanBlock(ArrayList<ArrayList<Object>> puzzle) {
        boolean done = block;
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        for (int i = 1; i<=9; i++){
            result.add(new ArrayList<>());
        }

        for (int i=0; i< 3; i++){
            for (int j = 0; j<3; j++){                                                  //takes the first 3 items from the first 3 rows and adds it to the first array
                result.getFirst().add(puzzle.get(i).get(j));
            }
            for (int j = 3; j<6; j++){                                                  //takes the next 3 items from the first 3 rows and adds it to the second array
                result.get(1).add(puzzle.get(i).get(j));
            }
            for (int j = 6; j<9; j++){                                                  //and so on
                result.get(2).add(puzzle.get(i).get(j));
            }
        }
        for (int i=3; i< 6; i++){
            for (int j = 0; j<3; j++){
                result.get(3).add(puzzle.get(i).get(j));
            }
            for (int j = 3; j<6; j++){
                result.get(4).add(puzzle.get(i).get(j));
            }
            for (int j = 6; j<9; j++){
                result.get(5).add(puzzle.get(i).get(j));
            }
        }
        for (int i=6; i< 9; i++){
            for (int j = 0; j<3; j++){
                result.get(6).add(puzzle.get(i).get(j));
            }
            for (int j = 3; j<6; j++){
                result.get(7).add(puzzle.get(i).get(j));
            }
            for (int j = 6; j<9; j++){
                result.get(8).add(puzzle.get(i).get(j));
            }
        }

        result = cleanRow(result);

        if(done){
            block = false;
            return result;
        }
        block = true;
        return cleanBlock(result);
    }
}
