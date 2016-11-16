
/**
 * A queen piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen. Places the queen on the chess board
     *
     * @param owner             owner to which the piece belongs to
     * @param initialLocation   starting location of the queen
     * @param game              game that the queen belongs to
     */
    public Queen(String owner, ChessLocation initialLocation, ChessGame game)
    {
        super(owner, initialLocation, game);

        if (owner.equals(game.getP1())) // assigns lettercase depending on owner
        {
            id = 'Q';
        } else
        {
            id = 'q';
        }
    }

    /**
     * Checks if the new location is a legal move. Legal if move is horizontal, vertical
     * or diagonal in any direction
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
     */
    protected boolean legalMove(ChessLocation newLocation)
    {
        if (location.equals(newLocation)) // if the location is the same it is still valid
        {
            return false;
        } else if (Math.abs(newLocation.getRow() - location.getRow()) == Math.abs(newLocation.getCol() - location.getCol())) // diagonal move
        {
            return true;
        } else if (newLocation.getRow() == location.getRow() || newLocation.getCol() == location.getCol()) // row or column move
        {
            return true;
        }
        return false;
    }

    /**
     * Checks in between the start and end locations for a piece that blocks the move
     *
     * @param start             starting location to check
     * @param end               end location to check
     * @return                  true if there is no piece blocking the move, otherwise false
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

        if (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) && getGame().getChessBoard().getPieceAt(end).getOwner() == this.getOwner())
        {
            return false;
        }
        return true;
    }
}
