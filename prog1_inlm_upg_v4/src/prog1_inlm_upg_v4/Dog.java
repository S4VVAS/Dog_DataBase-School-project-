package prog1_inlm_upg_v4;

public class Dog {

	// Henrik - "Final NAME och BREED behöver inte ha stora bokstäver i detta
	// fallet"
	private final String name, breed;
	private final double TAX_TAIL_LENGTH = 3.7;
	private int age, weight;
	private User owner;

	public Dog(String name, String breed, int age, int weight) {
		this.name = name.toLowerCase();
		this.breed = breed.toLowerCase();
		this.age = age;
		this.weight = weight;
	}

	public void increaseAge() {
		age++;
	}

	public void removeOwner() {
		this.owner = null;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		User owner = this.owner;
		return owner;
	}

	public String getName() {
		String name = this.name;
		return name;
	}

	public String getBreed() {
		String breed = this.breed;
		return breed;
	}

	public int getAge() {
		int age = this.age;
		return age;
	}

	public int getWeight() {
		int weight = this.weight;
		return weight;
	}

	public double getTailLength() {
		if (breed.equals("tax") || breed.equals("dachshund")) {
			return TAX_TAIL_LENGTH;
		} else {
			double tailLength = (age * weight) / 10.0;
			return tailLength;
		}
	}

	private String checkOwner() {
		if(getOwner() != null) {
			return ", " + StaticMethods.firstStrToUp(getOwner().getName());
		}
		return "";
	}
	
	@Override
	public String toString() {
		return (StaticMethods.firstStrToUp(name) + " (" + StaticMethods.firstStrToUp(breed) + ", " + age + " år, " + weight
				+ "kg, svans=" + getTailLength() + checkOwner() + ")");
	}
}
