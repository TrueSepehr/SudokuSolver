import java.util.List;

public class Cell {

    private final int row;
    private final int col;
    private List<Integer> domain;

    public Cell(int row, int col, List<Integer> domain) {
        this.row = row;
        this.col = col;
        this.domain = domain;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setDomain(List<Integer> domain) {
        this.domain = domain;
    }

    public List<Integer> getDomain() {
        return domain;
    }
}
