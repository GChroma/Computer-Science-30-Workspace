package code;
import java.util.Scanner;

public class LinkedListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList LL = new LinkedList();
		Scanner input = new Scanner(System.in);
		
		for(int i = 0; i < 3; i++) {
			LL.push(i);
		}
		
		int num1 = 0;
		while(num1 != 420) {
			LL.display();
			System.out.println("Input nums to be swapped.");
			num1 = input.nextInt();
			int num2 = input.nextInt();
			
			LL.swap(LL.find(num1), LL.find(num2));
		}
		
		input.close();
		
		//System.out.println(LL.find(3).getData());
	}

}
