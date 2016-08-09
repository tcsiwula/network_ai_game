
import java.util.Random;
import java.util.Scanner;

public class Network
{

  public Network()
  {

  }






    public String[][] frontEndBoard;
    public Node[][] backEndBoard;
    public boolean[][] player1board;
    public int p1Net = 0;
    public int p1Sum = 0;
    public int p2Net = 0;
    public int p2Sum = 0;
    public int noMovesFound = 0;
    public Node[] players1MovesMade = new Node[30];
    public Node[] players2MovesMade = new Node[30];
    public String[] rowCharacters;
    public String[] colNum;
    public boolean[][] player1GoalRegions, player2GoalRegions;
    public boolean noPlayerHasWinningNetwork = true;
    public int numberOfHumanPlayers =0;
    public boolean gameModeCvC, gameModeHvC, gameModeHvH;
    public HumanPlayer humanPlayer1, humanPlayer2;
    public ComputerPlayer computerPlayer1, computerPlayer2;
    public String p1Move, p2Move;
    public boolean isPlayers1Move = true, isPlayers2Move = false;
    public boolean p1NetworkSizeCheck, checkGoal, checkDirection, checkForBlocks, player1Wins, player2Wins;
    public Node[] players1Network = new Node[20];
    public Node[] players2Network = new Node[20];
    public boolean[][] player2board;
    public int player2NetworkSize = 0;
    public Node[] player1NetworkMoves = new Node[30];
    public Node[] player2PlayedMoves;
    public boolean isWinningNetwork;
    public String[] player1Move = new String[9];
    public String[] player2Move = new String[9];
    public int[] index = new int[2];
    public Node currentMove;
    public String[] computer1ValidMoves;
    public String[] computer2ValidMoves;
    public int c1MovesIndex = 0, c2MovesIndex = 0;
    public int c1RandomMove, c2RandomMove;
    public int p1MoveX,p2MoveX, p1MoveY,p2MoveY;
    public boolean isPlayers1Board = false;
    public boolean isPlayers2Board = false;
    public boolean isServersBoard = false;
    public Move move;

    //   TODO - meow meow meow mix
    //   TODO - Finish winning network
    //   TODO - Get working with 2 human players
    //   TODO - Get working with computer player, just valid random moves
    //   TODO - Get working with smart ai player, beta alpha prune and min/max

    public static void main(String[] args)
    {
        Network SidSacksonsGameCalledNetwork = new Network();
        SidSacksonsGameCalledNetwork.loadPlayers();
        SidSacksonsGameCalledNetwork.initializeBoard();
        SidSacksonsGameCalledNetwork.startGame();
    }

    // TODO - Helper functions for SidSacksonsGameCalledNetwork object referenced above
    // TODO Helper #1 - loadPlayers()
    // TODO Helper #2 - initializeBoard()
    // TODO Helper #3 - startGame()




    // TODO Helper #1 - loadPlayers()
    public void loadPlayers()
    {
       Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game called Network by Sid Sacksons!");

        System.out.println("Enter the number of human players (0, 1 or 2): __");
        numberOfHumanPlayers = scanner.nextInt();
        System.out.println();

        if (numberOfHumanPlayers > 2 || numberOfHumanPlayers < 0)
        {
            System.out.println("Incorrect number of players. players = " + numberOfHumanPlayers);
            System.out.println();
            loadPlayers();
        }

        if (numberOfHumanPlayers == 0)
        {
            computerPlayer1 = new ComputerPlayer(1);
            computerPlayer2 = new ComputerPlayer(2);
            gameModeCvC = true;
            gameModeHvH = false;
            gameModeHvC = false;
        }

        if (numberOfHumanPlayers == 1)
        {
            humanPlayer1 = new HumanPlayer(1);
            computerPlayer1 = new ComputerPlayer(2);
            gameModeCvC = false;
            gameModeHvH = false;
            gameModeHvC = true;
        }

        if (numberOfHumanPlayers == 2)
        {
            humanPlayer1 = new HumanPlayer(1);
            humanPlayer2 = new HumanPlayer(2);
            gameModeCvC = false;
            gameModeHvH = true;
            gameModeHvC = false;
        }
    }

    // TODO Helper #2 - initializeBoard()
    public void initializeBoard()
    {
        frontEndBoard = new String[8][8];
        player1board = new boolean[8][8];
        backEndBoard = new Node[8][8];
        player1GoalRegions = new boolean[8][8];
        player2GoalRegions = new boolean[8][8];
        rowCharacters = new String[8];
        colNum = new String[8];
        rowCharacters[0] = "A";
        rowCharacters[1] = "B";
        rowCharacters[2] = "C";
        rowCharacters[3] = "D";
        rowCharacters[4] = "E";
        rowCharacters[5] = "F";
        rowCharacters[6] = "G";
        rowCharacters[7] = "H";
        colNum[0] = "1";
        colNum[1] = "2";
        colNum[2] = "3";
        colNum[3] = "4";
        colNum[4] = "5";
        colNum[5] = "6";
        colNum[6] = "7";
        colNum[7] = "8";

        for (int i = 0; i < rowCharacters.length; i++)
        {
            for (int j = 0; j < colNum.length; j++)
            {
                Node node = new Node();
                node.locationX = i;
                node.locationY = j;
                backEndBoard[i][j] = node;
                frontEndBoard[i][j] = ".";
                player1board[i][j] = false;
                // top row
                if (i == 0)
                {
                    node.isTopRow = true;
                    node.isPlayer1Goal = true;
                    if (j == 0 || j == 7)
                    {
                        node.isCorner = true;
                        node.isPlayer1Goal = false;
                        node.isPlayer2Goal = false;
                    }
                }
                // bottom row
                if (i == 7)
                {
                    node.isBottomRow = true;
                    node.isPlayer1Goal = true;
                    if (j == 0 || j == 7)
                    {
                        node.isCorner = true;
                        node.isPlayer2Goal = false;
                        node.isPlayer1Goal = false;
                    }
                }
                // left column
                if (j == 0)
                {
                    node.isLeftColumn = true;
                    node.isPlayer2Goal = true;
                }
                // right column
                if (j == 7)
                {
                    node.isRightColumn = true;
                    node.isPlayer2Goal = true;
                }

                if (i == 0)
                {
                    node.myAddress = "A";
                }

                if (i == 1)
                {
                    node.myAddress = "B";
                }
                if (i == 2)
                {
                    node.myAddress = "C";
                }
                if (i == 3)
                {
                    node.myAddress = "D";
                }
                if (i == 4)
                {
                    node.myAddress = "E";
                }
                if (i == 5)
                {
                    node.myAddress = "F";
                }

                if (i == 6)
                {
                    node.myAddress = "G";
                }

                if (i == 7)
                {
                node.myAddress = "H";
                }

                if (j == 0)
                {
                    node.myAddress += "1";
                }

                if (j == 1)
                {
                    node.myAddress += "2";
                }
                if (j == 2)
                {
                    node.myAddress += "3";
                }
                if (j == 3)
                {
                    node.myAddress += "4";
                }
                if (j == 4)
                {
                    node.myAddress += "5";
                }
                if (j == 5)
                {
                    node.myAddress += "6";
                }
                if (j == 6)
                {
                    node.myAddress += "7";
                }
                if (j == 7)
                {
                    node.myAddress += "8";
                }
            }
        }
        printBoard();
    }
    // TODO Helper #3 - startGame()
    public void startGame()
    {
       for (int i = 0; i < 100; i++)
        {
            if (gameModeCvC)
            {
                getComputerMove();
                authenticateMove();
                if (currentMove.isValid)
                {
                    System.out.println("Current move is valid. Now making the move.");
                    makeMove();
                }
            }
            if(gameModeHvH)
            {
                getMove();
                authenticateMove();
                if (currentMove.isValid)
                {
                    System.out.println("Current move is valid. Now making the move.");
                    makeMove();
                }
            }
            if (gameModeHvC)
            {
                if ( isPlayers1Move) // human first
                {
                    getMove();
                    authenticateMove();
                    if (currentMove.isValid)
                    {
                        System.out.println("Current move is valid. Now making the move.");
                        makeMove();
                    }
                }
                else                // computer second
                {
                    getComputerMove();
                    authenticateMove();
                    if (currentMove.isValid)
                    {
                        System.out.println("Current move is valid. Now making the move.");
                        makeMove();
                    }
                }
            }
        }
    }

    // TODO - Helper functions for getMove()
    // TODO 1. getMove()


    public void getComputerMove()
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String nextMove;

        if (isPlayers1Move)
        {
            System.out.println("Computer player 1: Enter row and column position to place piece: __");
            nextMove = scanner.nextLine();
            getValidMoves();
            if (c1MovesIndex > 0)
            {
                c1RandomMove = random.nextInt(c1MovesIndex);
            }
            else
            {
                isPlayers1Move = false;
                getComputerMove();
            }
            p1Move = computer1ValidMoves[c1RandomMove];
            System.out.println("p1Move = " + p1Move);
        }
        else
        {
            System.out.println("Computer player 2: Enter row and column position to place piece: __");
            nextMove = scanner.nextLine();
            getValidMoves();

            if (c2MovesIndex > 0)
            {
                c2RandomMove = random.nextInt(c2MovesIndex);
            }
            else
            {
                isPlayers1Move = true;
                getComputerMove();
            }
            p2Move = computer2ValidMoves[c2RandomMove];
            System.out.println("p2Move = " + p2Move);
        }
    }

    // TODO Helper # - getValidMoves()
    public void getValidMoves()
    {
        Node tmp;
        computer2ValidMoves = new String[100];
        computer1ValidMoves = new String[100];
        c1MovesIndex = 0;
        c2MovesIndex = 0;

        for (int i = 0; i < rowCharacters.length; i++)
        {
            for (int j = 0; j < colNum.length; j++)
            {
                tmp = backEndBoard[i][j];

                if(!tmp.isPlayedPlayer1 && !tmp.isPlayedPlayer2 && !tmp.isCluster && !tmp.isCorner)
                {
                    if(isPlayers1Move)
                    {
                        if ( !tmp.isPlayer2Goal )
                        {
                            String validMove = tmp.myAddress;

                            //System.out.println("tmp.myAddress = " + tmp.myAddress);
                            computer1ValidMoves[c1MovesIndex] = validMove;
                            c1MovesIndex++;
                        }
                    }
                    else
                    {
                        if ( !tmp.isPlayer1Goal)
                        {
                            String validMove = tmp.myAddress;
                            //System.out.println("tmp.myAddress = " + tmp.myAddress);

                            computer2ValidMoves[c2MovesIndex] = validMove;
                            c2MovesIndex++;
                        }
                    }
                }
            }
        }


        if(isPlayers1Move)
        {
            if(c1MovesIndex == 0)
            {
                System.out.println("Computer player1 was not able to find a move.");
                noMovesFound++;
                if( noMovesFound > 7)
                {
                    System.out.println("There are no more moves left. Tie game!");
                    System.exit(0);
                }
            }
        }
        else
        {
            if(c2MovesIndex == 0)
            {
                System.out.println("Computer player2 was not able to find a move.");
                noMovesFound++;
                if( noMovesFound > 7)
                {
                    System.out.println("There are no more moves left. Tie game!");
                    System.exit(0);
                }
            }
        }
    }

    public void getMove()
    {
        Scanner scanner = new Scanner(System.in);

        if ( isPlayers1Move  )
        {
            System.out.println("Human player 1: Enter row and column position to place piece: __");

            p1Move = scanner.nextLine();

            if (p1Move.length() != 2)
            {
                System.out.println("Incorrect row and column input. You entered " + p1Move);
                System.out.println();
                getMove();
            }
        }
        else
        {
            System.out.println("Human player 2: Enter row and column position to place piece: __");
            p2Move = scanner.nextLine();
            if (p2Move.length() != 2)
            {
                System.out.println("Incorrect row and column input. You entered " + p2Move);
                System.out.println();
                getMove();
            }
        }
    }

    // TODO - Helper functions for authenticateMove()
    // TODO Helper #1 - authenticateMove()
    // TODO Helper #2 - parseMove()
    // TODO Helper #3 - checkCluster()
    // TODO Helper #4 - checkLocation()


    // TODO Helper #1 - authenticateMove()
    public void authenticateMove()
    {
        parseMove();
        checkCluster();
        checkLocation();
    }
    // TODO Helper #2 - parseMove()
    public void parseMove()
    {
        if (isPlayers1Move)
        {
            String letter = p1Move.substring(0, 1);
            String number = p1Move.substring(1, 2);
            String tmp, tmp2;
            for (int i = 0; i < rowCharacters.length; i++)
            {
                tmp = rowCharacters[i];
                if (tmp.equalsIgnoreCase(letter))
                {
                    //index[0] = i;
                    p1MoveX = i;
                }
            }
            for (int i = 0; i < colNum.length; i++)
            {
                tmp2 = colNum[i];
                if (tmp2.equalsIgnoreCase(number))
                {
                    //index[1] = i;
                    p1MoveY = i;
                }
            }
            currentMove = backEndBoard[p1MoveX][p1MoveY];
        }
        else
        {
            String letter = p2Move.substring(0, 1);
            String number = p2Move.substring(1, 2);
            String tmp, tmp2;
            for (int i = 0; i < rowCharacters.length; i++)
            {
                tmp = rowCharacters[i];
                if (tmp.equalsIgnoreCase(letter))
                {
                    //index[0] = i;
                    p2MoveX = i;
                }
            }
            for (int i = 0; i < colNum.length; i++)
            {
                tmp2 = colNum[i];
                if (tmp2.equalsIgnoreCase(number))
                {
                    //index[1] = i;
                    p2MoveY = i;
                }
            }
            currentMove = backEndBoard[p2MoveX][p2MoveY];
        }
    }
    // TODO Helper #3 - checkCluster()
    public void checkCluster()
    {
        Node myNeighbor;
        for (int i = currentMove.locationX - 1; i <= currentMove.locationX + 1; i++)
        {
            for (int j = currentMove.locationY - 1; j <= currentMove.locationY + 1; j++)
            {
                if ( i >= 0 && i <= 7 && j >= 0 && j <= 7 )
                {
                    myNeighbor = backEndBoard[i][j];
                    if (isPlayers1Move)
                    {
                        if (myNeighbor.isCluster && currentMove.hasNeighborPlayedPlayer1)
                        {
                            currentMove.isCluster = true;
                        }
                    }
                    else
                    {
                        if (myNeighbor.isCluster && currentMove.hasNeighborPlayedPlayer2)
                        {
                            currentMove.isCluster = true;
                        }
                    }
                }
            }
        }
    }

    // TODO Helper #4 - checkLocation()
    public void checkLocation()
    {
        String error = "";

        if ( currentMove.isPlayer1Goal || currentMove.isPlayer2Goal )
        {
            if (isPlayers1Move && currentMove.isPlayer2Goal)
            {
                currentMove.isValid = false;
                error = "Position in opponent goal.";
            }

            if (!isPlayers1Move && currentMove.isPlayer1Goal)
            {
                currentMove.isValid = false;
                error = "Position in opponent goal.";
            }
        }

        if ( currentMove.isCorner )
        {
            currentMove.isValid = false;
            error = "Position in corner.";
        }

        if ( currentMove.isCluster )
        {
            currentMove.isValid = false;
            error = "Position would form clump.";
        }

        if ( currentMove.isPlayedPlayer1 || currentMove.isPlayedPlayer2 )
        {
            currentMove.isValid = false;
            error = "Position already played.";
        }

        if (currentMove.isValid == false)
        {
            if (isPlayers1Move)
            {
                System.out.println("Player 1: Bad move!");
                System.out.println(error);
                System.out.println();
                startGame();
            }
            else
            {
                System.out.println("Player 2: Bad move!");
                System.out.println(error);
                System.out.println();
                startGame();
            }
        }
    }
    // TODO - Helper functions for makeMove()
    // TODO Helper #1 - makeMove()
    // TODO Helper #2 - updateNeighbors()
    // TODO Helper #3 - updateNetwork()
    // TODO Helper #4 - updateMove()
    // TODO Helper #5 - checkNetwork()


    // TODO Helper #1 - makeMove()
    public void makeMove()
    {
        updateMove();
        updateNeighbors();
        updateNetwork();
        checkNetwork();
    }
    // TODO Helper #1 - updateMove()
    public void updateMove()
    {
        if (currentMove.isValid)
        {
            if (isPlayers1Move)
            {
                //1
                currentMove.isPlayedPlayer1 = true;
                players1MovesMade[p1Sum] = currentMove;
                p1Sum++;
                frontEndBoard[currentMove.locationX][currentMove.locationY] = "1";
            }
            else
            {
                currentMove.isPlayedPlayer2 = true;
                players2MovesMade[p2Sum] = currentMove;
                p2Sum++;
                frontEndBoard[currentMove.locationX][currentMove.locationY] = "2";
            }
        }
    }
    // TODO Helper #2 - updateNeighbors()
    public void updateNeighbors()
    {
            Node myNeighbor;
            if (currentMove.isValid)
            {
                for (int i = currentMove.locationX - 1; i <= currentMove.locationX + 1; i++)
                {
                    for (int j = currentMove.locationY - 1; j <= currentMove.locationY + 1; j++)
                    {
                        if (i >= 0 && i <= 7 && j >= 0 && j <= 7 )
                        {
                            if (i != currentMove.locationX || j != currentMove.locationY)
                            {
                                myNeighbor = backEndBoard[i][j];
                                //2

                                if (isPlayers1Move)
                                {
                                    myNeighbor.hasNeighborPlayedPlayer1 = true;
                                }
                                else
                                {
                                    myNeighbor.hasNeighborPlayedPlayer2 = true;

                                }

                            }
                        }

                    }

                }
            }
        // check for clusters
        for (int i = currentMove.locationX - 1; i <= currentMove.locationX + 1; i++)
        {
            //System.out.print("Checking: ");
            for (int j = currentMove.locationY - 1;j <= currentMove.locationY + 1; j++)
            {
                //System.out.print(" [" +i+"]["+j+"]");
                if (i >= 0 && i <= 7 && j >= 0 && j <= 7 )
                {
                    if (i != currentMove.locationX || j != currentMove.locationY)
                    {
                        myNeighbor = backEndBoard[i][j];
                        //System.out.print(" [" + i + "][" + j + "]");
                        if (isPlayers1Move)
                        {
                            if (myNeighbor.isPlayedPlayer1)
                            {
                                System.out.println("Player1 - cluster now exists.");
                                currentMove.isCluster = true;
                                myNeighbor.isCluster = true;
                            }
                        }
                        else
                        {
                            if (myNeighbor.isPlayedPlayer2)
                            {
                                System.out.println("Player2 - cluster now exists.");
                                currentMove.isCluster = true;
                                myNeighbor.isCluster = true;
                            }
                        }
                    }
                }
            }
        }
    }
    // TODO Helper #3 - updateNetwork()
    public void updateNetwork()
    {
       // boolean connected = false;

        if (isPlayers1Move)
        {

            if (p1Net == 0)
            {
                players1Network[p1Net] = players1MovesMade[p1Sum - 1];
                p1Net++;
            }
            players1Network[p1Net] = players1MovesMade[p1Sum - 1];
            p1Net++;
        }
        else
        {
            if (p2Net == 0)
            {
                players2Network[p2Net] = players2MovesMade[p2Sum - 1];
                p2Net++;
            }
            players2Network[p2Net] = players2MovesMade[p2Sum - 1];
            p2Net++;

        }




//
//        if (p1Sum > 1)
//        {
//            Node start = players1MovesMade[p1Sum -2];
//                // south connection
//                if (start.locationY == currentMove.locationY && start.locationX < currentMove.locationX)
//                {
//                    start.directionOut = "south";
//                    currentMove.directionIn = "north";
//                    currentMove.illegalDirections[0] = "south";
//                    currentMove.illegalDirections[1] = "north";
//                    connected = true;
//
//                }
//
//                //  south east connection
//                if (start.locationY < currentMove.locationY && start.locationX < currentMove.locationX)
//                {
//                    start.directionOut = "southeast";
//                    currentMove.directionIn = "northwest";
//                    currentMove.illegalDirections[0] = "southeast";
//                    currentMove.illegalDirections[1] = "northwest";
//                    connected = true;
//                }
//
//                //  south west connection
//                if (start.locationY > currentMove.locationY && start.locationX > currentMove.locationX)
//                {
//                    start.directionOut = "southwest";
//                    currentMove.directionIn = "northeast";
//                    currentMove.illegalDirections[0] = "northeast";
//                    currentMove.illegalDirections[1] = "southwest";
//                    connected = true;
//                }
//
//                //  west connection, x same and start y > curr y
//                if (start.locationY > currentMove.locationY && start.locationX == currentMove.locationX)
//                {
//                    start.directionOut = "west";
//                    currentMove.directionIn = "east";
//                    currentMove.illegalDirections[0] = "west";
//                    currentMove.illegalDirections[1] = "east";
//                    connected = true;
//                }
//
//                //  east connection, x same and start y < curr y
//                if (start.locationY < currentMove.locationY && start.locationX == currentMove.locationX)
//                {
//                    start.directionOut = "east";
//                    currentMove.directionIn = "west";
//                    currentMove.illegalDirections[0] = "east";
//                    currentMove.illegalDirections[1] = "west";
//                    connected = true;
//                }
//                        // north connection
//                if (start.locationY == currentMove.locationY && start.locationX > currentMove.locationX)
//                {
//                    start.directionOut = "north";
//                    currentMove.directionIn = "south";
//                    currentMove.illegalDirections[0] = "north";
//                    currentMove.illegalDirections[1] = "south";
//                    connected = true;
//                }
//
//                    // north west connection
//                if (start.locationY > currentMove.locationY && start.locationX > currentMove.locationX)
//                {
//                    start.directionOut = "northwest";
//                    currentMove.directionIn = "southwest";
//                    currentMove.illegalDirections[0] = "northwest";
//                    currentMove.illegalDirections[1] = "southwest";
//                    connected = true;
//                }
//
//                // north east connection
//                if (start.locationY < currentMove.locationY && start.locationX > currentMove.locationX)
//                {
//                    start.directionOut = "northeast";
//                    currentMove.directionIn = "southwest";
//                    currentMove.illegalDirections[0] = "northwest";
//                    currentMove.illegalDirections[1] = "southwest";
//                    connected = true;
//                }
//            }
//
//        if ( connected )
//        {
//            if (p1Net == 0)
//            {
//                players1Network[p1Net] = players1MovesMade[p1Sum - 1];
//                p1Net++;
//            }
//            players1Network[p1Net] = players1MovesMade[p1Sum - 1];
//            p1Net++;
//        }
    }
    // TODO Helper #5 - checkNetwork()
    public void checkNetwork()
    {
        checkSize();
        checkForBlocks();
        verifyNetworkChecks();
    }

    public void checkSize()
    {
        if ( isPlayers1Move )
        {
            if (  p1Net >= 5 )
            {
                p1NetworkSizeCheck = true;
            }
            else
            {
                p1NetworkSizeCheck = false;
            }
        }
        else
        {
            if (  p2Net >= 5 )
            {
                p1NetworkSizeCheck = true;
            }
            else
            {
                p1NetworkSizeCheck = false;
            }
        }

        if ( p1NetworkSizeCheck)
        {
            if ( isPlayers1Move )
            {
                System.out.println("Player1 - Network size checks.");
                checkGoal();
            }
            else
            {
                System.out.println("Player2 - Network size checks.");
                checkGoal();
            }
        }
    }
    public void checkGoal()
    {
        boolean startGoal = false, endGoal = false, middleGoal = true;
        if (isPlayers1Move)
        {
            Node firstMove = players1Network[0];
            if ( firstMove.isPlayer1Goal && firstMove.isTopRow)
            {
                startGoal = true;
            }
            Node lastMove = players1Network[p1Net-1];
            if ( lastMove.isPlayer1Goal && lastMove.isBottomRow)
            {
                endGoal = true;
            }
            Node middleMove;
            for(int i = 1; i < p1Net-1; i++ )
            {
                middleMove = players1Network[i];
                if (middleMove.isPlayer1Goal)
                {
                    middleGoal = false;
                }
            }
            if (startGoal && endGoal && middleGoal)
            {
                System.out.println("Player1 - Network goals checks.");
                checkGoal = true;
                checkDirection();
            }
            else
            {
                System.out.println("Player1 - Network goals fails.");

            }
        }
        else
        {
            Node firstMove = players2Network[0];
            if ( firstMove.isPlayer2Goal && firstMove.isLeftColumn)
            {
                startGoal = true;
            }
            Node lastMove = players2Network[p2Net-1];
            if ( lastMove.isPlayer2Goal && lastMove.isRightColumn)
            {
                endGoal = true;
            }
            Node middleMove;
            for(int i = 1; i < p2Net-1; i++ )
            {
                middleMove = players2Network[i];
                if (middleMove.isPlayer2Goal)
                {
                    middleGoal = false;
                }
            }
            if (startGoal && endGoal && middleGoal)
            {
                System.out.println("Player2 - Network goals checks.");
                checkGoal = true;
                checkDirection();
            }
            else
            {
                System.out.println("Player2 - Network goals fails.");

            }
        }
    }

    public void checkDirection()
    {
        Node start, stop;
        if (isPlayers1Move  )
        {
            for(int i = 0; i < p1Net; i++ )
            {
                start = players1Network[i];
                stop = players1Network[i+1];
                if (start.directionOut == stop.directionOut || stop.directionOut == start.directionOut)
                {
                    System.out.println("Player1 - Direction check does not pass.");
                    checkDirection = false;
                }
            }
        }
        else
        {
            for(int i = 0; i < p2Net; i++ )
            {
                start = players2Network[i];
                stop = players2Network[i+1];
                if (start.directionOut == stop.directionOut || stop.directionOut == start.directionOut)
                {
                    System.out.println("Player2 - Direction check does not pass.");
                    checkDirection = false;
                }
            }

        }
        if(checkDirection)
        {
            if(isPlayers1Move)
            {
                System.out.println("Player1 - Direction check passes.");
            }
            else
            {
                System.out.println("Player2 - Direction check passes.");

            }
        }
    }

    public void checkForBlocks()
    {
        if (isPlayers1Move)
        {
            if ( p1Net >= 1)
            {
                for (int n = 0; n < p1Net-1; n++)
                {
                    Node start1 = players1Network[n];
                    Node stop1 = players1Network[n + 1];
                    Node myNeighbor = start1;
                    int startX = start1.locationX;
                    int startY = start1.locationY;
                    int stopX = stop1.locationX;
                    int stopY = stop1.locationY;
//                    System.out.println("startX = " + startX);
//                    System.out.println("startY = " + startY);
//                    System.out.println("stopX = " + stopX);
//                    System.out.println("stopY = " + stopY);
                    // north east connection
                    if (startY < stopY && startX > stopX)
                    {
                        System.out.println("north east connection");
                    }

                    // north west connection
                    if (startY > stopY && startX > stopX)
                    {
                        System.out.println("north west connection");
                    }
                    // north connection
                    if (startY == stopY && startX > stopX)
                    {
                        System.out.println("north connection");
                    }
                    //  east connection, x same and start y < curr y
                    if (startY < stopY && startX == stopX)
                    {
                        System.out.println("east connection");
//                        for (int i = startY; startY < stopY; i++)
//                        {
//                            myNeighbor = backEndBoard[startX][i];
//                            if (myNeighbor.isPlayedPlayer2)
//                            {
//                                System.out.println("There is a blocked piece!");
//                            }
//                        }
                    }
                    //  west connection, x same and start y > curr y
                    if (startY > stopY && startX == stopX)
                    {
                        System.out.println("north connection");
                    }
                    //  south west connection
                    if (startY > stopY && startX < stopX)
                    {
                        System.out.println("south west connection");
                    }
                    //  south east connection
                    if (startY < stopY && startX < stopX)
                    {
                        System.out.println("south east connection");
                    }
                    // south connection
                    if (startY == stopY && startX < stopX)
                    {
                        System.out.println("south connection");
                    }
                }
            }
        }
        else
        {
            if ( p2Net >= 1)
            {
                for (int n = 0; n < p2Net-1; n++)
                {
                    Node start1 = players2Network[n];
                    Node stop1 = players2Network[n + 1];
                    Node myNeighbor = start1;
                    int startX = start1.locationX;
                    int startY = start1.locationY;
                    int stopX = stop1.locationX;
                    int stopY = stop1.locationY;

                    // north east connection
                    if (startY < stopY && startX > stopX)
                    {
                        System.out.println("north east connection");
                    }

                    // north west connection
                    if (startY > stopY && startX > stopX)
                    {
                        System.out.println("north west connection");
                    }
                    // north connection
                    if (startY == stopY && startX > stopX)
                    {
                        System.out.println("north connection");
                    }
                    //  east connection, x same and start y < curr y
                    if (startY < stopY && startX == stopX)
                    {
                        System.out.println("east connection");
//                        for (int i = startY + 1; startY < stopY; i++)
//                        {
//                            myNeighbor = backEndBoard[startX][i];
//                            if (myNeighbor.isPlayedPlayer1)
//                            {
//                                System.out.println("There is a blocked piece!");
//                            }
//                        }
                    }
                    //  west connection, x same and start y > curr y
                    if (startY > stopY && startX == stopX)
                    {
                        System.out.println("north connection");
                    }
                    //  south west connection
                    if (startY > stopY && startX < stopX)
                    {
                        System.out.println("south west connection");
                    }
                    //  south east connection
                    if (startY < stopY && startX < stopX)
                    {
                        System.out.println("south east connection");
                    }
                    // south connection
                    if (startY == stopY && startX < stopX)
                    {
                        System.out.println("south connection");
                    }
                }
            }
        }
    }

    public void verifyNetworkChecks()
    {
        if ( isPlayers1Move )
        {
            isPlayers1Move = false;

            if (p1NetworkSizeCheck && checkGoal && checkDirection && checkForBlocks )
            {
                isWinningNetwork = true;
                player1Wins = true;
                printBoard();

                System.out.print("Player 1 wins! "); // A4 B5 B7 G2 D2 H6"
                Node tmp;
                for ( int i =0; i < p1Net; i++)
                {
                    tmp = players1Network[i];
                    System.out.print(tmp.locationX+tmp.locationY+" ");
                }
                System.exit(0);
            }
            else
            {
                p1NetworkSizeCheck = false;
                checkGoal = false;
                checkDirection = false;
                checkForBlocks = false;
                printBoard();
            }
        }
        else
        {
            isPlayers1Move = true;


            if (p1NetworkSizeCheck && checkGoal && checkDirection && checkForBlocks )
            {
                isWinningNetwork = true;
                player2Wins = true;
                printBoard();

                System.out.print("Player 2 wins! ");
                Node tmp;
                for ( int i =0; i < p2Net; i++)
                {
                    tmp = players2Network[i];
                    System.out.print(tmp.locationX+tmp.locationY+" ");
                }
                System.exit(0);
            }
            else
            {
                p1NetworkSizeCheck = false;
                checkGoal = false;
                checkDirection = false;
                checkForBlocks = false;
                printBoard();
            }
        }
    }


    // TODO - BEGIN BOARD METHODS

    public void printBoard()
    {
        System.out.println();
        for (int i = 0; i <= 8; i++)
        {
            if ( i == 0 )
            {
                System.out.print(" " + " ");
            }
            else
            {
                System.out.print(i + " ");
            }
        }

        System.out.println();

        for (int i = 0; i < rowCharacters.length; i++)
        {
            System.out.print(rowCharacters[i]+":");
            for ( int j = 0; j < colNum.length; j++)
            {
                //System.out.print("." + " ");
                System.out.print( frontEndBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
