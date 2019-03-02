package prog1_inlm_upg_v4;

public class Bid {

	private final User bidMaker;
	private final int bidAmount;
	
	public Bid(User user, int amount) {
		bidMaker = user;
		bidAmount = amount;
		
	}

	public User getUser() {
		return bidMaker;
	}

	public int getBid() {
		return bidAmount;
	}

	@Override
	public String toString() {
		return StaticMethods.firstStrToUp(bidMaker.getName() + " " + bidAmount + " kr");
	}
}
