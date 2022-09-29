package tumtraining.poly;

import java.awt.Color;

class A {

	int y = 3;
	static void f(A a) {
		System.out.println("static A");
	}

	void g(A a) {
		System.out.println("object A");
	}
}

class B extends A {

	int y = 9;
	static void f(A a) {
		System.out.println("static B");
	}

	void g(A a) {
		System.out.println("object B");
	}
}

public class PolyExample {

	public static void main(String[] args) {

		A a = new A();
		B b = new B();
		A ab = new B();

		ab.f(a);
		ab.g(a);
		b.f(a);
	}

	public static void example() {
		Animal myFriend = new Bird("Little Bird");
		Animal yourFriend = new Cat("my cat");

		myFriend.age = 8;
		myFriend.color = Color.YELLOW;

		yourFriend.age = 3;

		myFriend.talkToMe();
		yourFriend.talkToMe();
	}

}
