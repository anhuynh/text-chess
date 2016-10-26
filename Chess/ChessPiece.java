
/**
 * Superclass for chess pieces
 * 
 * @author An Huynh
 * @version version 2.0 (2016.11.06)
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
     * @param initialLocation   starting position of the piece
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
     * Moves the knight to the specified location if it is a legal move
     * 
     * @param  newLocation      location to move the piece to
     */
    public void moveTo(ChessLocation newLocation)
    {
        if (legalMove(newLocation) && checkLineOfSight(location, newLocation))
        {
            getGame().getChessBoard().removePiece(location);   // removes the knight from the old location
            getGame().getChessBoard().placePieceAt(this, newLocation);  // places the knight at the new location
            setLocation(newLocation);
        } else
        {
            if (!legalMove(newLocation))
            {
                System.out.println("\nInvalid location: illegal move\n");
            } else
            {
                System.out.println("\nInvalid location: blocked by another piece\n");
            }
        }
    }

    protected abstract boolean checkLineOfSight(ChessLocation start, ChessLocation end);

    protected abstract boolean legalMove(ChessLocation newLocation);

    /**
     * Sets the location of the piece
     *
     * @param location          new location
     */
    public void setLocation(ChessLocation location)
    {
        this.location = location;
    }

    public String getOwner()
    {
        return player;
    }

    public ChessLocation getLocation()
    {
        return location;
    }

    public ChessGame getGame()
    {
        return game;
    }

    public String toString()
    {
        return String.valueOf(id);
    }
}
