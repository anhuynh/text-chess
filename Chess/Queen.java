
/**
 * A queen piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen. Places the queen on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting position of the queen
     * @param game              game that the queen belongs to
     */
    public Queen(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1()))
        {
            id = 'Q';
        } else
        {
            id = 'q';
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
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            return false;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol()))
        {
            return true;
        } else if (newLocation.getRow() == location.getRow() || newLocation.getCol() == location.getCol())
        {
            return true;
        } else 
        {
            return false;
        }
    }

    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end)
    {
        if (end.getRow() == start.getRow() && start.getCol() < end.getCol())
        {
            for (int i = start.getCol() + 1; i <= end.getCol(); i++) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getRow() == start.getRow() && start.getCol() > end.getCol())
        {
            for (int i = start.getCol() - 1; i >= end.getCol(); i--) 
            {
                if (getGame().getChessBoard().isPieceAt(start.getRow(), i))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() < end.getRow())
        {
            for (int i = start.getRow() + 1; i <= end.getRow(); i++) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        } else if (end.getCol() == start.getCol() && start.getRow() > end.getRow())
        {
            for (int i = start.getRow() - 1; i >= end.getRow(); i--) 
            {
                if (getGame().getChessBoard().isPieceAt(i, start.getCol()))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() > 0 && start.getCol() - end.getCol() > 0) 
        {
            for (int row = start.getRow() - 1, col = start.getCol() - 1; row >= end.getRow(); row--, col--)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() > 0 && start.getCol() - end.getCol() < 0)
        {
            for (int row = start.getRow() - 1, col = start.getCol() + 1; row >= end.getRow(); row--, col++)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() < 0 && start.getCol() - end.getCol() > 0)
        {
            for (int row = start.getRow() + 1, col = start.getCol() - 1; row <= end.getRow(); row++, col--)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        } else if (start.getRow() - end.getRow() < 0 && start.getCol() - end.getCol() < 0)
        {
            for (int row = start.getRow() + 1, col = start.getCol() + 1; row <= end.getRow(); row++, col++)
            {
                if (getGame().getChessBoard().isPieceAt(row, col))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
