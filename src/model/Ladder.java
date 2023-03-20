package model;

public class Ladder {
    private Square startSquare;
    private Square endSquare;

    public Ladder(Square startSquare, Square endSquare) {
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
