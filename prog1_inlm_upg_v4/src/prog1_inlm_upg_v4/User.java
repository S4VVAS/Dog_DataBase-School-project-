package prog1_inlm_upg_v4;

import java.util.ArrayList;
import java.util.Arrays;

public class User {

	//Henrik - "Final NAME behöver inte ha stora bokstäver i detta fallet"
	private final String name;
	private ArrayList<Dog> ownedDogs = new ArrayList<Dog>();
	
	public User(String name) {
		this.name = name;
	}

	public String getName() {
		String name = this.name;
		return name;
	}

	public String[] getDogNames() {
		if (ownedDogs != null) {
			String[] dogs = new String[ownedDogs.size()];
			for (int i = 0; i < ownedDogs.size(); i++) {
				dogs[i] = StaticMethods.firstStrToUp(ownedDogs.get(i).getName());
			}
			return dogs;
		}
		String[] dogs = new String[0];
		return dogs;
	}

	public void addDog(Dog dog) {
		ownedDogs.add(dog);
	}
	
	public void removeDog(Dog dog) {
		ownedDogs.remove(dog);
	}

	public ArrayList<Dog> getDogs() {
		ArrayList<Dog> dogs = ownedDogs;
		return dogs;
	}

	@Override
	public String toString() {
		return StaticMethods.firstStrToUp(name) + " " + Arrays.toString(getDogNames());
	}
}
