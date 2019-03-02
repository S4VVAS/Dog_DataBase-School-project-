package prog1_inlm_upg_v4;

import java.util.ArrayList;
import java.util.Collections;

public class Register {

	private ArrayList<Dog> arrayOfDogs = new ArrayList<Dog>();
	private ArrayList<User> arrayOfUsers = new ArrayList<User>();
	private ArrayList<Auction> arrayOfAuctions = new ArrayList<Auction>();
	private int auctionNumber = 1;

	public Dog getDog(String name) {
		for (int i = 0; i < getDogArrCopy().size(); i++) {
			if (getDogArrCopy().get(i).getName().equals(name)) {
				return getDogArrCopy().get(i);
			}
		}
		return null;
	}

	public User getUser(String name) {
		for (int i = 0; i < getUserArrCopy().size(); i++) {
			if (getUserArrCopy().get(i).getName().equals(name)) {
				return getUserArrCopy().get(i);
			}
		}
		return null;
	}

	public Auction getAuction(String name) {
		for (int i = 0; i < getAuctionArrCopy().size(); i++) {
			if (getAuctionArrCopy().get(i).getName().equals(name)) {
				return getAuctionArrCopy().get(i);
			}
		}
		return null;
	}

	public Dog getDog(int index) {
		return getDogArrCopy().get(index);
	}

	public User getUser(int index) {
		return getUserArrCopy().get(index);
	}

	public Auction getAuction(int index) {
		return getAuctionArrCopy().get(index);
	}

	public int getDogArrSize() {
		return getDogArrCopy().size();
	}

	public int getUserArrSize() {
		return getUserArrCopy().size();
	}

	public int getAuctionArrSize() {
		return getAuctionArrCopy().size();
	}

	public boolean dogIsEmpty() {
		return getDogArrCopy().isEmpty();
	}

	public boolean auctionIsEmpty() {
		return getAuctionArrCopy().isEmpty();
	}

	public int getHighestBid(String dogName) {
		if (getAuction(dogName) != null) {
			if (getAuction(dogName).getHighestBid() == null)
				return 0;
			else
				return getAuction(dogName).getHighestBid().getBid();
		} else
			return -1;
	}

	public int getAuctionNum() {
		return auctionNumber;
	}

	private ArrayList<Auction> getAuctionArrCopy() {
		ArrayList<Auction> array = new ArrayList<Auction>(arrayOfAuctions);
		return array;
	}

	private ArrayList<Dog> getDogArrCopy() {
		ArrayList<Dog> array = new ArrayList<Dog>(arrayOfDogs);
		return array;
	}

	private ArrayList<User> getUserArrCopy() {
		ArrayList<User> array = new ArrayList<User>(arrayOfUsers);
		return array;
	}

	public void addUser(User user) {
		arrayOfUsers.add(user);
	}

	public void addDog(Dog dog) {
		arrayOfDogs.add(dog);
		getSortedDogList();
	}

	public void addAuction(Auction auction) {
		arrayOfAuctions.add(auction);
		auctionNumber++;
	}

	public void removeUser(User user) {
		for (Auction a : arrayOfAuctions)
			a.removeBids(user);

		System.out.println(arrayOfDogs.size());
		for (int i = 0; i < arrayOfDogs.size(); i++) {
			if (arrayOfDogs.get(i).getOwner() != null) {
				if (arrayOfDogs.get(i).getOwner().getName().equals(user.getName())) {
					arrayOfDogs.remove(i);
				}
			}
		}
		arrayOfUsers.remove(user);
	}

	public void removeDog(Dog dog) {
		if (dog.getOwner() != null)
			dog.getOwner().removeDog(dog);

		if (getAuction(dog.getName()) != null)
			removeAuction(getAuction(dog.getName()));

		arrayOfDogs.remove(dog);
	}

	public void removeAuction(Auction auction) {
		arrayOfAuctions.remove(auction);
	}

	private ArrayList<Dog> convertDogToList(Dog[] arr){
		ArrayList<Dog> list = new ArrayList<Dog>();
		
		for(int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}
	
	private Dog[] swapDogs(Dog[] array, int indexA, int indexB) {
		Dog tempSave = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = tempSave;
		
		return array;
	}
	
	public ArrayList<Dog> getSortedDogList(){
		
		int size = getDogArrCopy().size();
		
		Dog[] array = new Dog[size];
		
		for(int i = 0; i < size; i++) {
			array[i] = getDogArrCopy().get(i);
		}
		
		if (array.length == 1) {
			return convertDogToList(array);
		}
		
		for (int x = 0; x < array.length - 1; x++) {
			for (int i = 1; i < array.length - x; i++) {

				if (array[i].getTailLength() > array[i - 1].getTailLength()) {
					swapDogs(array, i, i-1);
					
				} else if (array[i].getTailLength() == array[i - 1].getTailLength()) {
					int com = array[i].getName().compareTo(array[i - 1].getName());
					if (com > 0) {
						swapDogs(array, i, i-1);
					}
				}
			}
		}
		return convertDogToList(array);
	}
	
}
