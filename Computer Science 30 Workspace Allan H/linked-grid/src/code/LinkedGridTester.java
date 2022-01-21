package code;
import java.util.Scanner;

public class LinkedGridTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedGrid LG = new LinkedGrid(10);
		
    
		Scanner input = new Scanner(System.in);
		
		int data = 0;
		int counter = 25;
		System.out.println("Flood:");
		LG.displayColour();
		
		while(data != -1 && counter > 0 && !LG.isFlooded()) {
			System.out.println();
			System.out.println("\u001b[1;41m  \u001b[0m 0");
			System.out.println("\u001b[1;42m  \u001b[0m 1");
			System.out.println("\u001b[1;43m  \u001b[0m 2");
			System.out.println("\u001b[1;44m  \u001b[0m 3");
			System.out.println("\u001b[1;45m  \u001b[0m 4");
			System.out.println("\u001b[1;46m  \u001b[0m 5");
			System.out.println("You have " + counter + " moves.");
			data = input.nextInt();
			counter--;
			LG.flood(data);
			
			LG.displayColour();
			
			
			
		}
		
		if(LG.isFlooded()) {
			System.out.println("You Win");
		}else {
			System.out.println("You Lose");
		}
		
	}

}
