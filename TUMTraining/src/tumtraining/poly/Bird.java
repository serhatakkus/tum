package tumtraining.poly;

public class Bird extends Animal {

	public Bird(String name) {
		super(name);
	}

	//@Override
	public int legCount() {
		return 2;
	}


	//@Override
	public String makeNoise() {
		return "cik cik cik";
	}
	
}
