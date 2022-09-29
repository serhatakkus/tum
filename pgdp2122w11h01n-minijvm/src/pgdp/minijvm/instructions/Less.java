package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Pops the top two elements a, b off the stack and pushes the result of the
 * comparison b < a encoded as int onto the stack. the truth value true should
 * be coded by a 1, the truth value false by a 0.
 */
public class Less extends Instruction {

	// TODO: LESS
	public void execute(Simulator simulator) {
		int a = simulator.getStack().pop();
		int b = simulator.getStack().pop();
		simulator.getStack().push(b < a ? 1 : 0);
	}
}
