
/**
 * A queen piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen. Places the queen on the chess board
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
     * Checks if the new location is a legal move. Legal if move is horizontal, vertical
     * or diagonal in any direction
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
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
