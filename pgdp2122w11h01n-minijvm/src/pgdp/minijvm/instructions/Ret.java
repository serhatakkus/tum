package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * exits the current stack frame and resets the simulator to the previously
 * saved state.
 * 
 * In order to use the CALL and RET instructions correctly, we need to remember
 * where we came from in order to return to the original program point after
 * executing a function. When a new function is called, first the current
 * programCounter and then the current stackOffset should be written to the
 * stack. The new stack frame then starts at the next free address. When
 * returning from a function, the values are popped off the stack and the
 * simulator is set to the state it was in before the function was called.
 */
public class Ret extends Instruction {

	public void execute(Simulator simulator) {
		// TODO: RET
		simulator.setStackOffset(simulator.getStack().pop());
		simulator.setProgramCounter(simulator.getStack().pop());
	}
}
