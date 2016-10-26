
/**
 * A pawn piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Pawn extends ChessPiece
{
    public boolean firstMove;
    /**
     * Constructor for objects of class Pawn. Places the pawn on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the pawn
     * @param game              game that the pawn belongs to
     */
    public Pawn(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);
        firstMove = true;

        if (owner.equals(game.getP1()))
        {
            id = 'P';
        } else
        {
            id = 'p';
        }
    }

    /**
     * Checks if the new location is a legal move. 
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (firstMove && newLocation.getCol() == location.getCol() && (newLocation.getRow() - location.getRow() == 2 || newLocation.getRow() - location.getRow() == 1))
        {
            firstMove = false;
            return true;
        } else if (newLocation.getRow() - location.getRow() == 1 && newLocation.getCol() == location.getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }

    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (getOwner().equals(getGame().getP1()))
        {
            if (end.getRow() - start.getRow() == 2 && getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().isPieceAt(start.getRow() + 1, start.getCol() + 1))
            {
                return false;
            } else if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()))
            {
                return false;
            }
        } else
        {
            if (start.getRow() - end.getRow() == 2 && getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().isPieceAt(start.getRow() - 1, start.getCol() - 1))
            {
                return false;
            } else if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()))
            {
                return false;
            }
        }
        return true;
    }
}
