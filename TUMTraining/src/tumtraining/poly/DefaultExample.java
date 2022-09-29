package tumtraining.poly;

public class DefaultExample implements PolyInterface {

	private int a;
	
	public void setA(int arg) {
		this.a = arg*2;
	}
	
	public static void main(String[] args) {
		DefaultExample def = new DefaultExample();
		def.setA(3);
		System.out.println(def.getNumber());
		System.out.println(def.getString());
		
		Math.sqrt(16);
	}

	@Override
	public String getString() {
		return "not the " + PolyInterface.super.getString();
	}
	
	@Override
	public int getNumber() {
		return a;
	}

}
