import java.util.*;

public class SudokuSolver {

    private final Sudoku sudoku;
    private final Stack<Cell> assignedCell;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        assignedCell = new Stack<>();
    }

    public boolean solve() {

        if (sudoku.getUnassignedCells().size() == 0) {
            return true;
        }

        Cell cell = selectUnassignedCell();
        int row = cell.getRow();
        int col = cell.getCol();
        List<Integer> domain = cell.getDomain();

        for (int i = 0; i < domain.size(); i++) {
            List<Integer> LCVs = getLCV(domain, row, col);
            int value = domain.get(LCVs.indexOf(Collections.min(LCVs)));
            domain.remove((Integer)value);
            if (isSafe(row, col, value)) {
                sudoku.getPuzzle()[row][col] = value;
                modifyDomains(cell, value, true);
                assignedCell.push(cell);
                sudoku.getUnassignedCells().remove(cell);
                if (solve())
                    return true;
                else {
                    sudoku.getPuzzle()[row][col] = 0;
                    sudoku.getUnassignedCells().add(assignedCell.pop());
                    modifyDomains(cell, value, false);
                }
            }
        }
        return false;
    }

    private Cell selectUnassignedCell() {
        List<Integer> domainsSize = new ArrayList<>();
        for (Cell cell : sudoku.getUnassignedCells())
            domainsSize.add(cell.getDomain().size());

        int minimum = Collections.min(domainsSize);
        List<Cell> possibleCells = new ArrayList<>();
        for (Cell cell : sudoku.getUnassignedCells())
            if (cell.getDomain().size() == minimum)
                possibleCells.add(cell);
        //if there are no ties, take the square with the MRV
        if (possibleCells.size() == 1)
            return possibleCells.get(0);
        //otherwise, find the most constraining variable (variable with the highest degree)
        else {
            List<Integer> degrees = new ArrayList<>();
            for (Cell cell : possibleCells) {
                int degree = getDegree(cell);
                degrees.add(degree);
            }
            int maximumDegree = Collections.max(degrees);
            List<Cell> maximumDegrees = new ArrayList<>();
            for (int i = 0; i < degrees.size(); i++) {
                int degree = degrees.get(i);
                if (degree == maximumDegree)
                    maximumDegrees.add(possibleCells.get(i));
            }
            return maximumDegrees.get(0);
        }
    }

    private int getDegree (Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        int degree = 0;
        for (int i = 0; i < sudoku.getPuzzle().length; i++) {
            if (i == row)
                continue;
            if (sudoku.getPuzzle()[i][col] == 0)
                degree++;
        }
        for (int i = 0; i < sudoku.getPuzzle()[0].length; i++) {
            if (i == col)
                continue;
            if (sudoku.getPuzzle()[row][i] == 0)
                degree++;
        }
        int gridRowStart = row - row % 3;
        int gridColStart = col - col % 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (i == row && j == col)
                    continue;
                if (sudoku.getPuzzle()[i][j] == 0)
                    degree++;
            }
        }
        return degree;
    }

    private List<Integer> getLCV(List<Integer> domain, int row, int col) {
        List<Integer> LCV = new ArrayList<>();
        int grid = getGrid(row, col);
        for (Integer d : domain) {
            int count = 0;
            for (Cell cell : sudoku.getUnassignedCells()) {
                if (row == cell.getRow() && col != cell.getCol()) {
                    if (cell.getDomain().contains(d))
                        count++;
                }
                if (row != cell.getRow() && col == cell.getCol()) {
                    if (cell.getDomain().contains(d))
                        count++;
                }
                if (cell.getRow() == row && cell.getCol() == col)
                    continue;
                if (getGrid(cell.getRow(), cell.getCol()) == grid) {
                    if (cell.getDomain().contains(d)) {
                        count++;
                    }
                }
            }
            LCV.add(count);
        }
        return LCV;
    }

    private int getGrid(int row, int col) {
        return row / 3 * 3 + col / 3;
    }

    //forward checking
    private void modifyDomains(Cell cell, int value, boolean check) {
        for (Cell unassignedCell : sudoku.getUnassignedCells()) {
            if (unassignedCell.getRow() == cell.getRow()) {
                if (check)
                    unassignedCell.getDomain().remove((Integer) value);
                else
                    unassignedCell.getDomain().add(value);
            }
            if (unassignedCell.getCol() == cell.getCol()) {
                if (check)
                    unassignedCell.getDomain().remove((Integer) value);
                else
                    unassignedCell.getDomain().add(value);

            }
            if (getGrid(unassignedCell.getRow(), unassignedCell.getCol()) == getGrid(cell.getRow(), cell.getCol())) {
                if (check)
                    unassignedCell.getDomain().remove((Integer) value);
                else if (!unassignedCell.getDomain().contains(value))
                    unassignedCell.getDomain().add(value);
            }
        }
    }

    private boolean isSafe(int row, int col, int value) {
        for (int i = 0; i < sudoku.getPuzzle().length; i++) {
            if (sudoku.getPuzzle()[row][i] == value) {
                return false;
            }
        }
        for (int j = 0; j < sudoku.getPuzzle()[0].length; j++) {
            if (sudoku.getPuzzle()[j][col] == value) {
                return false;
            }
        }
        int gridRowStart = row - row % 3;
        int gridColStart = col - col % 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (sudoku.getPuzzle()[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidConfig() {
        for (int i = 0; i < sudoku.getPuzzle().length; i++) {
            for (int j = 0; j < sudoku.getPuzzle().length; j++) {
                if (!isValid(sudoku.getPuzzle(), i, j))
                    return false;
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col) {
        return notInRow(board, row) && notInCol(board, col) && notInGrid(board, row - row % 3, col - col % 3);
    }

    private boolean notInRow(int[][] board, int row) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            if (hashSet.contains(board[row][i]))
                return false;
            if (board[row][i] != 0)
                hashSet.add(board[row][i]);
        }
        return true;
    }

    public static boolean notInCol(int[][] board, int col) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            if (hashSet.contains(board[i][col]))
                return false;
            if (board[i][col] != 0)
                hashSet.add(board[i][col]);
        }
        return true;
    }

    public static boolean notInGrid(int[][] board, int startRow, int startCol) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int curr = board[row + startRow][col + startCol];
                if (hashSet.contains(curr))
                    return false;
                if (curr != 0)
                    hashSet.add(curr);
            }
        }
        return true;
    }

    public void print() {
        for (int row = 0; row < sudoku.getPuzzle().length; row++) {
            for (int col = 0; col < sudoku.getPuzzle()[0].length; col++) {
                System.out.print(sudoku.getPuzzle()[row][col]);
                System.out.print(" ");
            }
            System.out.println();

            if ((row + 1) % 3 == 0) {
                System.out.print("");
            }
        }
        System.out.println();
    }
}
