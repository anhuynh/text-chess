
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

    public boolean moveTo(ChessLocation newLocation)
    {
        ChessPiece checkPiece = locationInDanger(newLocation);
        if (checkPiece != null)
        {
            System.out.println("\nCannot move here. Threatened by " + checkPiece.toString() + ".");
            return false;
        }

        super.moveTo(newLocation);
        return true;
    }

    public ChessPiece locationInDanger(ChessLocation destinationLocation)
    {
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                ChessLocation checkLocation = new ChessLocation(row, col);
                ChessPiece checkPiece = getGame().getChessBoard().getPieceAt(checkLocation);
                if (checkPiece != null && checkPiece.getOwner() != this.getOwner())
                {
                    checkPiece.updateThreateningLocation(checkLocation);
                    for (ChessLocation location : checkPiece.getThreateningLocations())
                    {
                        if (location.equals(destinationLocation))
                        {
                            return checkPiece;
                        }
                    }
                }
            }
        }
        return null;
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
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 1) && newLocation.getCol() == location.getCol()
            || ((Math.abs(newLocation.getCol() - location.getCol()) == 1) && newLocation.getRow() == location.getRow())
            || (Math.abs(newLocation.getRow() - location.getRow()) == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1))
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
                if (legalMove(checkLocation) && checkEnd(checkLocation))
                {
                    getThreateningLocations().add(checkLocation);
                }
            }
        }
    }
}
