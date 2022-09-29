package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/**
 * Pops the top two elements a, b off the stack and pushes the result of b - a onto the stack.
 */
public class Sub extends Instruction {

    public void execute(Simulator simulator) {
        //TODO: SUB
    	int result = 0 - simulator.getStack().pop() + simulator.getStack().pop();
    	simulator.getStack().push(result);
    }
}
