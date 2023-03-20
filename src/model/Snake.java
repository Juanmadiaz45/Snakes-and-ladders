package model;

public class Snake {
    private Square startSquare;
    private Square endSquare;

    public Snake(Square startSquare, Square endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        startSquare.setMoveSquare(endSquare);
    }
    
    public Square getStartSquare() {
        return startSquare;
    }
    
    public Square getEndSquare() {
        return endSquare;
    }
}
