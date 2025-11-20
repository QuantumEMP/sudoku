package logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Cleanup implements IMethods{

    public static ArrayList<ArrayList<Object>> solve(ArrayList<ArrayList<Object>> puzzle){
        puzzle = cleanRow(puzzle);
        System.out.println(puzzle);
        return null;
    }

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
}
