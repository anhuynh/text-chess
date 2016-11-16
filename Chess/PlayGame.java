import java.util.Scanner;

/**
 * Handles the game loop
 * 
 * @author An Huynh
 * @version 2016.11.06
 */
public class PlayGame
{
    private static ChessGame game;
    /**
     * Starts a new game.
     *
     * @param args  Command line arguments
     */
    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);
        String currentPlayer;
        String p1 = "Player 1";
        String p2 = "Player 2";
        boolean p1Turn = true;      // keeps track of whose turn it is; true for player 1, false for player 2
        boolean finished = false;
        boolean restart = true;
        while (!finished)
        {
            if (restart)
            {
                game = new ChessGame(p1, p2);
                System.out.println("\nA new game of chess has started!");
                p1Turn = true;
                restart = false;
            }

            currentPlayer = (p1Turn) ? p1 : p2;
            System.out.println("\n" + currentPlayer + ", it is your turn.\nHere is the current board:\n");
            System.out.println(game.getChessBoard().toString());
            System.out.println("Enter a location (row,col e.g. \"0,2\") of the piece to move, enter \"r\" to restart the game or enter \"q\" to quit.");
            System.out.print(">> ");
            String inp = reader.nextLine(); // takes in the user's input

            if (inp.equalsIgnoreCase("q")) // if user inputs "q", the game will finish
            {
                System.out.println("\nSee you next time!");
                finished = true;
                continue;
            }
            if (inp.equalsIgnoreCase("r"))
            {
                restart = true;
                continue;
            }
            
            ChessLocation sourceLocation = parseCommand(inp); // attempt to parse the input for a location
            if (sourceLocation != null)
            {
                if (game.getChessBoard().isPieceAt(sourceLocation.getRow(), sourceLocation.getCol())) // checks to see if there is a piece at the source location
                {
                    ChessPiece sourcePiece = game.getChessBoard().getPieceAt(sourceLocation);
                    if (sourcePiece.getOwner().equals(currentPlayer))
                    {
                        System.out.println("\nYou have selected " + sourcePiece.toString() + " at location " + sourcePiece.locationString() + ". Enter a location to move to");
                        System.out.print(">> ");
                        inp = reader.nextLine(); // takes in the user's input

                        ChessLocation destLocation = parseCommand(inp); // attempt to parse the input for a location
                        if (destLocation != null) 
                        {
                            if(sourcePiece.moveTo(destLocation))    // move the piece if it is a legal move
                            {
                                p1Turn = !p1Turn;
                            }
                        }
                    } else
                    {
                        System.out.println("\nThat piece is not yours! Please try again.");
                    }
                } else
                {
                    System.out.println("\nThere is no piece there. Please try again.\n");
                }
            }
        }
    }

    /**
     * Attempts to parse the input string for a location
     *
     * @param input             input string to parse location
     * @return                  ChessLocation containing the parsed location, returns null if input location is out of bounds or parsing fails
     */
    private static ChessLocation parseCommand(String input)
    {
        try // attempt to parse the input for a row and col
        {
            int row = Integer.parseInt(input.split(",")[0].trim());
            int col = Integer.parseInt(input.split(",")[1].trim());

            if (row < 8 && row >= 0 && col < 8 && col >= 0) // checks bounds of the input to ensure it is between 0 and 7
            {
                return new ChessLocation(row, col);
            }
            
            System.out.println("\nInvalid location: out of bounds\n");
            return null;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e)  // catches errors in the case that user inputs something other than the proper command
        {
            System.out.println("\nInvalid command\n");
            return null;
        } catch (Exception e) // catches all other errors
        {
            System.out.println("\nI don't know what happened. You did something bad :(\n");
            return null;
        }
    }
}
