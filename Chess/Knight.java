
/**
 * A knight piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Knight extends ChessPiece
{
    /**
     * Constructor for objects of class Knight. Places the knight on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the knight
     * @param game              game that the knight belongs to
     */
    public Knight(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'N';
        } else
        {
            id = 'n';
        }
    }

    /**
     * Checks if the new location is a legal move. Legal if row is +/- 2 and col is +/- 1
     * or row is +/- 1 and col is +/- 2
     *
     * @param newLocation  	    new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 2) && (Math.abs(newLocation.getCol() - location.getCol()) == 1))
        {
            return true;
        } else if ((Math.abs(newLocation.getRow() - location.getRow()) == 1) && (Math.abs(newLocation.getCol() - location.getCol()) == 2))
        {
            return true;
        } else 
        {
            return false;
        }
    }
}
