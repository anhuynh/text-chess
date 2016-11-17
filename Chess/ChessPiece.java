import java.util.ArrayList;

/**
 * Superclass for chess pieces
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public abstract class ChessPiece implements ChessPieceInterface
{
    private ChessGame game; // determine which game this piece belongs to
    private String player;  // owner of the piece
    protected ChessLocation location; // current location of the piece
    protected char id; // identifies the type of chess piece
    private ArrayList<ChessLocation> threateningLocations;

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
        threateningLocations = new ArrayList<>();

        game.getChessBoard().placePieceAt(this, initialLocation); // place piece on board at initialLocation
    }

    /**
     * Moves the piece to the specified location if it is a legal move
     * 
     * @param  newLocation      location to move the piece to
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        if (legalMove(newLocation) && checkLineOfSight(location, newLocation) && checkEnd(newLocation))
        {
            getGame().getChessBoard().removePiece(location);   // removes the piece from the old location
            getGame().getChessBoard().placePieceAt(this, newLocation);  // places the piece at the new location
            location = newLocation;
            updateThreateningLocation(newLocation);
            printList();
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

    protected boolean checkEnd(ChessLocation end)
    {
        if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().getPieceAt(end).getOwner() == this.getOwner())
        {
            return false;
        }
        return true;
    }

    public ArrayList<ChessLocation> getThreateningLocations()
    {
        return threateningLocations;
    }

    protected abstract void updateThreateningLocation(ChessLocation newLocation);

    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (end.getRow() == start.getRow() && start.getCol() < end.getCol()) // if move to the right
        {
            for (int i = start.getCol() + 1; i <= end.getCol() - 1; i++) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getRow() == start.getRow() && start.getCol() > end.getCol()) // if move to the left
        {
            for (int i = start.getCol() - 1; i >= end.getCol() + 1; i--) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() < end.getRow()) // if move down
        {
            for (int i = start.getRow() + 1; i <= end.getRow() - 1; i++) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() > end.getRow()) // if move up
        {
            for (int i = start.getRow() - 1; i >= end.getRow() + 1; i--) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() > 0 && start.getCol() - end.getCol() > 0) // if move is up left
        {
            for (int row = start.getRow() - 1, col = start.getCol() - 1; row >= end.getRow() + 1; row--, col--)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() > 0 && start.getCol() - end.getCol() < 0) // if move is up right
        {
            for (int row = start.getRow() - 1, col = start.getCol() + 1; row >= end.getRow() + 1; row--, col++)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() < 0 && start.getCol() - end.getCol() > 0) // if move is down left
        {
            for (int row = start.getRow() + 1, col = start.getCol() - 1; row <= end.getRow() - 1; row++, col--)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() < 0 && start.getCol() - end.getCol() < 0) // if move is down left
        {
            for (int row = start.getRow() + 1, col = start.getCol() + 1; row <= end.getRow() - 1; row++, col++)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /** Each subclass has it's own algorithm for checking legality */
    protected abstract boolean legalMove(ChessLocation newLocation);

    public void printList()
    {
        for (ChessLocation location : threateningLocations) 
        {
            System.out.println(location.getRow() + "," + location.getCol());
        }
    }
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

    public void setLocation(ChessLocation newLocation)
    {
        location = newLocation;
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
