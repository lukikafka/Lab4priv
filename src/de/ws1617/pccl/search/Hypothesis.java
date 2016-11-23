package de.ws1617.pccl.search;

public class Hypothesis {

	int state; //current state
	int inputIndex; //position in the input
	
	public Hypothesis (int state, int inputIndex)
	{
		this.state = state;
		this.inputIndex = inputIndex;
	}
	
	public int getState()
	{
		return state;
	}
	
	@Override
	public String toString() {
		return "Hypothesis [state=" + state + ", inputIndex=" + inputIndex + "]";
	}

	public int getInputIndex ()
	{
		return inputIndex;
	}

}
