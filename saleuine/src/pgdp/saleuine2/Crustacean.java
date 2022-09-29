package pgdp.saleuine2;

public class Crustacean extends PinguFood {

	public Crustacean(int weight) {
		super(0, weight);

	}

	@Override
	public boolean isEdible() {
		return true;
	}

	@Override
	public String toString() {
		return "Krill(" + getWeight() + "g)";
	}

}
