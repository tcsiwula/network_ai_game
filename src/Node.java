/**
 * Created by code on 4/30/15.
 */
public class Node
{
    // TODO - Helper variables for Node class
    // TODO Helper #1 - cornerCheck()
    // TODO Helper #2 - clusterCheck()
    // TODO Helper #3 - availabilityCheck()
    // TODO Helper #4 - goalCheck()
    // TODO Helper #5 - isValid()

    public int locationX = 0;
    public int locationY = 0;
    public boolean isPlayers1Move;
    public boolean isPlayers2Move;

    // TODO Helper #1 - cornerCheck()
    public boolean isCorner;

    // TODO Helper #2 - clusterCheck()
    public boolean isPlayedPlayer1 = false;
    public boolean hasNeighborPlayedPlayer1 = false;
    public boolean hasNeighborPlayedPlayer2 = false;

    public boolean isPlayedPlayer2 = false;
    public boolean isCluster = false;
    public boolean isValid = true;

    // TODO Helper #3 - availabilityCheck()


    // TODO Helper #4 - goalCheck()
    public boolean isTopRow;
    public boolean isLeftColumn;
    public boolean isBottomRow;
    public boolean isRightColumn;
    public boolean isPlayer1Goal = false;
    public boolean isPlayer2Goal = false;


    public String myAddress;
    public String directionOut;
    public String directionIn;
    public String[] illegalDirections = new String[2];
}
