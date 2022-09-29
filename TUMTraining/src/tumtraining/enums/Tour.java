package tumtraining.enums;

public class Tour {

	public static void main(String[] args) {

		for (Status st : Status.values()) {

			System.out.println(
					st.ordinal() + ". " + st.name() + ": class - " + Status.finished(st) + " - " + st.isFinished());
		}

		Status st = Status.getByNumber(1);
		System.out.println(st.toString());

		if (st == Status.CANCELLED) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

	}

}
