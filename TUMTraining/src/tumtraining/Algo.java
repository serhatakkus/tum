package tumtraining;

public class Algo {

	public static void main(String[] args) {

		reverse("text");
		reverse("asdf ghjk");
		reverse("123456789");
	}

	/**
	 * reverses the incoming parameter and writes to console Ex: text -> txet "asdf
	 * ghjk" -> "kjhg fdsa"
	 * 
	 * @param text
	 */
	public static void reverse(String text) {
		String result = " ";

		for (int j = text.length() - 1; j >= 0; j--) {
			result = result + text.charAt(j);
		}
		System.out.println(result);
	}

	/**
	 * reverses the incoming parameter and writes to console Ex: text -> txet "asdf
	 * ghjk" -> "kjhg fdsa"
	 * 
	 * @param text
	 */
	public static void reverse2(String text) {
		String result = " ";

		for (int j = text.length() - 1; j >= 0; j--) {
			result = result + text.charAt(j);
		}
		System.out.println(result);
	}
}
