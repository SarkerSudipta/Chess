
import java.util.Scanner;

public class ConsoleDisplay implements GameDisplay {

    private Scanner scanner;

    public ConsoleDisplay() {
        scanner = new Scanner(System.in);
    }

    //prompts user for computer difficulty level ranging from 1 to maxDifficulty
    //0 - Exit game
    //invalid input like input not int or input not in difficulty range
    //returns the level of difficulty choosen
    @Override
    public int promptForOpponentDifficulty(int maxDifficulty) {

        int difficulty = -1;
        while (difficulty == -1) {
            System.out.println("Enter a difficulty between 1 to " + maxDifficulty);

            // if input is int, check if input is in range of difficulty or 0
            // if not continue prompting
            if (scanner.hasNextInt())
            {
                difficulty = scanner.nextInt();
                
                if (difficulty > maxDifficulty || difficulty < 1) 
                {
                    difficulty = -1;
                    System.out.println("Invalid level of difficulty chosen");
                    scanner.nextLine();
                }
                
            } 
            else //else invalid input, prompt again
            {
                System.out.println("Invalid input. Enter an integer between 1 and " + maxDifficulty);
                scanner.next();
            }
        }
        return difficulty;
    }

    public void displayBoard(Board gameBoard) {
        gameBoard.printBoard();
    }


    //prompts the user for a move in the form orignRow originCol , destRow destCol
    //returns the Move
    //if user entered 0, null returned i.e Exit game
    //if invalid input, prompt user for Move again
    public Move promptForMove() {

        
        int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
        Move move = null;

        while (move == null) 
        {   
            System.out.println("Enter your next move in the form 'originRow originColumn , destinationRow destinationColumn");
            if (scanner.hasNextInt()) 
            {
                row1 = scanner.nextInt();
                if(row1 == 0)
                    return null;

                if (scanner.hasNextInt()) 
                {
                    col1 = scanner.nextInt();
    
                    if (scanner.hasNext() && scanner.next().equals(",")) 
                    {
                        if (scanner.hasNextInt()) 
                        {
                            row2 = scanner.nextInt();

                            if (scanner.hasNextInt())
                            {
                                col2 = scanner.nextInt();
                                move = new Move(row1 - 1, col1 - 1, row2 - 1, col2 - 1);
                            }
                        }
                    }
                }
            }
            // if(move == null){
            //     scanner.nextLine();
            //     System.out.println("Invalid move. Try again");
            // }
        }
        return move;
    }

    //promts the user to select a piece for their pawn to be replaced with
    //it is called from the Logic class whenever it detects a pawn has been moved to the far end of the board
    //keeps prompting user for input until a valid input given or 0 entered for exiting the game
    //returns the piece chosen or 
    //returns null if user quits -- 0
    public Piece promptForPromotion()
    {   
        boolean validInput;
        Piece piecePromoted = null;

        do{
            System.out.println("Choose any piece from the following for your pawn to get promoted : Enter Q for Queen, B for Bishop, N for Knight, R for Rook");
            String piece = scanner.next();
            validInput = false;

            if(piece.equals("Q"))
                piecePromoted = new Queen("White");
            else if(piece.equals("B"))
                piecePromoted = new Bishop("White");
            else if(piece.equals("N"))
                piecePromoted = new Knight("piece");
            else if(piece.equals("R"))
                piecePromoted = new Rook("White");
            else if(piece.equals("0"))
                return null;
                
            if(piecePromoted != null)
                validInput = true;
        }while( !validInput );
        
        return piecePromoted;
    }

    public void gameOver(int winner)
    {
        if(winner == 1)
            System.out.println("You won :)");
        else if(winner == 2)
            System.out.println("Computer won :)");
        else if(winner == 0)
            System.out.println("Game forfeited");
    }



    public void summarizeMove(Move move, Piece pieceMoved, Piece pieceCaptured)
    {
        pieceMoved.printName();
        System.out.format(" moved from (%d, %d) to (%d, %d).", move.getOriginRow() + 1, move.getOriginCol() + 1, move.getDestRow() + 1, move.getDestCol() + 1);
        if(pieceCaptured == null)
            System.out.println(" No capture made.");
        else
        {
            pieceCaptured.printName();
            System.out.println(" captured");
        }
    }


    public boolean promptPlayAgain()
    {
        while( true )
        {   
            System.out.println("Would you like to play again? Please enter 0 for yes or 1 for no.");
            if(scanner.hasNextInt())
            {
                int prompt = scanner.nextInt();
                if(prompt == 0)
                    return true;
                else if(prompt == 1)
                    return false;
                else
                System.out.println("Not a valid input. Try again");
            }
        }
    }
}