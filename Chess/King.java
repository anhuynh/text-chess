
/**
 * A king piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class King extends ChessPiece
{
    /**
     * Constructor for objects of class King. Places the king on the chess board.
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

    /**
     * {@inheritDoc}
     * Legal move if move is one space in any direction.
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
     * Checks if the destination is threatened by any pieces.
     * 
     * @param  destinationLocation      location to check
     * @return                          ChessPiece that is threatening the location, null if there is no threat
     */
    public ChessPiece locationInDanger(ChessLocation destinationLocation)
    {
        for (int row = 0; row < 8; row++) // look through the entire board
        {
            for (int col = 0; col < 8; col++)
            {
                ChessLocation checkLocation = new ChessLocation(row, col);
                ChessPiece checkPiece = getGame().getChessBoard().getPieceAt(checkLocation);
                if (checkPiece != null && checkPiece.getOwner() != this.getOwner()) // if there is a piece at the location and it belongs to opposing player
                {
                    checkPiece.updateThreateningLocation(checkLocation);
                    for (ChessLocation location : checkPiece.getThreateningLocations()) // go through the piece's threateningLocations
                    {
                        if (location.equals(destinationLocation))
                        {
                            return checkPiece; // return the piece if destinationLocation is in its threateningLocations
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if the king can make any moves that are not being threatened.
     * 
     * @return                  true if king can make a move, otherwise false
     */
    public boolean anyMovesLeft()
    {
        for (int row = 0; row < 8; row++) // go through entire board
        {
            for (int col = 0; col < 8; col++)
            {
                ChessLocation location = new ChessLocation(row, col);
                if (locationInDanger(location) == null && legalMove(location)) // check for a free move
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines if the king is in check.
     * 
     * @return                  ChessPiece that is threatening the king, otherwise return null
     */
    public ChessPiece check()
    {
        ChessPiece piece = locationInDanger(location);
        if (piece != null)
        {
            return piece;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Legal if move is one space in any direction.
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if ((Math.abs(newLocation.getRow() - location.getRow()) == 1 && newLocation.getCol() == location.getCol())
            || ((Math.abs(newLocation.getCol() - location.getCol()) == 1) && newLocation.getRow() == location.getRow())
            || (Math.abs(newLocation.getRow() - location.getRow()) == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1))
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
                if (legalMove(checkLocation) && checkEnd(checkLocation))
                {
                    getThreateningLocations().add(checkLocation);
                }
            }
        }
    }
}
