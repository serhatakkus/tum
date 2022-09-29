package pgdp.saleuine2;

public class Anchovie extends PinguFood {
	private static final int MIN_AGE = 1;
	private static final int MIN_WEIGHT = 5;

	public Anchovie(int age, int weight) {
		super(age, weight);
	}

	@Override
	public boolean isEdible() {
		if (getAge() >= MIN_AGE && getWeight() >= MIN_WEIGHT) {
			return true;
		}
		return false;

	}

	@Override
	public String toString() {

		return "Sardelle(Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g)";

	}

}
