package tumtraining.poly;

import java.awt.Color;

public abstract class Animal {

	public final String name;
	public Color color;
	public int age;

	public Animal() {
		this.name = "default name";
	}
	
	public Animal(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	/*-
	public void setName(String name) {
		this.name = name;
	}
	*/

	private void privateMethod() {
		System.out.println("i am private method");
	}
	
	public void talkToMe() {
		System.out.println(makeNoise() + "! I'm " + this.name + " and i am " + age + " years old. I have " + legCount() + " legs");
		System.out.println(makeNoise() + "! Ben " + name + " ve " + age + " yaþýndayým");
	}
	
	public abstract int legCount();
	
	public abstract String makeNoise();
	
}
