//Colin Beary
//CIS160380
//04/27/2022
//Final project game
import java.util.Scanner;

class JavaFinal {

	//Defining object variables
	private String playername;
	private int playertotal = 0;
	private int win = 0;
	
	static Scanner input = new Scanner(System.in);
	
	//methods for setting player name and grabbing the name and total, as well as if they have won
	public JavaFinal(String playername) {
		this.playername = playername;
	}
	public String getplayername() {
		return playername;
	}
	public int gettotal() {
		return playertotal;
	}
	public int haswon() {
		return win;
	}
	
	//method for changing the players total and also determines when they win or resets them if they go over
	public void addtotal(int n){
		playertotal += n;
		if (playertotal >= 31) {
			System.out.println("You went over! Your total has been reset.");	
			playertotal = 0;
		} else if (playertotal == 30) {
			win = 1;
		} else {
			System.out.println("Your total is now " + playertotal + ".");
			}
		}
	
	//method for rolling 6 sided dice
	public static int d6(){
		      int upperbound = 6;
		      int lowerbound = 1;
		      int diceroll = lowerbound + (int)(Math.random() * (upperbound - lowerbound) + 1);
		      return diceroll;
		}
	
	//general method to print out player information at the start of every turn
	public void printInfo() {
        System.out.println("Player Name: " + getplayername());
        System.out.println("Current total: " + gettotal());
        System.out.println();
    }
	
	
}

public class Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//establishing players as objects and storing them in an array
		JavaFinal array[] = new JavaFinal[2];
		
		System.out.println("What is the first players's name?");
		String username = input.nextLine();
		array[0] = new JavaFinal(username);
		System.out.println("What is the second players's name?");
		String nextusername = input.nextLine();
		array[1] = new JavaFinal(nextusername);
		
		
		//welcome message and game instructions
		System.out.println("Welcome " + array[0].getplayername() + " and " + array[1].getplayername() + " to the game!");
		System.out.println("The goal of the game is to get to 30 while rolling 2 6-sided dice without going over before the other player.");
		System.out.println("Every time you roll both dice, you will be able to choose which dice, or both, to add to your total.");
		System.out.println("Good luck " + array[0].getplayername() + " and " + array[1].getplayername() + "!");
		
		
		//establishing a win condition variable to ensure the loop ends if a player wins inside the for loop
		int someonehaswon = 0;
		
		do {
			for(int i = 0; i<array.length; i++) {
				array[i].printInfo();
				int firstroll = JavaFinal.d6();
				int secondroll = JavaFinal.d6();
				System.out.println("Your first roll (1) was " + firstroll);
				System.out.println("Your second roll (2) was " + secondroll);
				System.out.println("Your total roll (T) is " + (firstroll+secondroll));
				System.out.println("Which roll would you like to take: 1, 2, or T?");
				String userwants = input.nextLine();
				
				//making sure the user inputs a required value so it doesn't skip a turn and give them a 0 on their turn	
				while (true) {
					if (userwants.equals("1") ) {
						array[i].addtotal(firstroll);
						break;
					} 
					else if (userwants.equals("2")) {
						array[i].addtotal(secondroll);
						break;
					} 
					else if (userwants.equals("T")) {
						array[i].addtotal(firstroll+secondroll);
						break;
					}
					else {
						System.out.println("Please select a viable option");
						userwants = input.nextLine();
					}
				}
				int didtheywin = array[i].haswon();
				
				//Letting the player know they won, and leaving the while loop
				if (didtheywin == 1) {
					someonehaswon = 1;
					System.out.println(array[i].getplayername() + " Won!");
					break;
				}
					System.out.println();
				}
		}
		while (someonehaswon == 0);
		
		//finally closing scanner
		input.close();
		}
}