package hr.mpopijac.data;

public class CursorData {

    private char[][] chars;

    private Integer x;

    private Integer y;

    private char letter;

    private MoveDirection direction;

    public CursorData() {
    }

    public char[][] getChars() {
        return chars;
    }

    public void setChars(char[][] chars) {
        this.chars = chars;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public MoveDirection getDirection() {
        return direction;
    }

    public void setDirection(MoveDirection direction) {
        this.direction = direction;
    }
}
