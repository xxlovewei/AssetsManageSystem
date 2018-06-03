package com.dt.core.tool.util.id;
public class SequenceWrapperTest {

	
	public static void main(String[] args) {
		
		SequenceWrapper sequenceWrapper = new SequenceWrapper();
		
		for (int i = 0; i < 500; i++) {
	 
			System.out.println(sequenceWrapper.getNextId());
			
		}
		
		System.out.println();
	}
	
}