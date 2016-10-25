
/**
 * A king piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class King extends ChessPiece
{
    /**
     * Constructor for objects of class King. Places the king on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the king
     * @param game              game that the king belongs to
     */
    public King(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'K';
        } else
        {
            id = 'k';
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
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 1) && newLocation.getCol() == location.getCol())
        {
            return true;
        } else if ((Math.abs(newLocation.getCol() - location.getCol()) == 1) && newLocation.getRow() == location.getRow())
        {
            return true;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1)
        {
            return true;
        } else
        {
            return false;
        }
    }
}
