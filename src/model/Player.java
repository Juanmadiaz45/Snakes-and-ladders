package model;

public class Player {
    private String symbol;
    private Square currentSquare;

    public Player(String symbol, Square startingSquare) {
        this.symbol = symbol;
        this.currentSquare = startingSquare;
    }

    public String getSymbol() {
        return symbol;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square square) {
        this.currentSquare = square;
    }
}
