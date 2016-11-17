
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
    private ChessPiece knight1, knight2, knight3, knight4;
    private ChessPiece pawn1, pawn2,  pawn3, pawn4, pawn5, pawn6, pawn7, pawn8, pawn9, pawn10, pawn11, pawn12, pawn13, pawn14, pawn15, pawn16;
    private ChessPiece rook1, rook2, rook3, rook4;
    private ChessPiece bishop1, bishop2, bishop3, bishop4;
    private ChessPiece king1, king2;
    private ChessPiece queen1, queen2;

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
        initializePieces();
    }

    private void initializePieces()
    {
        // player 1 pieces
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
        king1 = new King(player1, new ChessLocation(4,4), this);
        queen2 = new Queen(player1, new ChessLocation(0,3), this);
        // player 2
        knight3 = new Knight(player2, new ChessLocation(7,1), this);
        knight4 = new Knight(player2, new ChessLocation(7,6), this);
        pawn9 = new Pawn(player2, new ChessLocation(6,0), this);
        pawn10 = new Pawn(player2, new ChessLocation(6,1), this);
        pawn11 = new Pawn(player2, new ChessLocation(6,2), this);
        pawn12 = new Pawn(player2, new ChessLocation(6,3), this);
        pawn13 = new Pawn(player2, new ChessLocation(6,4), this);
        pawn14 = new Pawn(player2, new ChessLocation(6,5), this);
        pawn15 = new Pawn(player2, new ChessLocation(6,6), this);
        pawn16 = new Pawn(player2, new ChessLocation(6,7), this);
        rook3 = new Rook(player2, new ChessLocation(7,0), this);
        rook4 = new Rook(player2, new ChessLocation(7,7), this);
        bishop3 = new Bishop(player2, new ChessLocation(7,2), this);
        bishop4 = new Bishop(player2, new ChessLocation(7,5), this);
        king2 = new King(player2, new ChessLocation(7,4), this);
        queen2 = new Queen(player2, new ChessLocation(7,3), this);
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
