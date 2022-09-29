package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * pops the top two elements off the stack and pushes the result of a logical
 * AND back onto the stack. All values that are not 0 count as true.
 */
public class And extends Instruction {

	public void execute(Simulator simulator) {
		// TODO: AND
		int a = simulator.getStack().pop();
		int b = simulator.getStack().pop();
		simulator.getStack().push((a != 0 && b != 0) ? 1 : 0);
	}
}
