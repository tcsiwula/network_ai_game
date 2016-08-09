
public class ComputerPlayer extends Player
{
    Network computerPlayersBoard;
    public ComputerPlayer(int playerNum)
    {
        super(playerNum);
        computerPlayersBoard = new Network();
        if (playerNum == 1)
        {
            computerPlayersBoard.isPlayers1Board = true;
        }
        else
        {
            computerPlayersBoard.isPlayers2Board = true;
        }
    }


    @Override
    public Move getMove()
    {
        return null;
    }

    @Override
    public void OpponentMove(Move m)
    {

    }





    public void minMax(){};
    public int evaluate()
    {

         int length;
        return 0;
    };

    



}
