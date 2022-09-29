package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Pops the top two elements off the stack and pushes the result of the addition onto the stack.
 */
public class Add extends Instruction {

    public void execute(Simulator simulator) {
        //TODO: ADD
    	int total = simulator.getStack().pop() + simulator.getStack().pop();
    	simulator.getStack().push(total);
    }
}
