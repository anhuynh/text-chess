
/**
 * A knight piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Knight
{
    private ChessGame game; // determine which game this piece belongs to
    private String owner; // owner of the piece
    private ChessLocation location; // current location of the knight piece

    /**
     * Constructor for objects of class Knight. Places the knight on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the knight
     * @param game              game that the knight belongs to
     */
    public Knight(String owner, ChessLocation initialLocation, ChessGame game)
    {
        this.game = game;
        this.owner = owner;
        location = initialLocation;
        
        game.getChessBoard().placePieceAt(this, initialLocation); // place this knight at the initialLocation on the board
    }

    /**
     * Moves the knight to the specified location if it is a legal move
     * 
     * @param  newLocation      location to move the piece to
     */
    public void moveTo(ChessLocation newLocation)
    {
        if (legalMove(newLocation))
        {
            game.getChessBoard().removePiece(this.location);   // removes the knight from the old location
            game.getChessBoard().placePieceAt(this, newLocation);  // places the knight at the new location
            setLocation(newLocation);
        } else
        {
            System.out.println("\nInvalid location: illegal move");
        }
    }

    /**
     * Checks if the new location is a legal move. Legal if row is +/- 2 and col is +/- 1
     * or row is +/- 1 and col is +/- 2
     *
     * @param newLocation  	    new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    private boolean legalMove(ChessLocation newLocation)
    {
        if (newLocation.getRow() == location.getRow() && newLocation.getCol() == location.getCol()) // if the location is the same it is still valid
        {
            return true;
        } else if ((Math.abs(newLocation.getRow() - location.getRow()) == 2) && (Math.abs(newLocation.getCol() - location.getCol()) == 1))
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

    /**
     * Sets the location of the knight
     *
     * @param location          new location
     */
    public void setLocation(ChessLocation location)
    {
        this.location = location;
    }

    public String getOwner()
    {
        return owner;
    }

    public ChessLocation getLocation()
    {
        return location;
    }

    public ChessGame getGame()
    {
        return game;
    }
}
