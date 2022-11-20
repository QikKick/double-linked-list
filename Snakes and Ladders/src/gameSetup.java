public class gameSetup {
    private int position;
    private Integer snake;
    private Integer ladder;


    public gameSetup(int position, int snake, int ladder) {
        this.position = position;
        this.snake = snake;
        this.ladder = ladder;
    }

    public String getPosition() {
        return printPos(position);
    }

    public String printPos(int pos)
    {
        String posi = Integer.toString(pos);
        if(!snake.equals(-1))
            return posi + "S";

        if(!ladder.equals(-1))
            return posi + "L";

            return posi;
    }


    public void setPosition(int position) {
        this.position = position;
    }
    public gameSetup() {
        this(0, 0, 0);
    }

    public int getSnake() {
        return snake;
    }

    public void setSnake(int snake) {
        this.snake = snake;
    }

    public int getLadder() {
        return ladder;
    }

    public void setLadder(int ladder) {
        this.ladder = ladder;
    }

    public static void getPos(int position) {
        System.out.println(position);
    }
}
