package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Loads the constant i onto the stack
 */
public class Const extends Instruction {

	private int i;

	// TODO: Const
	public Const(int constant) {
		this.i = constant;
	}

	public void execute(Simulator simulator) {
		simulator.getStack().push(i);
	}

	public int getParameter() {
		return i;
	}
}
