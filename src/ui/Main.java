package ui;

import model.*;
import java.util.Scanner;

public class Main {
    private Game controller;
	private Scanner reader;

	public Main() {
		controller = new Game();
		reader = new Scanner(System.in);

	}

	public static void main(String[] args) {

		// creación del objeto. 
		Main main = new Main(); 
		// llamdo a uno de los metodos de la clase. 
		int option = 0; 

		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

		main.getReader().close();

	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Bienvenidos a escaleras y serpientes >>>>>");
		System.out.println(
				"1. Jugar.\n" +
				"0. Salir. \n");
		option =  validateIntegerInput();
		return option; 
	}

	public void executeOption(int option){

        int rows = 0;
		int option2 = 0;
        int columns = 0;
        int numSnakes = 0;
        int numLadders = 0;
		String msj = "";
		Board board;

		switch(option){
			case 1: 

                System.out.println("\nIngrese el numero de filas del tablero: ");
                rows = reader.nextInt();

                System.out.println("\nIngrese el numero de columnas del tablero: ");
                columns = reader.nextInt();

                System.out.println("\nIngrese el numero de serpientes: ");
                numSnakes = reader.nextInt();

                System.out.println("\nIngrese el numero de escaleras: ");
                numLadders = reader.nextInt();

                board = controller.createBoard(rows, columns, numSnakes, numLadders);
				System.out.println("\n");

				controller.printBoard(board);

				System.out.println("\n");

				controller.playGame(option2);
				option2 = reader.nextInt();

				System.out.println("\n");

				controller.printScoreBoard();

				System.out.println("\n");

				break;

			case 2: 
				break; 

			case 3: 
				break; 

			case 4:
                break;
			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}

    public Scanner getReader(){
		return reader; 
	}

	public int validateIntegerInput(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public double validateDoubleInput(){
		double option = 0; 

		if(reader.hasNextDouble()){
			option = reader.nextDouble(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}
}
