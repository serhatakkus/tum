package tumtraining.poly;

public class Cat extends Animal {

	public Cat(String name) {
		super(name);
	}
	
	public int legCount() {
		return 4;
	}

	public String makeNoise() {
		return "meoovvv";
	}
}
