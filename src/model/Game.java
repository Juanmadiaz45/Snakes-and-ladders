package model;
import java.util.Date;

public class Game {
    private int score;
    private int elapsedTime;
    private Date startTIme;
    private Board board;
    private Scoreboard scoreboard;
    
    public Game() {
        startTIme = new Date(System.currentTimeMillis());
        this.score = score;
    }

    public void calculateElapsedTime(){

        Date now = new Date(System.currentTimeMillis());

        this.elapsedTime = (int) (now.getTime() - startTIme.getTime())/1000;

    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void calculateScore(){
        calculateElapsedTime();
        this.score =  (600 - elapsedTime ) / 6;
    }

    public Board createBoard(int rows, int columns, int numSnakes, int numLadders) {
        return board = new Board(rows, columns, numSnakes, numLadders);
    }

    public void printBoard(Board board){
        board.printBoard();
    }

    public void playGame(int option2){
        board.playGame(option2);
    }

    public void printScoreBoard(){
        scoreboard.printScores();
    }
    
}
