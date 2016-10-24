
/**
 * A bishop piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop. Places the bishop on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the bishop
     * @param game              game that the bishop belongs to
     */
    public Bishop(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'B';
        } else
        {
            id = 'b';
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
            return true;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol()))
        {
            return true;
        } else 
        {
            return false;
        }
    }
}
