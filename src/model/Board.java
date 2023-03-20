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

    public void movePlayer(Player player, int numSquares) {
        Square currentSquare = player.getCurrentSquare();
        movePlayerRecursive(player, currentSquare, numSquares);
    }
    
    private void movePlayerRecursive(Player player, Square currentSquare, int numSquares) {
        if (numSquares == 0 || currentSquare == null) {
            return;
        }
        Square nextSquare = currentSquare.getMoveSquare();
        player.setCurrentSquare(nextSquare);
        movePlayerRecursive(player, nextSquare, numSquares - 1);
    }

    public void playGame(int option2) {
        Player currentPlayer = player1;
    
        while (true) {
            System.out.println("Jugador " + currentPlayer.getSymbol() + ", es tu turno");
            System.out.println("1. Tirar dado");
            System.out.println("2. Ver escaleras y serpientes");
    
            switch (option2) {
                case 1:
                    int roll = (int) (Math.random() * 6) + 1;
                    System.out.println("Has sacado un " + roll);
    
                    movePlayer(currentPlayer, roll);
    
                    Square currentSquare = currentPlayer.getCurrentSquare();
    
                    if (currentSquare.isSnake()) {
                        System.out.println("Te has caído en una serpiente!");
                        movePlayer(currentPlayer, -2);
                    } else if (currentSquare.isLadder()) {
                        System.out.println("Has subido por una escalera!");
                        movePlayer(currentPlayer, 2);
                    }
    
                    if (currentSquare == lastSquare) {
                        System.out.println("¡Felicidades, jugador " + currentPlayer.getSymbol() + ", has ganado!");
                        return;
                    }
    
                    break;
                case 2:
                    printBoard();
                    break;
            }
    
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else if (currentPlayer == player2) {
                currentPlayer = player3;
            } else {
                currentPlayer = player1;
            }
        }
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
        printBoardRecursive(numRows, 1);
    }
    
    private void printBoardRecursive(int row, int column) {
        if (row < 1) {
            return;
        }
        if (column > numColumns) {
            System.out.println();
            printBoardRecursive(row - 1, 1);
            return;
        }
        Square currentSquare = getSquare((row - 1) * numColumns + column);
        if (player1.getCurrentSquare() == currentSquare) {
            System.out.print("[1$" + player1.getSymbol() + "%] ");
        } else if (player2.getCurrentSquare() == currentSquare) {
            System.out.print("[2*" + player2.getSymbol() + "] ");
        } else if (player3.getCurrentSquare() == currentSquare) {
            System.out.print("[3" + player3.getSymbol() + "] ");
        } else {
            System.out.print("[" + currentSquare.getSquareId() + "] ");
        }
        printBoardRecursive(row, column + 1);
    }
    
    private int getNumber(int row, int column) {
        if (row % 2 == 0) {
            return ((this.numRows - row) * this.numColumns) + (this.numColumns - column + 1);
        } else {
            return ((this.numRows - row) * this.numColumns) + column;
        }
    }

    public void printSnakesAndLadders() {
        printSnakesAndLaddersRecursive(1, 1);
    }
    
    private void printSnakesAndLaddersRecursive(int row, int column) {
        if (row > this.numRows) {
            return;
        }
        if (column > this.numColumns) {
            System.out.println();
            printSnakesAndLaddersRecursive(row + 1, 1);
            return;
        }
        Square square = getSquare(getNumber(row, column));
        if (square.isSnake() || square.isLadder()) {
            String symbol = square.isSnake() ? "S" : "L";
            int number = square.isSnake() ? getSnakeNumber(square) : getLadderNumber(square);
            System.out.print("[" + symbol + number + "] ");
        } else {
            System.out.print("[ ] ");
        }
        printSnakesAndLaddersRecursive(row, column + 1);
    }
    
    private int getSnakeNumber(Square square) {
        if (square.isSnake() && square.getMoveSquare() != null) {
            int nextSquareId = square.getMoveSquare().getSquareId();
            int snakeNumber = 1 + getSnakeNumber(getSquare(nextSquareId));
            return snakeNumber;
        } else {
            return 0;
        }
    }
    
    private int getLadderNumber(Square square) {
        if (square.isLadder() && square.getMoveSquare() != null) {
            int nextSquareId = square.getMoveSquare().getSquareId();
            int ladderNumber = 1 + getLadderNumber(getSquare(nextSquareId));
            return ladderNumber;
        } else {
            return 0;
        }
    }

}