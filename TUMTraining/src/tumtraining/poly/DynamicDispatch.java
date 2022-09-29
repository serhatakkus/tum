package tumtraining.poly;

public class DynamicDispatch {
	static class Fisch {
		String name;

		public Fisch(String name) {
			this.name = name;
			write("Ich bin " + name);
		}

		static void gruss(Fisch f) {
			write("Hello ");
			write(f.name);
		}

		static void gruss(Hering h) {
			write("Hi ");
			write(h.name);
		}

		void bildeSchwarm(Fisch f) {
			Fisch.gruss(f);
			write(" mein Fisch Schwarm");
		}

		void bildeSchwarm(Hering h) {
			Fisch.gruss(h);
			write(" mein Hering Schwarm");
		}
	}

	static class Hering extends Fisch {
		public Hering(String name) {
			super(name);
			write(", ein stolzer Hering");
		}

		static void gruss(Fisch f) {
			write("Servus ");
			write(f.name);
		}

		static void gruss(Hering h) {
			write("Servus ");
			write(h.name);
		}

		void bildeSchwarm(Fisch f) {
			Hering.gruss(f);
			write(" mein Fisch Schwarm");
		}

		void bildeSchwarm(Hering h) {
			Hering.gruss(h);
			write(" mein Hering Schwarm");
		}

	}

	static void write(String str) {
		System.out.print(str);
	}

	public static void main(String[] args) {
		Fisch f = new Fisch("Frank");
		System.out.println();
		Hering h = new Hering("Hannelore");
		System.out.println();
		Fisch g = h;
		g.bildeSchwarm(h);
		System.out.println();
		write(((Fisch) h).name);
		System.out.println();
		Fisch.gruss(h);
		System.out.println();
		Hering.gruss(f);
		System.out.println();
		f.bildeSchwarm((Fisch) h);
		System.out.println();
		g.bildeSchwarm((Hering) f);
	}
	/*-
		Ich bin FrankIch bin Hannelore, ein stolzer HeringServus Hannelore mein Hering SchwarmHanneloreHi HanneloreServus FrankHi Hannelore mein Fisch SchwarmException in thread "main" java.lang.ClassCastException: class tumtraining.poly.DynamicDispatch$Fisch cannot be cast to class tumtraining.poly.DynamicDispatch$Hering (tumtraining.poly.DynamicDispatch$Fisch and tumtraining.poly.DynamicDispatch$Hering are in unnamed module of loader 'app')
	at tumtraining.poly.DynamicDispatch.main(DynamicDispatch.java:74)

	 */
}
