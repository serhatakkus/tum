package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Pops the top element from the stack. If the value of this element is 0, a
 * jump should be made to the instruction at index i. Otherwise, no jump is
 * performed and program flow continues normally.
 */
public class FJump extends Instruction {

	private int i;

	// TODO: FJUMP
	public FJump(int targetAddress) {
		this.i = targetAddress;
	}

	public void execute(Simulator simulator) {
		int value = simulator.getStack().pop();
		if (value == 0) {
			simulator.setProgramCounter(i);
		}
	}

	public int getParameter() {
		return i;
	}
}
