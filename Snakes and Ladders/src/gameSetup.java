public class gameSetup {
    private int position;
    private int snake;
    private int ladder;

    public static void getPos(int position) {
        System.out.println(position);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public gameSetup(int position, int snake, int ladder) {
        this.position = position;
        this.snake = snake;
        this.ladder = ladder;
    }

    public gameSetup() {
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
}
