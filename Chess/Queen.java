
/**
 * A queen piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen. Places the queen on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the queen
     * @param game              game that the queen belongs to
     */
    public Queen(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'Q';
        } else
        {
            id = 'q';
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
        if (newLocation.getRow() == location.getRow() && newLocation.getCol() == location.getCol()) // if the location is the same it is still valid
        {
            return false;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol()))
        {
            return true;
        } else if (newLocation.getRow() == location.getRow() || newLocation.getCol() == location.getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }
}
