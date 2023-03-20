package model;

public class Square {
    private int squareId;
    private boolean isSnake;
    private boolean isLadder;
    private Square moveSquare;

    public Square(int squareId) {
        this.squareId = squareId;
    }
    
    public int getSquareId() {
        return squareId;
    }
    
    public boolean isSnake() {
        return isSnake;
    }
    
    public void setIsSnake(boolean isSnake) {
        this.isSnake = isSnake;
    }
    
    public boolean isLadder() {
        return isLadder;
    }
    
    public void setIsLadder(boolean isLadder) {
        this.isLadder = isLadder;
    }
    
    public Square getMoveSquare() {
        return moveSquare;
    }
    
    public void setMoveSquare(Square moveSquare) {
        this.moveSquare = moveSquare;
    }
}