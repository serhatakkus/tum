package pgdp.saleuine2;

public class Sardine extends PinguFood {
	private int length;
	private static final int MIN_AGE = 1;
	private static final int MIN_WEIGHT = 100;
	private static final int MIN_LENGTH = 14;

	public Sardine(int age, int weight, int length) {
		super(age, weight);
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	@Override
	public boolean isEdible() {
		if (getAge() >= MIN_AGE && getWeight() >= MIN_WEIGHT && length >= MIN_LENGTH) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Sardine(Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g, Länge: " + getLength() + ")";
	}

}
