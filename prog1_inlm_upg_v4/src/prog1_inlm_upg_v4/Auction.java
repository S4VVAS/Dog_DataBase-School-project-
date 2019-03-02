package prog1_inlm_upg_v4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Auction {

	private final Dog dog;
	private final int auctionNumber;
	private ArrayList<Bid> arrayOfBids = new ArrayList<Bid>();

	public Auction(Dog dog, int auctionNumber) {
		this.dog = dog;
		this.auctionNumber = auctionNumber;
	}

	public String getName() {
		String name = dog.getName();
		return name;
	}

	public int getNumber() {
		int num = auctionNumber;
		return num;
	}

	public void addBid(Bid bid) {
		for (int i = 0; i < arrayOfBids.size(); i++) {
			if (arrayOfBids.get(i).getUser().equals(bid.getUser())) {
				arrayOfBids.remove(i);
			}
		}
		arrayOfBids.add(bid);
	}

	private void sortBids() {
		boolean sort = true;

		if (arrayOfBids.size() <= 1) {
			return;
		}
		while (sort) {
			sort = false;
			for (int i = 0; i < arrayOfBids.size() - 1; i++) {
				if (arrayOfBids.get(i).getBid() < arrayOfBids.get(i + 1).getBid()) {
					Collections.swap(arrayOfBids, i, i + 1);
					sort = true;
				}
			}
		}
	}

	public void removeBids(User user) {
		for (int i = 0; i < arrayOfBids.size(); i++) {
			if (arrayOfBids.get(i).getUser().getName().equals(user.getName())) {
				arrayOfBids.remove(i);
			}
		}
	}

	public Bid getHighestBid() {

		if (arrayOfBids.size() != 0) {
			sortBids();
			Bid bid = arrayOfBids.get(0);
			return bid;
		}
		return null;
	}

	public String[] getAllBids() {
		int size = arrayOfBids.size();
		sortBids();
		
		if (arrayOfBids.size() > 0) {
			String[] bids = new String[size];
			for (int i = 0; i < size; i++) {
				bids[i] = StaticMethods.firstStrToUp(arrayOfBids.get(i).toString());
			}
			return bids;
		}
		String[] bids = new String[0];
		return bids;
	}
	
	private String[] getTopBids(String[] arr) {
		if(arr.length > 3){
			String[] newArr = new String[3];
			for(int i = 0; i < 3; i++) {
				newArr[i] = arr[i];
			}
			return newArr;
		}
		return arr;
	}

	@Override
	public String toString() {
		return "Auction #" + auctionNumber + ": " + StaticMethods.firstStrToUp(dog.getName()) + ". Top bids: "
				+ Arrays.toString(getTopBids(getAllBids()));
	}
}
