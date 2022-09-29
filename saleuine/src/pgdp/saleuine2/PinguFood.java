package pgdp.saleuine2;

public class PinguFood {

	private int age;
	private int weight;

	public PinguFood(int age, int weight) {
		this.age = age;
		this.weight = weight;
	}

	public boolean isEdible() {

		return false;

	}

	public String toString() {
		return "Alter: " + age + " Jahre, Gewicht: " + weight + "g";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
