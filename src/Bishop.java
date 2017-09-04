
/**
 * A bishop piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop. Places the bishop on the chess board.
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the bishop
     * @param game              game that the bishop belongs to
     */
    public Bishop(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'B';
        } else
        {
            id = 'b';
        }
    }

    /**
     * {@inheritDoc}
     * Legal move if move is is diagonal in any direction.
     */
    @Override
    public boolean moveTo(ChessLocation newLocation)
    {
        if (location.equals(newLocation))
        {
            System.out.println("\nCannot move to the same location.\n");
            return false;
        } else if (legalMove(newLocation)) // diagonal move
        {
            return super.moveTo(newLocation);
        }
        System.out.println("\nInvalid location: illegal move.\n");
        return false;
    }

    /**
     * {@inheritDoc}
     * Legal if move is diagonal in any direction.
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            return false;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol()))
        {
            return true;
        }
        return false;
    }

    protected void updateThreateningLocation(ChessLocation newLocation)
    {
        getThreateningLocations().clear();
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                ChessLocation checkLocation = new ChessLocation(row, col);
                if (legalMove(checkLocation) && checkLineOfSight(newLocation, checkLocation) && checkEnd(checkLocation))
                {
                    getThreateningLocations().add(checkLocation);
                }
            }
        }
    }
}
