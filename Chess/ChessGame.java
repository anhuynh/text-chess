
/**
 * This class holds the board and pieces.
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class ChessGame
{
    private ChessBoard board;
    private String player1;
    private String player2;
    private ChessPiece knight1;
    private ChessPiece knight2;
    private ChessPiece pawn1;
    private ChessPiece pawn2;
    private ChessPiece pawn3;
    private ChessPiece pawn4;
    private ChessPiece pawn5;
    private ChessPiece pawn6;
    private ChessPiece pawn7;
    private ChessPiece pawn8;
    private ChessPiece rook1;
    private ChessPiece rook2;
    private ChessPiece bishop1;
    private ChessPiece bishop2;
    private ChessPiece king;
    private ChessPiece queen;

    /**
     * Constructor for objects of class ChessGame. Creates a new chess board and initializes
     * all pieces to their proper locations
     *
     * @param player1           string of player 1 name
     * @param player2           string of player 2 name
     */
    public ChessGame(String player1, String player2)
    {
        this.player1 = player1;
        this.player2 = player2;

        board = new ChessBoard();
        // initialize all pieces to the proper locations
        knight1 = new Knight(player1, new ChessLocation(0,1), this);
        knight2 = new Knight(player1, new ChessLocation(0,6), this);
        pawn1 = new Pawn(player1, new ChessLocation(1,0), this);
        pawn2 = new Pawn(player1, new ChessLocation(1,1), this);
        pawn3 = new Pawn(player1, new ChessLocation(1,2), this);
        pawn4 = new Pawn(player1, new ChessLocation(1,3), this);
        pawn5 = new Pawn(player1, new ChessLocation(1,4), this);
        pawn6 = new Pawn(player1, new ChessLocation(1,5), this);
        pawn7 = new Pawn(player1, new ChessLocation(1,6), this);
        pawn8 = new Pawn(player1, new ChessLocation(1,7), this);
        rook1 = new Rook(player1, new ChessLocation(0,0), this);
        rook2 = new Rook(player1, new ChessLocation(0,7), this);
        bishop1 = new Bishop(player1, new ChessLocation(0,2), this);
        bishop2 = new Bishop(player1, new ChessLocation(0,5), this);
        king = new King(player1, new ChessLocation(0,4), this);
        queen = new Queen(player1, new ChessLocation(0,3), this);
    }
    
    /**
     *
     * @return                  chessboard array
     */
    public ChessBoard getChessBoard()
    {
        return board;
    }

    /**
     *
     * @return                  player1 string
     */
    public String getP1()
    {
        return player1;
    }

    /**
     *
     * @return                  player2 string
     */
    public String getP2()
    {
        return player2;
    }
}
