
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
        if (newLocation.getRow() == getLocation().getRow() && newLocation.getCol() == getLocation().getCol()) // if the location is the same it is still valid
        {
            return true;
        } else if (newLocation.getRow() == getLocation().getRow() || newLocation.getCol() == getLocation().getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }
}
