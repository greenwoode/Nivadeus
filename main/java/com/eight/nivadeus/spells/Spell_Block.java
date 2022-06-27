package com.eight.nivadeus.spells;

import com.eight.nivadeus.spells.web_struct.Node;

public class Spell_Block extends Node {

    private String type;
    private final int inputCount;
    private final String[] inputs;
    private final int outputCount;
    private final String[] outputs;


    public Spell_Block(double x, double y, int maxOut, int maxIn, String nodeType, int inputsCount, String[] inputNames, int outputsCount, String[] outputNames) {

        super(x, y, maxIn * inputsCount, maxIn * inputsCount);

        this.type = nodeType;

        this.inputCount = inputsCount;
        this.inputs = inputNames;
        this.outputCount = outputsCount;
        this.outputs = outputNames;
    }

    public Spell_Block(double x, double y, String nodeType) {
        this(x, y, 5, 5, nodeType, 1, new String[] {"input_1"}, 1, new String[] {"output_1"});
    }

    public String getType(){
        return this.type;
    }

    public String[] getInputs(){
        return inputs;
    }
    public String[] getOutputs(){
        return outputs;
    }

    public int getInputCount(){
        return inputCount;
    }
    public int getOutputCount(){
        return outputCount;
    }

    public String getInputName(int input){
        return inputs[input];
    }
    public String getOutputName(int output){
        return outputs[output];
    }

    @Override
    public String toString(){
        return this.getType() + ": (" + this.getXpos() + ", " + this.getYpos() + ")";
    }

}
