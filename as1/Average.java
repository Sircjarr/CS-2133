import java.util.*;
public class Average {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double inputNumber = 0;
		int numberOfInputs = 0;
		double sum = 0; 
		System.out.println("Enter a number ");
		
		// Continue entering numbers, until an entered number is < 0
		while (inputNumber >= 0) {
			inputNumber = Double.parseDouble(scan.next());
			if (inputNumber>=0) {
				sum+= inputNumber; 
				numberOfInputs++; 
			}
		}
		double average = sum / numberOfInputs;
		System.out.print("You entered " + (numberOfInputs) + " numbers averaging " +  average + ".");
	}
}