
/**
 * A knight piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class Knight extends ChessPiece
{
    /**
     * Constructor for objects of class Knight. Places the knight on the chess board.
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the knight
     * @param game              game that the knight belongs to
     */
    public Knight(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'N';
        } else
        {
            id = 'n';
        }
    }

    /**
     * {@inheritDoc}
     * Legal move if row is +/- 2 and col is +/- 1 or row is +/- 1 and col is +/- 2.
     */
    @Override
    public boolean moveTo(ChessLocation newLocation)
    {
        if (location.equals(newLocation))
        {
            System.out.println("\nCannot move to the same location.\n");
            return false;
        } else if (legalMove(newLocation))
        {
            return super.moveTo(newLocation);
        }
        System.out.println("\nInvalid location: illegal move.\n");
        return false;
    }

    /**
     * {@inheritDoc}
     * Legal if row is +/- 2 and col is +/- 1 or row is +/- 1 and col is +/- 2.
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 2) && (Math.abs(newLocation.getCol() - location.getCol()) == 1))
        {
            return true;
        } else if ((Math.abs(newLocation.getRow() - location.getRow()) == 1) && (Math.abs(newLocation.getCol() - location.getCol()) == 2))
        {
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected void updateThreateningLocation(ChessLocation newLocation)
    {
        getThreateningLocations().clear();
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                ChessLocation checkLocation = new ChessLocation(row, col);
                if (legalMove(checkLocation) && checkEnd(checkLocation))
                {
                    getThreateningLocations().add(checkLocation);
                }
            }
        }
    }
}
