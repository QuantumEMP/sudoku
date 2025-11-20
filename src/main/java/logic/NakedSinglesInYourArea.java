package logic;

import java.util.ArrayList;
import java.util.HashSet;

public class NakedSinglesInYourArea implements IMethods{


    public static ArrayList<ArrayList<Object>> solve(ArrayList<ArrayList<Object>> puzzle) {
        boolean didSomething = false;
        for (ArrayList<Object> line : puzzle){
            for (Object item : line){
                HashSet<Object> possibilities;
                try{
                    possibilities = (HashSet<Object>) item;
                } catch (Exception e) {
                    continue;
                }

                if (possibilities.size() == 1){
                    item = possibilities.toArray()[0];
                    didSomething = true;
                }
            }
        }

        ArrayList<ArrayList<Object>> result;

        if (didSomething){
            result = NakedSinglesInYourArea.solve(puzzle);
        } else{
            result = puzzle;
        }

        return result;
    }
}
