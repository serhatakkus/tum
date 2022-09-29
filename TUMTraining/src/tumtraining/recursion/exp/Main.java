package tumtraining.recursion.exp;

public class Main {

	public static void main(String[] args) {
		// Expression deneme = new Const(10);
		// ((3+2) + (((3 + (2+1)) + 4 ) + 2 ))
		
		/* (						  )
		 *  (3+2) + (				 )
		 * 			(			) + 2 
		 * 			 (3 +  ) + 4 
		 * 			  (2+1)
		 
		 */
		

		Expression ex1 = new Add(new Const(3), new Const(2));

		Expression ex2 = new Add(new Const(2), new Const(1));
		Expression ex3 = new Add(new Const(3), ex2);
		Expression ex4 = new Add(ex3, new Const(4));
		Expression ex5 = new Add(ex4, new Const(2));
		
		Expression ex6 = new Add(ex1, ex5);
		
		ConstCounter vst = new ConstCounter();
		ex6.accept(vst);
		System.out.println("const count: " + vst.getCount());

		HeightCounter hg = new HeightCounter();
		ex6.accept(hg);
		System.out.println("height: " + hg.getHeightMax());
		
		/*
		ConstCounter deneme1 = new ConstCounter();
		deneme.accept(deneme1);
		System.out.println(deneme1.getCount());
		*/
	}
}

abstract class Expression {
	public abstract void accept(Visitor visitor);
}

final class Const extends Expression {
	private int n;

	public Const(int x) {
		n = x;
	}
	
	@Override
	public String toString() {
		return "" + n;
	}

	public int getValue() {
		return n;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

final class Add extends Expression {
	private Expression left, right;

	public Add(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	@Override
	public String toString() {
		return "(" + left.toString() + " + " + getRight() + ")";
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

interface Visitor {
	public void visit(Const c);

	public void visit(Add add);
}

// constlarin sayisini ve kac adim iceriye gittigini sayacagiz
class ConstCounter implements Visitor {

	int count = 0;

	public int getCount() {
		return count;
	}

	@Override
	public void visit(Const c) {
		count++;
	}

	@Override
	public void visit(Add add) {
		add.getLeft().accept(this);
		add.getRight().accept(this);
	}

}

class HeightCounter implements Visitor {

	private int height = 0;
	private int heightMax = 0;

	@Override
	public void visit(Const c) {
		// do nothing
		return;
	}

	@Override
	public void visit(Add add) {
		height++;
		if (height > heightMax) {
			heightMax = height;
			System.out.println(add.toString());
		}
		add.getLeft().accept(this);
		add.getRight().accept(this);
		height--;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getHeightMax() {
		return heightMax;
	}
	
}
