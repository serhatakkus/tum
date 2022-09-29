package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Reads the value at index i of the stack and pushes it onto the stack.
 */
public class Load extends Instruction {

	private int i;

	// TODO: LOAD
	public Load(int stackAddress) {
		this.i = stackAddress;
	}

	public void execute(Simulator simulator) {
		int value = simulator.getStack().getValueAtIndex(i);
		simulator.getStack().push(value);
	}

	public int getParameter() {
		return i;
	}
}
