package prog1_inlm_upg_v4;

import java.util.ArrayList;

public class Main {

	private Register register = new Register();
	private InputMethods inputMethod = new InputMethods();

	private void makeBid() {
		boolean bidOk = false;
		int highestBid = -1;
		String userName = inputMethod.enterUserName();
		
		if (register.getUser(userName) != null) {
			String dogName = inputMethod.enterDogName();
			if (register.getDog(dogName) != null) {
				highestBid = register.getHighestBid(dogName);
				if (highestBid != -1) {
					while (!bidOk) {
						int bid = inputMethod.printAndIntInput("Amount to bid (min " + (highestBid + 1) + ") ");
						if (bid > highestBid) {
							bidOk = true;
							Bid newBid = new Bid(register.getUser(userName), bid);
							register.getAuction(dogName).addBid(newBid);
							StaticMethods.printMessage("Bid made");
						} else
							StaticMethods.printMessage("Error: too low bid!");
					}
				} else
					StaticMethods.printMessage("Error: this dog is not up for auction");
			} else
				StaticMethods.printMessage("Error: this dog does not exist");
		} else
			StaticMethods.printMessage("Error: no such user");
	}

	private void listBids() {
		String dog = inputMethod.enterDogName();

		if (register.getDog(dog) != null) {
			if (register.getAuction(dog) != null) {
				if (register.getAuction(dog).getAllBids().length != 0) {
					StaticMethods.printMessage("Here are the bids for this auction:");
					for (String element : register.getAuction(dog).getAllBids()) {
						StaticMethods.printMessage(element);
					}
				} else
					StaticMethods.printMessage("No bids registered yet for this auction");
			} else
				StaticMethods.printMessage("Error: this dog is not up for auction");
		} else
			StaticMethods.printMessage("Error: this dog does not exist");
	}

	private void startAuction() {
		String name = inputMethod.enterDogName();

		if (register.getAuction(name) != null || register.getDog(name) == null
				|| register.getDog(name).getOwner() != null || !(register.getDog(name).getOwner() == null)) {
			StaticMethods.printMessage("Error: Auction could not be created!");
		} else {
			Auction auction = new Auction(register.getDog(name), register.getAuctionNum());
			register.addAuction(auction);
			StaticMethods.printMessage(name + " has been put up for auction in auction #" + auction.getNumber());
		}
	}

	private void listAuctions() {
		if (!register.auctionIsEmpty())
			for (int i = 0; i < register.getAuctionArrSize(); i++)
				StaticMethods.printMessage(register.getAuction(i).toString());
		else
			StaticMethods.printMessage("Error: no auctions in progress");
	}

	private void closeAuction() {
		String dogName = inputMethod.enterDogName();

		if (register.getDog(dogName) != null) {
			if (register.getAuction(dogName) != null) {
				if (register.getAuction(dogName).getHighestBid() != null) {
					User winner = register.getAuction(dogName).getHighestBid().getUser();
					winner.addDog(register.getDog(dogName));
					register.getDog(dogName).setOwner(winner);
					StaticMethods.printMessage("The auction is closed. The winning bid was "
							+ register.getAuction(dogName).getHighestBid().getBid() + "kr and was made by "
							+ StaticMethods.firstStrToUp(winner.getName()));
					register.removeAuction(register.getAuction(dogName));
				} else {
					register.removeAuction(register.getAuction(dogName));
					StaticMethods.printMessage(
							"The auction is closed. No bids where made for " + StaticMethods.firstStrToUp(dogName));
				}
			} else
				StaticMethods.printMessage("Error: this dog is not up for auction");
		} else
			StaticMethods.printMessage("Error: this dog does not exist");
	}

	private void newUser() {
		boolean loop = true;
		String name = null;

		while (loop) {
			name = inputMethod.enterUserName();
			if (register.getUserArrSize() > 0) {
				if (register.getUser(name) == null) {
					loop = false;
				} else
					StaticMethods.printMessage("Error, user with that name already exists!");
			} else
				loop = false;
		}
		User user = new User(name);
		register.addUser(user);
		StaticMethods.printMessage(StaticMethods.firstStrToUp(name) + " has been added to the register");
	}

	private void listUsers() {

		if (register.getUserArrSize() > 0) {
			for (int i = 0; i < register.getUserArrSize(); i++)
				StaticMethods.printMessage(register.getUser(i).toString());
		} else
			StaticMethods.printMessage("Error: no users in register");
	}

	private void newDog() {
		boolean loop = true;
		String name = null, breed = "";

		while (loop) {
			name = inputMethod.enterDogName();
			if (register.getDogArrSize() > 0) {
				if (register.getDog(name) == null) {
					loop = false;
				} else
					StaticMethods.printMessage("Error, dog with that name already exists!");
			} else
				loop = false;
		}
		breed = inputMethod.printAndStringInput("Enter the breed of the dog: ");

		int age = inputMethod.printAndIntInput("Enter the age of the dog: ");
		int weight = inputMethod.printAndIntInput("Enter the weight of the dog: ");

		Dog dog = new Dog(name, breed, age, weight);

		register.addDog(dog);
		StaticMethods.printMessage(StaticMethods.firstStrToUp(name) + " had been added to the register");
	}

	private void increaseAge() {
		String name = inputMethod.enterDogName();
		if (register.getDog(name) == null) {
			StaticMethods.printMessage("Error: Dog with such name does not exist");
		} else if (register.getDog(name) != null) {
			register.getDog(name).increaseAge();
			StaticMethods.printMessage(
					StaticMethods.firstStrToUp(name) + "'s new age is now " + register.getDog(name).getAge() + "!");
		}
	}

	private void listDogs() {
		if (register.dogIsEmpty()) {
			StaticMethods.printMessage("Error: no dogs in register");
			return;
		}
		int requestedLength = inputMethod.printAndIntInput("Shortest tail length to be shown: ");
		ArrayList<Dog> sortedList = register.getSortedDogList();
		for (int i = sortedList.size() - 1; i >= 0; i--) {
			if (requestedLength < sortedList.get(i).getTailLength()) {
				StaticMethods.printMessage(sortedList.get(i).toString());
			}
		}
	}

	private void removeDog() {
		String name = inputMethod.enterDogName();
		if (register.getDog(name) != null) {
			register.removeDog(register.getDog(name));
			StaticMethods.printMessage("The dog: " + StaticMethods.firstStrToUp(name) + ", was removed successfully");
		} else
			StaticMethods.printMessage("Error: Dog with such name does not exist");
	}

	private void removeUser() {
		String name = inputMethod.enterUserName();
		if (register.getUser(name) != null) {
			register.removeUser(register.getUser(name));
			StaticMethods.printMessage("The user: " + StaticMethods.firstStrToUp(name) + ", was removed successfully");
		} else
			StaticMethods.printMessage("Error: User with such name does not exist");
	}

	private void terminate() {
		StaticMethods.printMessage("Terminating.. ");
	}

	public static void main(String[] args) {
		Main main = new Main();
		String in;

		do {
			StaticMethods.ln(1);
			in = main.inputMethod.printAndStringInput("Please enter one of the following commands:\n"
					+ "\n Register new user" + "\n Register new dog" + "\n Increase age" + "\n List dogs"
					+ "\n List users" + "\n Remove dog" + "\n Start auction" + "\n List auctions" + "\n List bids"
					+ "\n Make bid" + "\n Close auction" + "\n Remove user" + "\n Exit");

			switch (in) {
			case "register new user":
			case "newu":
				main.newUser();
				continue;
			case "register new dog":
			case "newd":
				main.newDog();
				continue;
			case "increase age":
			case "incage":
				main.increaseAge();
				continue;
			case "list users":
			case "listu":
				main.listUsers();
				continue;
			case "list dogs":
			case "listd":
				main.listDogs();
				continue;
			case "remove dog":
			case "deld":
				main.removeDog();
				continue;
			case "remove user":
			case "delu":
				main.removeUser();
				continue;
			case "start auction":
			case "newa":
				main.startAuction();
				continue;
			case "list auctions":
				main.listAuctions();
				continue;
			case "make bid":
				main.makeBid();
				continue;
			case "list bids":
				main.listBids();
				continue;
			case "close auction":
				main.closeAuction();
				continue;
			case "exit":
			case "stop":
			case "end":
				main.terminate();
				in = "end";
				continue;
			default:
				StaticMethods.printMessage("Error: false input!");
			}
		} while (!in.equals("end"));

		StaticMethods.printMessage("Terminated successfully");
	}
}
