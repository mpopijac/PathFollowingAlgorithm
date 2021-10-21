package hr.mpopijac.data;

public enum MoveDirection {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private int x;

    private int y;

    MoveDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MoveDirection getOppositeDirection() {
        if (this.equals(UP)) {
            return DOWN;
        } else if (this.equals(DOWN)) {
            return UP;
        } else if (this.equals(RIGHT)) {
            return LEFT;
        } else if (this.equals(LEFT)) {
            return RIGHT;
        }
        return null;
    }

}
