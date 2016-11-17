
/**
 * A king piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class King extends ChessPiece
{
    /**
     * Constructor for objects of class King. Places the king on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the king
     * @param game              game that the king belongs to
     */
    public King(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'K';
        } else
        {
            id = 'k';
        }
    }

    /**
     * Checks if the new location is a legal move. Legal if move is one space in any
     * direction
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 1) && newLocation.getCol() == location.getCol())
        {
            return true;
        } else if ((Math.abs(newLocation.getCol() - location.getCol()) == 1) && newLocation.getRow() == location.getRow())
        {
            return true;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks for a piece at the end location
     *
     * @param start             starting location to check
     * @param end               end location to check
     * @return                  true if there is no piece blocking the move, otherwise false
     */
    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().getPieceAt(end).getOwner() == this.getOwner())
        {
            return false;
        }
        return true;
    }

    protected void updateThreateningLocation(ChessLocation newLocation)
    {
        
    }
}
