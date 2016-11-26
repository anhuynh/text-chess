
/**
 * A queen piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen. Places the queen on the chess board.
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the queen
     * @param game              game that the queen belongs to
     */
    public Queen(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'Q';
        } else
        {
            id = 'q';
        }
    }

    /**
     * {@inheritDoc}
     * Legal move if move is horizontal, vertical or diagonal in any direction.
     */
    @Override
    public boolean moveTo(ChessLocation newLocation)
    {
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            System.out.println("\nCannot move to the same location.\n");
            return false;
        } else if (legalMove(newLocation)) // diagonal move or row/col move
        {
            return super.moveTo(newLocation);
        }
        System.out.println("\nInvalid location: illegal move.\n");
        return false;
    }

    /**
     * {@inheritDoc}
     * Legal if move is horizontal, vertical or diagonal in any direction.
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            return false;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol())) // diagonal move
        {
            return true;
        } else if (newLocation.getRow() == location.getRow() || newLocation.getCol() == location.getCol()) // row or column move
        {
            return true;
        }
        return false;
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
                if (legalMove(checkLocation) && checkLineOfSight(newLocation, checkLocation) && checkEnd(checkLocation))
                {
                    getThreateningLocations().add(checkLocation);
                }
            }
        }
    }
}
