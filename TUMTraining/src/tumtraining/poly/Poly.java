package tumtraining.poly;

public class Poly {
	static class A {
		void f(C c) {
			System.out.println("A.f(C)" + " " + this);
			c.f(this);
			c.g(this);
		}

		static void g() {
			System.out.println("A.g()");
		}
	}

	static class B extends A {
		void f(D d) {
			System.out.println("B.f(D)");
			d.f(this);
			((C) d).g(this);
		}
/*-
		void f(C c) {
			System.out.println("B.f(C)" + " " + this);
			c.f(this);
			c.g(this);
		}
*/
		static void g() {
			System.out.println("B.g()");
		}
	}

	static class C {
		void f(A a) {
			System.out.println("C.f(A)");
		}

		static void g(A a) {
			System.out.println("C.g(A)");
		}

		static void g(B b) {
			System.out.println("C.g(B)");
		}
	}

	static class D extends C {
		void f(A a) {
			System.out.println("D.f(A)");
		}

		void f(B b) {
			System.out.println("D.f(B)");
		}

		static void g(A a) {
			System.out.println("D.g(A)");
		}

		static void g(B b) {
			System.out.println("D.g(B)");
		}
	}

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		D d = new D();

		a.f(d); // Aufruf 1
		System.out.println(" ");
		a.g(); // Aufruf 2
		System.out.println(" ");

		a = b;
		c = d;
		
		a.f(c); // Aufruf 3
		System.out.println(" ");
		a.f(d); // Aufruf 4
		System.out.println(" ");
		b.f(c); // Aufruf 5
		
	}
}
