import java.util.*;

public class Sudoku {

    private final int[][] puzzle;
    private final List<Cell> unassignedCells;

    public Sudoku(int[][] board) {
        this.puzzle = board;
        this.unassignedCells = new ArrayList<>();
        initializeCells();
    }

    public int[][] getPuzzle() {return puzzle;}

    public List<Cell> getUnassignedCells() {
        return unassignedCells;
    }

    private void initializeCells() {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (puzzle[i][j] == 0) {
                    Cell cell = new Cell(i, j, null);
                    cell.setDomain(initializeDomain(cell));
                    unassignedCells.add(cell);
                }
            }
        }
    }

    private List<Integer> initializeDomain(Cell cell) {
        List<Integer> domain = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
                add(8);
                add(9);
            }
        };

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[cell.getRow()][i] != 0 || puzzle[i][cell.getCol()] != 0)
                domain.remove((Integer) puzzle[cell.getRow()][i]);
                domain.remove((Integer) puzzle[i][cell.getCol()]);
        }
        int gridRowStart = cell.getRow() - cell.getRow() % 3;
        int gridColStart = cell.getCol() - cell.getCol() % 3;
        for (int i = gridRowStart; i < gridRowStart + 3; i++) {
            for (int j = gridColStart; j < gridColStart + 3; j++) {
                if (puzzle[i][j] != 0) {
                    domain.remove((Integer) puzzle[i][j]);
                }
            }
        }
        return domain;
    }

}