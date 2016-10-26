
/**
 * This class holds and sets the location of the pieces
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
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

    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public void setRow()
    {
        this.row = row;
    }
    
    public void setCol()
    {
        this.col = col;
    }

    public boolean equals(ChessLocation checkLocation)
    {
        if (row == checkLocation.row && col == checkLocation.col)
        {
            return true;
        }

        return false;
    }
}
