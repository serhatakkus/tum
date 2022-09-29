package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

/*
 * Modifizieren Sie diese Klasse nicht.
 */
public abstract class Instruction {

    /**
     * Führt die Instruktion auf einem Simulator aus.
     *
     * @param simulator Der Simulator, auf dem die Instruktion ausgeführt werden soll.
     */
    public abstract void execute(Simulator simulator);

}
