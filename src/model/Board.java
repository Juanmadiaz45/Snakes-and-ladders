package model;

public class Board {
    private int numRows;
    private int numColumns;
    private Square firstSquare;
    private Square lastSquare;
    private int numSnakes;
    private int numLadders;
    private Player player1;
    private Player player2;
    private Player player3;

    public Board(int numRows, int numColumns, int numSnakes, int numLadders) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numSnakes = numSnakes;
        this.numLadders = numLadders;

        createBoard(1);
        addSnakesAndLadders(numSnakes, numLadders);
        player1 = new Player("%", firstSquare);
        player2 = new Player("*", firstSquare);
        player3 = new Player("#", firstSquare);
    }

    private Square createBoard(int squareId) {
        if (squareId > numRows * numColumns) {
            return null;
        }

        Square square = new Square(squareId);

        if (firstSquare == null) {
            firstSquare = square;
        }

        if (squareId == numRows * numColumns) {
            lastSquare = square;
        }

        square.setMoveSquare(createBoard(squareId + 1));
        return square;
    }

    private void addSnakesAndLadders(int numSnakes, int numLadders) {
        if (numSnakes > 0) {
            addSnake();
            addSnakesAndLadders(numSnakes - 1, numLadders);
        }

        if (numLadders > 0) {
            addLadder();
            addSnakesAndLadders(numSnakes, numLadders - 1);
        }
    }

    private void addSnake() {
        int start = (int) (Math.random() * (numRows * numColumns - 1)) + 2;
        int end = (int) (Math.random() * (start - 1)) + 1;

        if (start == numRows * numColumns || end == 1) {
            addSnake();
            return;
        }

        if (isSnakeOrLadderSquare(start) || isSnakeOrLadderSquare(end)) {
            addSnake();
            return;
        }

        Square startSquare = getSquare(start);
        Square endSquare = getSquare(end);

        startSquare.setIsSnake(true);
        startSquare.setMoveSquare(endSquare);
    }

    private void addLadder() {
        int start = (int) (Math.random() * (numRows * numColumns - 2)) + 2;
        int end = (int) (Math.random() * (numRows * numColumns - start)) + start;

        if (start == 1 || end == numRows * numColumns) {
            addLadder();
            return;
        }

        if (isSnakeOrLadderSquare(start) || isSnakeOrLadderSquare(end)) {
            addLadder();
            return;
        }

        Square startSquare = getSquare(start);
        Square endSquare = getSquare(end);

        startSquare.setIsLadder(true);
        startSquare.setMoveSquare(endSquare);
    }

    private boolean isSnakeOrLadderSquare(int squareId) {
        Square square = getSquare(squareId);

        if (square.isSnake() || square.isLadder()) {
            return true;
        }

        if (square.getMoveSquare() == null) {
            return false;
        }

        return isSnakeOrLadderSquare(square.getMoveSquare().getSquareId());
    }

    private Square getSquare(int squareId) {
        return getSquare(firstSquare, squareId);
    }

    private Square getSquare(Square square, int squareId) {
    if (square == null) {
        return null;
    } else if (square.getSquareId() == squareId) {
        return square;
    } else {
        return getSquare(square.getMoveSquare(), squareId);
    }
    }

    public void printBoard() {
        printBoardRecursive(1, 1);
    }
    
    private void printBoardRecursive(int row, int column) {
        if (row > this.numRows) {
            return;
        }
        if (column > this.numColumns) {
            System.out.println();
            printBoardRecursive(row + 1, 1);
            return;
        }
        int number = getNumber(row, column);
        System.out.print("[" + number + "] ");
        printBoardRecursive(row, column + 1);
    }
    
    private int getNumber(int row, int column) {
        if (row % 2 == 0) {
            return ((this.numRows - row) * this.numColumns) + (this.numColumns - column + 1);
        } else {
            return ((this.numRows - row) * this.numColumns) + column;
        }
    }

}