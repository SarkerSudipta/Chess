public class Main {
    public static void main(String[] args) throws Exception {


       
        GameDisplay display = new ConsoleDisplay();
        GameLogic gameLogic = new GameLogic(display);
        gameLogic.playGame();
        
        while(display.promptPlayAgain())
            gameLogic.playGame();
        
    }

}
