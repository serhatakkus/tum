package tumtraining.poly;

public class Dog extends Animal {

	public int age;
	
	public static void main(String[] args) {
		Dog dog = new Dog();
		System.out.println(dog.getName());
		dog.getClassName();
		
	}
	
	public void setAge(int age) {

		System.out.println(this.age);
	}
	
	public int attr = 4;
	public static boolean hasTail = true;
	
	public static String getClassName() {
		return "Dog";
	}
	
	public String getName() {
		return "I am " + super.getName();
	}
	
	public void getTail() {
		System.out.println("doI have a tail ? " + hasTail);
	}

	@Override
	public int legCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String makeNoise() {
		// TODO Auto-generated method stub
		return null;
	}

}
