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
        while(stop == false)
        {
            snakeArrayCount++;
            snakeStarttemp = sc.nextLine();
            if("ladder".equals(snakeStarttemp))
            {
                stop = true;
            }
            else
            snakeStart[snakeArrayCount] = Integer.parseInt(snakeStarttemp);

            if(stop == false) {
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
        mainMenu(playercount,mapLength);


        // GAME STARTS OMG FINALLY JAU NEŽINAU KOKIĄ VALADNĄ RAŠAU TAI PRAŠAU PADĖKIT MANO SĄMANOĖJ, EINU DARYT JAU 5 KAVĄ, AR GALITE INDUS MAN IŠPLAUT ?????


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
            if(ladder == false)
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
                if(snake == false && ladder == false)
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

    System.out.println("Awesome, Are you ready? (Press enter)");
    menuscan.nextLine();
    menuscan.close();

    printGame(mapLength);
    playersTraverse(playerCount);
}

public static void playersTraverse(int playerCount)
{
    //System.out.println(playerCount);
    int[] player = new int[playerCount];

        for(int i = 0; i<playerCount; i++)
            player[i] = 0;

    System.out.println("Player Positions: ");

        for(int i = 0; i<playerCount;i++)
            System.out.println(i + "Player is at position " + (player[1]+1));

    Scanner sc = new Scanner(System.in);

}

public static int rollDice()
{
    int n = 0;
    Random r = new Random();
    n = r.nextInt(7);
    return (n == 0 ? 1 : n);
}


}