import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;





public class Main {

    static LinkedList<gameSetup> gameMap = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        int playercount = 0;



        // 1.File Input
        File file = new File("C:\\Users\\emili\\IdeaProjects\\Snakes and Ladders\\mapxxx.json");
        Scanner sc = new Scanner(new FileInputStream(file));

        // 1.1. How big is the field?
        String mapLengthTemp;
        mapLengthTemp = sc.nextLine();
        int mapLength = Integer.parseInt(mapLengthTemp);

        // 1.2 Where are the Snakes?
        String snakeStarttemp, snakeEndTemp;
        int snakeArrayCount=-1,ladderArrayCount = -1;
        int[] snakeStart = new int[100];
        int[] snakeEnd = new int[100];
        boolean stop = false;
        while(!stop)
        {
            snakeArrayCount++;
            snakeStarttemp = sc.nextLine();
            if("ladder".equals(snakeStarttemp))
            {
                stop = true;
            }
            else
            snakeStart[snakeArrayCount] = Integer.parseInt(snakeStarttemp);

            if(!stop) {
                snakeEndTemp = sc.nextLine();
                if (snakeEndTemp == "ladder")
                    break;
                snakeEnd[snakeArrayCount] = Integer.parseInt(snakeEndTemp);
                //System.out.println(snakeStart[4]);
            }
        }
        stop = false;

        //1.3 Where are the ladders?

        String ladderStartTemp, ladderEndTemp;
        int[] ladderStart = new int[100];
        int[] ladderEnd = new int[100];
        while(sc.hasNextLine())
        {
            ladderArrayCount++;
            ladderStartTemp = sc.nextLine();
            ladderStart[ladderArrayCount] = Integer.parseInt(ladderStartTemp);

            ladderEndTemp = sc.nextLine();
            ladderEnd[ladderArrayCount] = Integer.parseInt(ladderEndTemp);

        }

        for(int i = 0; i< ladderArrayCount; i++)
            System.out.println(ladderStart);
        //Game map Setup
        gameSetup mapa = new gameSetup();
        gameMapSetup(gameMap, mapLength,ladderArrayCount, ladderStart, ladderEnd, snakeArrayCount, snakeStart, snakeEnd);

        // GAME STARTS OMG FINALLY JAU NEŽINAU KOKIĄ VALADNĄ RAŠAU TAI PRAŠAU PADĖKIT MANO SĄMANOĖJ, EINU DARYT JAU 5 KAVĄ, AR GALITE INDUS MAN IŠPLAUT ?????
        mainMenu(playercount,mapLength);





    }


    private static void gameMapSetup(LinkedList gameMap, int mapLength, int lac, int[] LS, int[] LE, int sac, int[] SS, int[] SE){
        boolean ladder = false, snake = false;
        for(Integer i =0; i<mapLength; i++) {

            for(int j = 0; j<lac; j++)
            {

                if(i == LS[j]) {
                    ladder = true;
                    gameMap.add(new gameSetup(i,-1, LS[j]));
                }
            }
            if(!ladder)
            {
                for(int j = 0; j<sac;j++)
                {
                    //System.out.println(SS[0]);
                    if( i.equals(SS[j]))
                    {
                        snake = true;
                        gameMap.add(new gameSetup(i, SE[j],-1));
                    }
                }
                if(!snake && !ladder)
                //System.out.println(i);
                gameMap.add(new gameSetup(i, -1, -1 ));
            }

        ladder = false;
            snake = false;
    }

}

public static void printGame(int mapLength)
{
    int lineCount = 1;
    //System.out.println(gameMap);
    for(int i = 0; i< mapLength; i++) {

        if(lineCount !=10) {
            System.out.print(gameMap.get(i).getPosition() + "\t");
            lineCount+= 1;
        }
        else if(lineCount == 10) {
            System.out.println(gameMap.get(i).getPosition());
            lineCount = 1;
        }

    }
}

public static void mainMenu(int playerCount,int mapLength)
{

    Scanner menuscan = new Scanner(System.in);
    System.out.println("Hi!");
    System.out.println("This is the Snakes and Ladders game!");
    boolean hasNextInt = false;
    while(!hasNextInt) {
        System.out.println("Will you want to play today? ^^ (click 1 )");
        hasNextInt = menuscan.hasNextInt();
        Integer play = menuscan.nextInt();

        if(!play.equals(1))
        {
            System.out.println("sad :(");
            System.exit(0);
        }
    }

    System.out.println("Please input the player count (1 - 3 )");
    playerCount = menuscan.nextInt();
    menuscan.nextLine();

    boolean Check= false;
    System.out.println("Do you want to change any game settings? ");
    Check = menuscan.nextBoolean();
        if(Check)
            gameSettings(mapLength);


    System.out.println("Awesome, Are you ready? (Press enter)");
    menuscan.nextLine();
    menuscan.close();

    //2.1 Print game
    printGame(mapLength);
    //3. Main game, players play
    playersTraverse(playerCount,mapLength);
}

public static void playersTraverse(int playerCount, int mapLength)
{


    int diceRollAmount;
    int[] player = new int[playerCount];
// --------------------------


        for(int i = 0; i<playerCount; i++)
            player[i] = 0;

    System.out.println("Player Positions: ");

        for(int i = 0; i<playerCount;i++)
            System.out.println((i+1) + " Player is at position " + (player[1]+1));
    Scanner sc = new Scanner(System.in);

    Integer stopGame=0;
    while (player[0] != (mapLength-1) || player[1] != (mapLength-1) || player[2] != (mapLength-1))
    {
        for(int i = 0;i< playerCount; i++)
        {
            System.out.println(i + " player it's your turn!   (if you want to stop press 1)");

            boolean hasNextInt = false;
            while(!hasNextInt){
                hasNextInt = sc.hasNextInt();
                stopGame = sc.nextInt();

                if(stopGame.equals(1))
                {
                    System.exit(0);
                }

                sc.close();
                diceRollAmount= rollDice();
                System.out.println("It's " + diceRollAmount);
                player[i] += diceRollAmount;
                player[i] = gameMap.get(player[i]).checkElements(player[i]);
                System.out.println("Player " + (i+1) + " is at the " + (player[i]+1) + " pos.");

                if(player[i] >= mapLength-1)
                    winGame(player[i]);
            }

        }
        System.out.println();
    }
}

public static int rollDice()
{
    int n = 0;
    Random r = new Random();
    n = r.nextInt(7);
    return (n == 0 ? 1 : n);
}
public static void winGame(int Winner)
{

}


public static int gameSettings(int mapLength)
{
    int setup = 0;
    while (setup != 6)
    {
        setup = 0;
        Scanner gameSettingsScanner = new Scanner(System.in);
        System.out.println("What Do you want to change?");
        System.out.println(" 1 - Add Snake");
        System.out.println(" 2 - Add Ladder");
        System.out.println(" 3 - Add Position");
        System.out.println(" 4 - Remove Snake");
        System.out.println(" 5 - Remove Ladder");
        System.out.println(" 6 - Leave Settings and start game");
        System.out.println("\t Map:");
        printGame(mapLength);
        System.out.println();

        setup = gameSettingsScanner.nextInt();

        if (setup == 1) {
            System.out.println(" What Position? From 0 to " + (mapLength - 1));
            setup = gameSettingsScanner.nextInt();
            int snakee = -1;
            System.out.println("Where should it go? ");
            snakee = gameSettingsScanner.nextInt();
            if (snakee >= setup) {
                System.out.println("Failed snake end position is higher than start");
            } else
                Main.gameMap.get(setup).setSnake(snakee);
            snakee = 0;
        }

        if(setup == 2){
            System.out.println(" What Position? From 0 to " + (mapLength - 1));
            setup = gameSettingsScanner.nextInt();
            int ladderr = -1;
            System.out.println("Where should it go? ");
            ladderr = gameSettingsScanner.nextInt();
            if(ladderr <= setup )
            {
                System.out.println("Failed, ladder end position is lower than start");
            }
            else
                Main.gameMap.get(setup).setLadder(ladderr);
            ladderr = 0;
        }

        if(setup == 3){
            gameMap.add(new gameSetup(mapLength,-1,-1));
            mapLength+=1;
        }

        if(setup == 4){

        }
    }
    return mapLength;
}
}