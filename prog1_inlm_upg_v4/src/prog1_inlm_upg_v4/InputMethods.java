package prog1_inlm_upg_v4;

import java.util.Scanner;

public class InputMethods {

	private String strInput;
	private int intInput;
	private Scanner scanner = new Scanner(System.in);

	public String stringInput() {
		strInput = scanner.nextLine().toLowerCase().trim();
		return strInput;
	}

	public int integerInput() {
		intInput = scanner.nextInt();
		scanner.nextLine();
		return intInput;
	}
	
	public String whileEmpty(String string) {
		while(string.isEmpty()) {
			string = stringInput().toLowerCase();
			if(string.isEmpty()) 
				StaticMethods.printMessage("Error: cant be empty");
		}
		return string;
	}

	public String printAndStringInput(String textToPrint) {
		StaticMethods.printMessage(textToPrint);
		return whileEmpty("");
	}

	public int printAndIntInput(String textToPrint) {
		StaticMethods.printMessage(textToPrint);
		return integerInput();
	}
	
	public String enterDogName() {
		return printAndStringInput("Enter the name of the dog: ");
	}
	
	public String enterUserName() {
		return printAndStringInput("Enter the name of the user: ");
	}

	public void addOwnerTo(User owner, Dog dog) {
		dog.removeOwner();
		dog.setOwner(owner);
	}

	public void delOwnerFrom(Dog dog) {
		dog.removeOwner();
	}

	public void addDogTo(User owner, Dog dog) {
		owner.addDog(dog);
	}

	public void delDogFrom(User owner, Dog dog) {
		owner.removeDog(dog);
	}
}
