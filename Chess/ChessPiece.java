
/**
 * Superclass for chess pieces
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public abstract class ChessPiece
{
    private ChessGame game; // determine which game this piece belongs to
    private String player;  // owner of the piece
    protected ChessLocation location; // current location of the piece
    protected char id; // identifies the type of chess piece

    /**
     * Constructor for chess pieces. Places the piece on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the piece
     * @param game              game that the piece belongs to
     */
    public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game)
    {
        this.game = game;
        player = owner;
        location = initialLocation;

        game.getChessBoard().placePieceAt(this, initialLocation); // place piece on board at initialLocation
    }

    /**
     * Moves the piece to the specified location if it is a legal move
     * 
     * @param  newLocation      location to move the piece to
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        if (legalMove(newLocation) && checkLineOfSight(location, newLocation))
        {
            getGame().getChessBoard().removePiece(location);   // removes the piece from the old location
            getGame().getChessBoard().placePieceAt(this, newLocation);  // places the piece at the new location
            location = newLocation;
            return true;
        } else
        {
            if (!legalMove(newLocation))
            {
                System.out.println("\nInvalid location: illegal move\n");
                return false;
            } else
            {
                System.out.println("\nInvalid location: blocked by another piece\n");
                return false;
            }
        }
    }

    /** Each subclass has it's own algorithm of checking line of sight */
    protected abstract boolean checkLineOfSight(ChessLocation start, ChessLocation end);

    /** Each subclass has it's own algorithm for checking legality */
    protected abstract boolean legalMove(ChessLocation newLocation);

    /**
    *
     * @return                  string that contains current location of the piece
     */
    public String locationString()
    {
        return location.getRow() + "," + location.getCol();
    }

    /**
     *
     * @return                  string of owner of piece
     */
    public String getOwner()
    {
        return player;
    }

    /**
     *
     * @return                  current location of the piece
     */
    public ChessLocation getLocation()
    {
        return location;
    }

    /**
     *
     * @return                  return game to which the piece belongs to
     */
    public ChessGame getGame()
    {
        return game;
    }

    /**
     * String representation of the piece
     *
     * @return                  string with character id of the piece
     */
    public String toString()
    {
        return String.valueOf(id);
    }
}
