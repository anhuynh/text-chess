import java.util.ArrayList;

/**
 * Superclass for chess pieces
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public abstract class ChessPiece implements ChessPieceInterface
{
    private ChessGame game; // determine which game this piece belongs to
    private String player;  // owner of the piece
    protected ChessLocation location; // current location of the piece
    protected char id; // identifies the type of chess piece
    private ArrayList<ChessLocation> threateningLocations;

    /**
     * Constructor for chess pieces. Places the piece on the chess board.
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the piece
     * @param game              game that the piece belongs to
     */
    public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game)
    {
        this.game = game;
        player = owner;
        location = null;
        threateningLocations = new ArrayList<>();

        game.getChessBoard().placePieceAt(this, initialLocation); // place piece on board at initialLocation
    }

    /**
     * Moves the piece to the specified location if it is a legal move.
     * 
     * @param  newLocation      location to move the piece to
     * @return                  true if move is successful, otherwise false
     */
    public boolean moveTo(ChessLocation newLocation)
    {
        if (legalMove(newLocation) && checkLineOfSight(location, newLocation) && checkEnd(newLocation))
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

    /**
     * Checks the end location of the move for a piece.
     * 
     * @param  end              location to check
     * @return                  true if there is no piece at end or if piece at end is opposing player's piece, otherwise false
     */
    protected boolean checkEnd(ChessLocation end)
    {
        if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().getPieceAt(end).getOwner() == this.getOwner())
        {
            return false;
        }
        return true;
    }

    /**
     *
     * @return                  threateningLocations array
     */
    public ArrayList<ChessLocation> getThreateningLocations()
    {
        return threateningLocations;
    }

    /**
     * Updates the locations that the piece threatens.
     * 
     * @param  newLocation              location to check
     */
    protected abstract void updateThreateningLocation(ChessLocation newLocation);

    /**
     * Checks line of sight from start to finish to ensure that there are no pieces
     * blocking a move.
     * 
     * @param start             starting location to check
     * @param end               end location to check
     * @return                  true if line of sight is clear, otherwise false
     */
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

    /**
     * Checks if the new location is a legal move.
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected abstract boolean legalMove(ChessLocation newLocation);

    /**
     * REMOVE LATER
     */
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

    /**
     *
     * @param newLocation       newlocation to set the piece to
     */
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
     * String representation of the piece.
     *
     * @return                  string with character id of the piece
     */
    public String toString()
    {
        return String.valueOf(id);
    }
}
