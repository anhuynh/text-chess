import java.util.Scanner;

/**
 * Handles the game loop
 * 
 * @author An Huynh
 * @version version 1.0 (2016.10.09)
 */
public class PlayGame
{
    /**
     * Starts a new game.
     *
     * @param args  Command line arguments
     */
    public static void main (String[] args)
    {
        Scanner reader = new Scanner(System.in);
        ChessGame game = new ChessGame("p1", "p2");
        
        System.out.println("\nMove the knight around the chess board!");
        
        boolean finished = false;
        while (!finished)
        {
            ChessLocation location = game.getKnight().getLocation();
            System.out.println("\nHere is the current board:\n");
            System.out.println(game.getChessBoard().toString());
            
            System.out.println("Knight's current location: " + location.getRow() + "," + location.getCol());
            System.out.println("Enter a new location (row,col e.g. \"2,2\") or enter \"q\" to quit.");
            System.out.print(">> ");
            String inp = reader.nextLine(); // takes in the user's input
            
            if (inp.equals("q"))
            {
                System.out.println("\nSee you next time!");
                finished = true;
                continue;
            }
            
            try // attempt to parse the input for a row and col
            {
                int row = Integer.parseInt(inp.split(",")[0].trim());
                int col = Integer.parseInt(inp.split(",")[1].trim());

                if (inBounds(row, col)) // checks bounds of the input to ensure it is between 0 and 7
                {
                    game.getKnight().moveTo(new ChessLocation(row,col));
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e)  // catches errors in the case that user inputs something other than the proper command
            {
                System.out.println("\nInvalid command");
            } catch (Exception e) // catches all other errors
            {
                System.out.println("\nI don't know what happened. You did something bad :(\n");
            }
        }
    }

    private static boolean inBounds(int row, int col)
    {
        if (row < 8 && row >= 0 && col < 8 && col >= 0) // checks bounds of the input to ensure it is between 0 and 7
        {
            return true;
        } else 
        {
            System.out.println("\nInvalid location: out of bounds");
            return false;
        }
    }
}
