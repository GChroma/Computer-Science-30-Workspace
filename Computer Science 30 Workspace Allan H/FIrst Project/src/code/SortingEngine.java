package code;

import java.util.Scanner;


public class SortingEngine {
	
	static int[] popSequential(int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			
			array[i] = i + 1;
			
		}
		
		return array;
		
	}
	
	static int[] popRandom(int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			int num = (int) ((Math.random() * (101 - 0)) + 0);
			array[i] = num;
		}
		
		return array;	
	}
	
	static boolean checkSorted(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] > array[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			if((i + 1 )%10 == 0) {
				System.out.println(array[i]);
			}else if(array[i] < 10){
			System.out.print(array[i] + "   ");
			}else if(array[i] == 100) {
				System.out.print(array[i] + " ");
			}else {
				System.out.print(array[i] + "  ");
			}
			
		}
	}
	
	static void shuffle(int[] array) {
		
		for (int i = 0; i < array.length; i++) {
			int num = (int) ((Math.random() * (100 - -1)) + -1);
			int temp = array[num];
			array[num] = array[i];
			array[i] = temp;	
		}
	}
	
	static int linearSearch(int[] array, int num) {
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i] == num) {
				return i;
			}
			
		}
		
		return -1;
		
	}
	
	static int binarySearch(int[] array, int num, int top, int bottom) {//this can probably be done recursively as well.
		
		if(checkSorted(array) == false) {
			System.out.println("Not Sorted.");
		}else {
			do {
				
				int midpoint = (int)((top + bottom)/2);
				
				if(num < array[midpoint]) {
					top = midpoint - 1;
				}else if(num > array[midpoint]) {
					bottom = midpoint + 1;
				}
				
				if(array[midpoint] == num) {
					return midpoint;
				}
				
				if((top - bottom) < 1) {
					if(array[top] == num) {
						return top;
					}else if(array[bottom] == num) {
						return bottom;
					}else {
						return -1;
					}
					
					
				}
			}while(true);
			
			
		}
			return -1;
		
	}
	
	static int[] bubbleSort(int[] array) {
		int counter = 0;
		do {
			for(int i = 0; i < array.length - 1 - counter; i++) {
				if(array[i] >= array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;			
				}
			}
			counter++;
			//print(array);
		}while(!checkSorted(array));
		
		return array;
		
	}
	
	static int[] selectionSort(int[] array) {

		int sorted = 0;
		
		do {
			int smallestValue = 101;
			int smallestIndex = -1;//index of the smallest num in unsorted array
			for(int i = sorted; i < array.length; i++) {
				if(array[i] < smallestValue) {
					smallestValue = array[i];
					smallestIndex = i;
				}
			}
			array[smallestIndex] = array[sorted];
			array[sorted] = smallestValue;
			
			sorted++;
			
		}while(sorted < array.length);
		
		return array;
	
	}

	static int[] insertionSort(int[] array) {
		
		int[] copy = new int[array.length];
		copy[0] = array[0];//initialize copy[0]
		//System.out.println(copy[0]);(debug)
		
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > -1; j--) {//look at second array and travel down from one spot behind. Put it above the checked.
				
				if(j == 0) {
					if(array[i] <= copy[j]) {//at the first spot in copy, smallest number must be checked.
						swapUp(copy, j, i);
						copy[j] = array[i];
						//System.out.println("smallest: " + copy[j]);(debugging)
						break;
					}
				}
				
				if(array[i] >= copy[j - 1]) {//if >= it makes it stable?
					swapUp(copy, j, i);//top not included
					copy[j] = array[i];
					break;
				}
				
				
				
			}
		}
		return copy;
	}
	
	static void swapUp(int[] array, int bottom, int top) {//bottom is included in the swap up. Top is replaced
		
		for(int i = top; i > bottom; i--) {
			array[i] = array[i - 1]; 
		}		
	}
	
	static int[] radixSort(int[] array) {
		int[] copy = new int[array.length];
		int largest = -1;
		for(int i = 0; i < array.length; i++) {//find largest value
			if(array[i] > largest) {
				largest = array[i];
			}
		}
		int mostDigits = 0;
		while(largest != 0) {
			largest /= 10;
			mostDigits += 1;
		}
		
		for(int i = 0; i < mostDigits; i++) {//digit - 0, 1, 2 (ones, tens, hundreds) Never more.
			int counter = 0;
			for(int k = 0; k < 10; k++) {//loop through digit check - potential digit
				 for(int j = 0; j < array.length; j++){//loop through array
					if(digitAt(array[j], i) == k) {//digit i of current index j is equal to k.
						copy[counter] = array[j];
						counter++;
					}
				}
			}
			
			array = replaceValues(array, copy);	

		}
		
		return array;
	}
	static int digitAt(int num, int digit) {// 0 is first digit (index value)
		
		int value = num/((int)(Math.pow(10, digit)));
				
		value = value % 10;
		
		return value;
	}
	
	static int[] replaceValues(int[] array, int[] array2) {//replace from array2 to array
		
		for(int i = 0; i < array2.length; i++) {
			array[i] = array2[i];
		}
		return array;
		
	}
	
	static int[] mergeSort(int[] array, int bottom, int top) {
		
		int midpoint = (top+bottom)/2;
		int[] left = new int[0];
		int[] right = new int[0];
		int[] current = new int[(top-bottom) + 1];
		
		if(top-bottom >= 1) {//the array that was split is still greater than or equal to 2 indices
			//left = new int[midpoint - bottom];
			//right = new int[top - (midpoint + 1)];
			left = mergeSort(array, bottom, midpoint);//bottom and top are included
			
			right = mergeSort(array, midpoint + 1, top);
		}
		
		int sortCounter = 0;
		int leftHalf = 0;
		int rightHalf = 0;
		if(right.length > 0 && left.length > 0) {
			/*System.out.println("Left: ");
			print(left);
			System.out.println(" ");
			System.out.println("Right: ");
			print(right);
			System.out.println(" "); */
			//For Debugging
			while(sortCounter < current.length) {//combine both halves
				/*if(right[rightHalf] == 1000 && left[leftHalf] == 1000) {
					System.out.println("too big ");
				}*/
			
			if(left[leftHalf] <= right[rightHalf]) {
				//System.out.println("Combining " + left[leftHalf]);
				current[sortCounter] = left[leftHalf];
				left[leftHalf] = 1000;
				if(leftHalf < (left.length - 1)) {
					leftHalf++;
				}
				
				
				sortCounter++;
			}else {
				//System.out.println("Combining " + right[rightHalf]);
				current[sortCounter] = right[rightHalf];
				right[rightHalf] = 1000;
				if(rightHalf < (right.length - 1)) {
					rightHalf++;
				}
				sortCounter++;
			}
		}
		
			
		}else {
			//System.out.println("Reached bottom " + array[bottom]);// For debugging
			current[sortCounter] = array[bottom];
		}
		
		
		/*System.out.println("current: ");
		print(current);
		System.out.println(" ");*/
		//For Debugging.
		return current;
	}
	
/*static void mergeSortBetter(int[] array, int bottom, int top) { Working on coding a better version of mergeSort.
		
		int midpoint = (top+bottom)/2;
		boolean smallest = false;
		int[] temp = new int[array.length];
		
		if(top-bottom >= 1) {//the array that was split is still greater than or equal to 2 indices
			mergeSort(array, bottom, midpoint);//bottom and top are included
			
			mergeSort(array, midpoint + 1, top);
			smallest = true;
		}
		
		int sortCounter = 0;
		int leftHalf = 0;
		int rightHalf = 0;
		
		if(!smallest) {
			while(sortCounter < (top-bottom)) {//combine both halves
				
			if(array[leftHalf + bottom] <= array[rightHalf + midpoint]) {
				//System.out.println("Combining " + left[leftHalf]);
				temp[sortCounter] = array[leftHalf + bottom];
				if(leftHalf < (midpoint-bottom)) {
					leftHalf++;
				}
				
				sortCounter++;
			}else {
				//System.out.println("Combining " + right[rightHalf]);
				temp[sortCounter] = array[rightHalf + midpoint];
				if(rightHalf < (top - midpoint)) {
					rightHalf++;
					}
				sortCounter++;
				}
			}	
		}else {
			System.out.println("Reached bottom " + array[bottom]);// For debugging
		}
		
		for(int i = 0; i < top-bottom; i++) {
			
		}
		
	}*/
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Boolean exit = false;
		
		int[] array = new int[100];
		
		while(exit == false) {
			System.out.println("Input:");
			System.out.println("0: Exit");
			System.out.println("1: Populate Sequentially");
			System.out.println("2: Populate Randomly");
			System.out.println("3: Check if sorted");
			System.out.println("4: Display");
			System.out.println("5: Shuffle");
			System.out.println("6: Linear Search");
			System.out.println("7: Binary Search");
			System.out.println("8: Bubble Sort");
			System.out.println("9: Selection Sort");
			System.out.println("10: Insertion Sort");
			System.out.println("11: Radix Sort");
			System.out.println("12: Merge Sort");
			int choice = input.nextInt();
			
			if(choice == 0) {
				exit = true;
			}else if(choice == 1) {
				popSequential(array);
			}else if(choice == 2) {
				popRandom(array);
			}else if (choice == 3) {
				System.out.println(checkSorted(array));
			}else if(choice == 4) {
				print(array);
			}else if(choice == 5) {
				shuffle(array);
			}else if(choice == 6) {
				System.out.print("Pick a number from 1 - 100 (-1 means it isn't in the array): ");
				int num = input.nextInt();
				System.out.println(linearSearch(array, num));
			}else if(choice == 7) {
				System.out.print("Pick a number from 1 - 100 (-1 means it isn't in the array): ");
				int num = input.nextInt();
				System.out.println(binarySearch(array, num, 99, 0));
			}else if(choice == 8) {
				bubbleSort(array);
			}else if(choice == 9) {
				selectionSort(array);
			}else if(choice == 10) {
				array = insertionSort(array);
			}else if(choice == 11) {
				array = radixSort(array);
			}else if(choice == 12) {
				array = mergeSort(array, 0, 99);
			}
			
		}		
		
		
		input.close();
	}
}
