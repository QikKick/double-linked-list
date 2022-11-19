import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {




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

        //Game map Setup
        LinkedList<Class> gameMap = new LinkedList<>();

        gameMapSetup(gameMap, mapLength,ladderArrayCount, ladderStart, ladderEnd, snakeArrayCount, snakeStart, snakeEnd);

        System.out.println();

    }


    private static void gameMapSetup(LinkedList gameMap, int mapLength, int lac, int[] LS, int[] LE, int sac, int[] SS, int[] SE){
        boolean ladder = false;
        for(int i =0; i<mapLength; i++) {
            for(int j = 0; j<lac; j++)
            {
                if(i == LS[j]) {
                    ladder = true;
                    for(int x=0; x<sac+1; x++)
                    {
                        if(i == SS[x])
                        {

                            gameMap.add(new gameSetup(i, LE[j],SE[x]));
                        }
                        else if (i == sac)
                        {
                            gameMap.add(new gameSetup(i, LE[j], -1));
                        }
                    }
                }
            }
            if(ladder == false)
            {
                for(int j = 0; j<sac;j++)
                {
                    if( i == SS[j])
                    {
                        gameMap.add(new gameSetup(i,-1,SE[j]));
                    }
                }
            }


    }

}}