package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Increases the stack pointer by i. You can use Stack's alloc method to do
 * this.
 */
public class Alloc extends Instruction {

	private int i;

	// TDOD: ALLOC
	public Alloc(int count) {
		this.i = count;
	}

	public void execute(Simulator simulator) {
		simulator.getStack().alloc(i);
	}

	public int getParameter() {
		return i;
	}
}
