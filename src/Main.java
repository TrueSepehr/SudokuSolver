import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        long startTime = 0;
        long endTime = 0;

//        Scanner scanner = new Scanner(System.in);
//        int[][] puzzle = new int[9][9];

//        for (int i = 0; i < puzzle.length; i++){
//            for (int j = 0; j < puzzle.length; j++){
//                puzzle[i][j] = scanner.nextInt();
//            }
//        }

        int[][] testCase1 = new int[][] {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };

        int[][] testCase2 = new int[][] {
                { 0, 9, 0, 7, 2, 0, 0, 0, 0 },
                { 0, 8, 0, 5, 4, 0, 9, 0, 0 },
                { 2, 0, 1, 0, 0, 0, 0, 0, 0 },
                { 6, 3, 0, 2, 0, 4, 0, 0, 5 },
                { 0, 0, 4, 0, 0, 0, 3, 0, 0 },
                { 5, 0, 0, 9, 0, 3, 0, 4, 6 },
                { 0, 0, 0, 0, 0, 0, 5, 0, 8 },
                { 0, 0, 3, 0, 5, 7, 0, 9, 0 },
                { 0, 0, 0, 0, 8, 9, 0, 6, 0 }
        };

        int[][] testCase3 = new int[][] {
                { 1, 3, 0, 0, 0, 4, 0, 0, 0 },
                { 0, 4, 5, 0, 0, 7, 0, 0, 0 },
                { 2, 0, 6, 0, 0, 5, 0, 0, 0 },
                { 0, 9, 0, 0, 5, 0, 1, 0, 0 },
                { 3, 2, 0, 7, 0, 1, 0, 5, 6 },
                { 0, 0, 1, 0, 2, 0, 0, 7, 0 },
                { 0, 0, 0, 8, 0, 0, 5, 0, 2 },
                { 0, 0, 0, 5, 0, 0, 4, 8, 0 },
                { 0, 0, 0, 4, 0, 0, 0, 6, 9 }
        };

        Sudoku sudoku = new Sudoku(testCase1);
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        if (sudokuSolver.isValidConfig()) {
            System.out.println("Sudoku is Valid. Solving...");
            startTime = System.nanoTime();
            sudokuSolver.solve();
        } else {
            System.out.println("Sudoku is Invalid! Try again...");
        }
        if (sudokuSolver.solve()) {
            endTime = System.nanoTime();
            System.out.println(endTime - startTime);
            sudokuSolver.print();
        }
        else {
            System.out.println("No solution");
        }

    }
}
