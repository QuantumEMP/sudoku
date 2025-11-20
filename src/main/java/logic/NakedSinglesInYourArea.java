package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class NakedSinglesInYourArea implements IMethods{


    public static ArrayList<ArrayList<Object>> solve(ArrayList<ArrayList<Object>> puzzle) {
        puzzle = Cleanup.solve(puzzle);                                                             //removes unnecessary "notes"
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        for (ArrayList<Object> line : puzzle){
            ArrayList<Object> l = new ArrayList<>();
            for (Object item : line){
                HashSet<Object> possibilities;
                possibilities = new HashSet<>(Arrays.asList(item.toString().split(",")));     //converts the "notes" from a string to a set

                if (possibilities.size() == 1){                                                     //if there is only one possibility then it has to be that number
                    l.add(possibilities.toArray()[0]);
                } else {
                    l.add(item);
                }
            }
            result.add(l);
        }

        if (!puzzle.equals(result)){
            result = NakedSinglesInYourArea.solve(result);
        }

        return result;
    }
}
