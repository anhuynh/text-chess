
/**
 * This class holds and sets the location of the pieces
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class ChessLocation
{
    private int row;
    private int col;

    /**
     * Constructor for objects of class ChessLocation
     *
     * @param row   row location
     * @param col   column location
     */
    public ChessLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     *
     * @return                  row integer
     */
    public int getRow()
    {
        return row;
    }

    /**
     *
     * @return                  col integer
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Compares another ChessLocation to see if they are equal
     *
     * @param checkLocation         row location
     * @return                      true if locations are equal, otherwise false
     */
    public boolean equals(ChessLocation checkLocation)
    {
        if (this == checkLocation)
        {
            return true;
        } else if (checkLocation == null) {
            return false;
        } 

        return (row == checkLocation.row && col == checkLocation.col);
    }
}
