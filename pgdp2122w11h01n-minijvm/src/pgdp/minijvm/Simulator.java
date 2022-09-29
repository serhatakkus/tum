package pgdp.minijvm;

import pgdp.minijvm.instructions.Instruction;

public class Simulator {

    private Instruction[] code;
    private int programCounter = 0;
    private int stackOffset = 0;

    private Stack stack;
    private boolean halted;


    public Simulator(int stackSize, Instruction[] code) {
        //TODO: Konstruktor
    	this.code = code;
    	this.stack = new Stack(stackSize);
    }


    public void executeInstructions() {
        //TODO: Simulate
    	while (!halted && code[programCounter] != null) {
    		Instruction ins = code[programCounter++];
    		ins.execute(this);
    	}
    }

    /**
     * Liefert den Stack des Simulators.
     */
    public Stack getStack() {
        return stack;
    }

    /**
     * Setzt den Programmzähler des Simulators auf den übergebenen Wert.
     *
     * @param programCounter Der neue Wert des Programmzählers.
     */
    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    /**
     * Liefert den Wert des Programmzählers des Simulators.
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Setzt das {@code halted}-Attribut
     *
     * @param halted Der neue Wert des Attribus.
     */
    public void setHalted(boolean halted) {
        this.halted = halted;
    }

    /**
     * Liefert den Wert des {@code halted}-Attributs.
     */
    public boolean isHalted() {
        return halted;
    }

    /**
     * Liefert den Beginn des aktuellen Stack frames
     */
    public int getStackOffset() {
        return stackOffset;
    }

    /**
     * Setzt den Beginn des aktuellen Stack frames
     * @param stackOffset neuer Wert des {@code stackOffset}-Attributs-
     */
    public void setStackOffset(int stackOffset) {
        this.stackOffset = stackOffset;
    }
}
