
/**
 * A rook piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Rook extends ChessPiece
{
    /**
     * Constructor for objects of class Rook. Places the rook on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the rook
     * @param game              game that the rook belongs to
     */
    public Rook(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'R';
        } else
        {
            id = 'r';
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
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            return false;
        } else if (newLocation.getRow() == location.getRow() || newLocation.getCol() == location.getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }

    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (end.getRow() == start.getRow() && start.getCol() < end.getCol())
        {
            for (int i = start.getCol() + 1; i <= end.getCol(); i++) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getRow() == start.getRow() && start.getCol() > end.getCol())
        {
            for (int i = start.getCol() - 1; i >= end.getCol(); i--) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() < end.getRow())
        {
            for (int i = start.getRow() + 1; i <= end.getRow(); i++) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() > end.getRow())
        {
            for (int i = start.getRow() - 1; i >= end.getRow(); i--) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
