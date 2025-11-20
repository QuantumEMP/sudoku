import logic.NakedSinglesInYourArea;

static String inputFile;
static String outputFile;

void main(String[] args) {
    IO.println("start");

    List<String> sudoku = readFile(args);

    if (Objects.equals(sudoku.getFirst(), "ERROR")) {
        System.exit(0);
    }

    ArrayList<ArrayList<Object>> puzzle = convertLines(sudoku);
    ArrayList<ArrayList<Object>> result = NakedSinglesInYourArea.solve(puzzle);
    System.out.println(result);

}

/**
 * Reads the given sudoku puzzle from the text file
 *
 * @param args the name of the input and output files
 * @return the puzzle as a list of strings
 */
private static List<String> readFile(String[] args) {
    inputFile = args[0];                                                //takes the name from the arguments
    for (int i = 0; i < args.length; i++) {
        if (Objects.equals(args[i], "-o")) {                         //finds the output file name
            outputFile = args[i + 1];
        }
    }

    try {
        return Files.readAllLines(Paths.get(inputFile));                //reads the file from the input
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
    return List.of("ERROR");
}

/**
 * Converts the List<String> to an ArrayList object for ease of use
 *
 * @param sudoku the puzzle directly from the file
 * @return the puzzle in the desired format
 */
private static ArrayList<ArrayList<Object>> convertLines(List<String> sudoku) {
    ArrayList<ArrayList<Object>> result = new ArrayList<>();
    for (String line : sudoku) {
        String[] list = line.strip().split(" ");

        ArrayList<Object> lineResult = new ArrayList<>();

        for (String item : list) {
            if (Objects.equals(item, "0")) {
                item = "1,2,3,4,5,6,7,8,9";                              //replaces the 0s with "notes" of every possible number
                lineResult.add(item);
            } else {
                lineResult.add(item);
            }
        }

        result.add(lineResult);

    }

    return result;
}
