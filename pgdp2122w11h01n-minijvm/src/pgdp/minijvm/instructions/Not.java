package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Inverts the logical value of the top element on the stack.
 */
public class Not extends Instruction {

	public void execute(Simulator simulator) {
		// TODO: NOT
		simulator.getStack().push(simulator.getStack().pop() == 0 ? 1 : 0);
	}
}
