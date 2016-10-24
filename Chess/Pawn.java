
/**
 * A pawn piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Pawn extends ChessPiece
{
    public boolean firstMove;
    /**
     * Constructor for objects of class Pawn. Places the pawn on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the pawn
     * @param game              game that the pawn belongs to
     */
    public Pawn(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);
        firstMove = true;

        if (owner.equals(game.getP1()))
        {
            id = 'P';
        } else
        {
            id = 'p';
        }
    }

    /**
     * Checks if the new location is a legal move. 
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (newLocation.getRow() == getLocation().getRow() && newLocation.getCol() == getLocation().getCol()) // if the location is the same it is still valid
        {
            return true;
        } else if (firstMove && newLocation.getCol() == getLocation().getCol() && (newLocation.getRow() - getLocation().getRow() == 2 || newLocation.getRow() - getLocation().getRow() == 1))
        {
            firstMove = false;
            return true;
        } else if (newLocation.getRow() - getLocation().getRow() == 1 && newLocation.getCol() == getLocation().getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }
}
