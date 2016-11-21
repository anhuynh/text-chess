
/**
 * A pawn piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.27
 */
public class Pawn extends ChessPiece
{
    public boolean firstMove;
    /**
     * Constructor for objects of class Pawn. Places the pawn on the chess board.
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the pawn
     * @param game              game that the pawn belongs to
     */
    public Pawn(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);
        firstMove = true;

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'P';
        } else
        {
            id = 'p';
        }
    }

    /**
     * {@inheritDoc}
     * Legal if move is 2 or 1 spaces forward on first move and 1 space forward if not
     * on first move.
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (getOwner().equals(getGame().getP1())) // forward direction depends on player
        {
            if (firstMove && newLocation.getCol() == location.getCol() && (newLocation.getRow() - location.getRow() == 2 || newLocation.getRow() - location.getRow() == 1))
            {
                if (checkLineOfSight(location, newLocation))
                {
                    firstMove = false;
                }
                return true;
            } else if (newLocation.getRow() - location.getRow() == 1 && newLocation.getCol() == location.getCol())
            {
                return true;
            } else if (newLocation.getRow() - location.getRow() == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1 && getGame().getChessBoard().isPieceAt(newLocation.getRow(), newLocation.getCol()) && getGame().getChessBoard().getPieceAt(newLocation).getOwner() != this.getOwner())
            {
                firstMove = false;
                return true;
            }
        } else
        {
            if (firstMove && newLocation.getCol() == location.getCol() && (location.getRow() - newLocation.getRow() == 2 || location.getRow() - newLocation.getRow() == 1))
            {
                if (checkLineOfSight(location, newLocation))
                {
                    firstMove = false;
                }
                return true;
            } else if (location.getRow() - newLocation.getRow() == 1 && location.getCol() == newLocation.getCol())
            {
                return true;
            } else if (location.getRow() - newLocation.getRow() == 1 && Math.abs(newLocation.getCol() - location.getCol()) == 1 && getGame().getChessBoard().isPieceAt(newLocation.getRow(), newLocation.getCol()) && getGame().getChessBoard().getPieceAt(newLocation).getOwner() != this.getOwner())
            {
                firstMove = false;
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (getOwner().equals(getGame().getP1())) // forward direction depends on player
        {
            if (start.getCol() == end.getCol() && getGame().getChessBoard().isPieceAt(start.getRow() + 1, start.getCol()))
            {
                return false;
            }
        } else
        {
            if (start.getCol() == end.getCol() && getGame().getChessBoard().isPieceAt(start.getRow() - 1, start.getCol()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected void updateThreateningLocation(ChessLocation newLocation)
    {
        getThreateningLocations().clear();
        if (getOwner().equals(getGame().getP1())) // forward direction depends on player
        {
            ChessLocation right = new ChessLocation(newLocation.getRow() + 1, newLocation.getCol() + 1);
            ChessLocation left = new ChessLocation(newLocation.getRow() + 1, newLocation.getCol() - 1);
            if (newLocation.getCol() == 0)
            {
                addPiece(right);
            } else if (newLocation.getCol() == 7)
            {
                addPiece(left);
            } else
            {
                addPiece(right);
                addPiece(left);
            }
        } else
        {
            ChessLocation right = new ChessLocation(newLocation.getRow() - 1, newLocation.getCol() + 1);
            ChessLocation left = new ChessLocation(newLocation.getRow() - 1, newLocation.getCol() - 1);
            if (newLocation.getCol() == 0)
            {
                addPiece(right);
            } else if (newLocation.getCol() == 7)
            {
                addPiece(left);
            } else
            {
                addPiece(right);
                addPiece(left);
            }
        }
    }

    /**
     * Helper method for updateThreateningLocation. Adds the piece at location if the
     * location is empty or if the piece is not the same owner.
     *
     * @param location             location to add
     */
    private void addPiece(ChessLocation location)
    {
        if (!getGame().getChessBoard().isPieceAt(location.getRow(), location.getCol()))
        {
            getThreateningLocations().add(location);
        } else if (getGame().getChessBoard().getPieceAt(location).getOwner() != this.getOwner())
        {
            getThreateningLocations().add(location);
        }
    }
}
