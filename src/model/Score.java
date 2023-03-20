package model;

public class Score {
    private String symbol;
    private int score;
    private Score left;
    private Score right;

    public Score(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score getLeft() {
        return left;
    }

    public void setLeft(Score left) {
        this.left = left;
    }

    public Score getRight() {
        return right;
    }

    public void setRight(Score right) {
        this.right = right;
    }
}
