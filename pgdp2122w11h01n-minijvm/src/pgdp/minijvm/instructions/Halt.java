package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Halt extends Instruction {

    public void execute(Simulator simulator) {
        //TODO: HALT
    	simulator.setHalted(true);
    }
}
