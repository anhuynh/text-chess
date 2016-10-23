
/**
 * This class holds the board and pieces.
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class ChessGame
{
    private ChessBoard board;
    private Knight knight;

    /**
     * Constructor for objects of class ChessGame. Creates a new chess board and new knight
     * that is initialised at location 0,1
     */
    public ChessGame()
    {
        board = new ChessBoard();
        knight = new Knight("Black", new ChessLocation(0,1), this);
    }
    
    public ChessBoard getChessBoard()
    {
        return board;
    }
    
    public Knight getKnight()
    {
        return knight;
    }
}
