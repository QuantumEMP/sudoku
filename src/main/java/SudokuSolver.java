import logic.Cleanup;

static String inputFile;
static String outputFile;

void main(String[] args) {
    IO.println("start");

    List<String> sudoku = readFile(args);

    if (Objects.equals(sudoku.getFirst(), "ERROR")) {
        System.exit(0);
    }

    ArrayList<ArrayList<Object>> puzzle = convertLines(sudoku);
    Cleanup.solve(puzzle);


}

private static List<String> readFile(String[] args) {
    inputFile = args[0];
    for (int i = 0; i < args.length; i++) {
        if (Objects.equals(args[i], "-o")) {
            outputFile = args[i + 1];
        }
    }

    try {
        return Files.readAllLines(Paths.get(inputFile));
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
    return List.of("ERROR");
}

private static ArrayList<ArrayList<Object>> convertLines(List<String> sudoku) {
    ArrayList<ArrayList<Object>> result = new ArrayList<>();
    for (String line : sudoku) {
        String[] list = line.strip().split(" ");

        ArrayList<Object> lineResult = new ArrayList<>();

        for (String item : list) {
            if (Objects.equals(item, "0")) {
                item = "1,2,3,4,5,6,7,8,9";
                lineResult.add(item);
            } else {
                lineResult.add(item);
            }
        }

        result.add(lineResult);

    }

    return result;
}
