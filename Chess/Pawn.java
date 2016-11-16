
/**
 * A pawn piece that can be moved and checks the legality of the move
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class Pawn extends ChessPiece
{
    public boolean firstMove;
    /**
     * Constructor for objects of class Pawn. Places the pawn on the chess board
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
     * Checks if the new location is a legal move. Legal if move is 2 or 1 spaces forward
     * on first move and 1 space forward if not on first move
     *
     * @param newLocation       new location to check the legality of
     * @return                  true if the move is legal, otherwise false
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
                return true;
            }
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
        if (getOwner().equals(getGame().getP1())) // forward direction depends on player
        {
            if (end.getRow() - start.getRow() == 2 && (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) || getGame().getChessBoard().isPieceAt(start.getRow() + 1, start.getCol())))
            {
                return false;
            } else if (end.getRow() - start.getRow() == 1 && end.getCol() == start.getCol() && getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()))
            {
                return false;
            }
        } else
        {
            if (start.getRow() - end.getRow() == 2 && (getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()) || getGame().getChessBoard().isPieceAt(start.getRow() - 1, start.getCol())))
            {
                return false;
            } else if (start.getRow() - end.getRow() == 1 && end.getCol() == start.getCol() && getGame().getChessBoard().isPieceAt(end.getRow(), end.getCol()))
            {
                return false;
            }
        }
        return true;
    }
}
