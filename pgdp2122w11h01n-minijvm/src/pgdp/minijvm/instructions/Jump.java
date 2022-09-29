package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * is to convert a jump to the instruction at index i.
 */
public class Jump extends Instruction {

	private int i;

	// TODO: JUMP
	public Jump(int targetAddress) {
		this.i = targetAddress;
	}

	public void execute(Simulator simulator) {
		simulator.setProgramCounter(i);
	}

	public int getParameter() {
		return i;
	}
}
