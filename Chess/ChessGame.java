
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
    private String player1;
    private String player2;
    private Pawn pawn;
    private Rook rook;
    private Bishop bishop;

    /**
     * Constructor for objects of class ChessGame. Creates a new chess board and new knight
     * that is initialised at location 0,1
     */
    public ChessGame(String player1, String player2)
    {
        this.player1 = player1;
        this.player2 = player2;

        board = new ChessBoard();
        knight = new Knight(player2, new ChessLocation(0,1), this);
        pawn = new Pawn(player2, new ChessLocation(1,0), this);
        rook = new Rook(player2, new ChessLocation(0,0), this);
        bishop = new Bishop(player2, new ChessLocation(0,2), this);
    }
    
    public ChessBoard getChessBoard()
    {
        return board;
    }
    
    public Knight getKnight()
    {
        return knight;
    }

    public String getP1()
    {
        return player1;
    }

    public String getP2()
    {
        return player2;
    }
}
