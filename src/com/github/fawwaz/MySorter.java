package com.github.fawwaz;

import java.util.ArrayList;

public class MySorter {
	ArrayList<String> inputstring;
	ArrayList<String> retval;
	
	public MySorter(ArrayList<String> inputstring){
		this.inputstring = inputstring;
	}
	
	public ArrayList<String> doSorting(){
		retval = new ArrayList<>();
		
		for(String input: inputstring){
			if(isHasAPrimaryKey(input)){
				if(isStartedWithZero(input)){
					System.out.println("[PRIMARY- ANY LEADING ZERO] " + input + " = " + CountZero(input));
				}else{
					System.out.println("[PRIMARY - NO LEADING ZERO] " + input);
				}
			}else{
				System.out.println("[NO PRIMARY] " + input);
			}
		}
		
		return retval;
	}
	
	// --- Private Funtions ---
	/**
	 * @param 	String	the string to be checked
	 * @return 	boolean	false is string doesn't has any primary sorter 
	 */
	private boolean isHasAPrimaryKey(String str){
		Character firstchar = str.charAt(0);
		if(isStartedWithZero(str) || firstchar.equals('1') || firstchar.equals('2') || firstchar.equals('3') || firstchar.equals('4') || firstchar.equals('5') || firstchar.equals('6') || firstchar.equals('7') || firstchar.equals('8') || firstchar.equals('9')){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * @param 	String 	the string to be checked
	 * @return	boolean false if string doesnt start with 0;  
	 */
	private boolean isStartedWithZero(String str){
		Character firstchar = str.charAt(0);
		if(firstchar.equals('0')){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @param 	String 	the string with any leading zero 
	 * @return	int 	the number of leading zero  
	 */
	private int CountZero(String str){
		int retval = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='0'){
				retval++;
			}else{
				break;
			}
		}
		return retval;
	}
	
	/**
	 * @param 	String	the string of key with any leading zero
	 * @return	int		the integer of primary key extracted 
	 */
	private int getPrimaryKey(String str){
		for (int i = str.length(); i > 0; i--) {
			
		}
		return 0;
	}
}

