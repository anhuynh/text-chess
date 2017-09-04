
/**
 * This class is used to store a knight piece at different locations of
 * a chess board.
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class ChessBoard
{
    private ChessPiece[][] board; // board is an array of chess pieces

    /**
     * Constructor for objects of class ChessBoard.
     */
    public ChessBoard()
    {
        board = new ChessPiece[8][8];
        
        // initialise the array to all nulls
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[row].length; col++)
            {
                board[row][col] = null;
            }
        }
    }
    
    /**
     * String representation of the board.
     *
     * @return                  string with board elements
     */
    public String toString()
    {
        int num = 0;
        String stringBoard = "   0  1  2  3  4  5  6  7\n";
        for (ChessPiece[] rows : board)
        {
            stringBoard += num + "  ";
            num++;
            for (ChessPiece cell : rows)
            {
                if (cell == null)
                {
                    stringBoard += "-  ";
                } else
                {
                    stringBoard += cell.toString() + "  ";
                }
            }
            stringBoard += "\n";
        }
        return stringBoard;
    }
    
    /**
     * Gets the piece at the specified location.
     *
     * @param location          location to get the piece from
     * @return                  ChessPiece at the specified location
     */
    public ChessPiece getPieceAt(ChessLocation location)
    {
        return board[location.getRow()][location.getCol()];
    }

    /**
     * Checks if a piece is at the specified location.
     * 
     * @param row   row of the location
     * @param col   column of the location
     * @return      true if the piece is at specified location, otherwise false
     */
    public boolean isPieceAt(int row, int col)
    {
        // check if the location is null
        if (board[row][col] == null)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Places a piece at the specified location.
     *
     * @param piece     chess piece to place
     * @param location  new location to place the piece
     */
    public void placePieceAt(ChessPiece piece, ChessLocation location)
    {
        if (board[location.getRow()][location.getCol()] != null) // checks if there is a piece here to capture
        {
            board[location.getRow()][location.getCol()].setLocation(null); // set the piece at the new location to null
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setLocation(location);
    }
    
    /**
     * Removes a piece at the specified location.
     *
     * @param location  location to remove a piece from
     */
    public void removePiece(ChessLocation location)
    {
        board[location.getRow()][location.getCol()] = null;
    }
}
