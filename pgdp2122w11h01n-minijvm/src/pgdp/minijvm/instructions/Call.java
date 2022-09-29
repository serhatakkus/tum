package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * should save the current stack frame as described and continue execution at
 * the given address.
 * 
 * In order to use the CALL and RET instructions correctly, we need to remember
 * where we came from in order to return to the original program point after
 * executing a function. When a new function is called, first the current
 * programCounter and then the current stackOffset should be written to the
 * stack. The new stack frame then starts at the next free address. When
 * returning from a function, the values are popped off the stack and the
 * simulator is set to the state it was in before the function was called.
 */
public class Call extends Instruction {

	private int i;

	// TODO: CALL
	public Call(int functionPointer) {
		this.i = functionPointer;
	}

	public void execute(Simulator simulator) {
		int currProgCounter = simulator.getProgramCounter();
		simulator.getStack().push(currProgCounter);
		int currStackOffset = simulator.getStackOffset();
		simulator.getStack().push(currStackOffset);
		
		simulator.setProgramCounter(i);
	}

	public int getParameter() {
		return i;
	}
}
