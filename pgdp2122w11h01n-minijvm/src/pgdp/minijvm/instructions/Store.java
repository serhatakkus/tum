package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Pops the top value off the stack and stores it at index i of the stack.
 */
public class Store extends Instruction {

	private int i;

	// TODO: STORE
	public Store(int stackAddress) {
		this.i = stackAddress;
	}

	public void execute(Simulator simulator) {
		simulator.getStack().setValueAtIndex(i, simulator.getStack().pop());
	}

	public int getParameter() {
		return i;
	}
}
