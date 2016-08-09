import java.util.Scanner;

public class HumanPlayer extends Player
{

    Network humanPlayersBoard;

    SingletonScanner scanner;
    private static Scanner s = null;

    public HumanPlayer(int playerNum)
    {
        super(playerNum);
        humanPlayersBoard = new Network();
        if (playerNum == 1)
        {
            humanPlayersBoard.isPlayers1Board = true;
        }
        else
        {
            humanPlayersBoard.isPlayers2Board = true;
        }
    }

    @Override
    public Move getMove()
    {
        s = scanner.getInstance();
        return null;
    }

    @Override
    public void OpponentMove(Move m)
    {

    }


























}
