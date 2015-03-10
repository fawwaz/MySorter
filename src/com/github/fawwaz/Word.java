package com.github.fawwaz;

import java.util.Comparator;

public class Word implements Comparable<Word>{

	String	primarykey;
	String	secondarykey;
	String	leadingzeros;
	Integer numberofleadingzeros; // Assuming the length is always less than 2^31-1
	Integer primarykeynumber; // The primary key in Integer data type, assuming it always less than 2^31-1, this code will run inconsistently if the input string like this one : 99999999999999999999999hoge 
	
	public Word(String string){
		extract(string);
		this.numberofleadingzeros 	= leadingzeros.length();
		this.primarykeynumber 		= primarykey.equals("") ? Integer.MAX_VALUE : Integer.valueOf(primarykey); // the primary key is set to be max value to simplify the problem so it always come on the last after everything
	}
	
	
	
	// --- Overrides ---
	@Override
	public int compareTo(Word otherword) {
		if(this.isHasAPrimaryKey() && otherword.isHasAPrimaryKey()) {
			if(this.primarykeynumber < otherword.getPrimarykeynumber()){
				return -1;
			}else if(this.primarykeynumber > otherword.getPrimarykeynumber()){
				return 1;
			}else {
				// if both primary key is equal, compare with number of leading zeros;
				if(this.numberofleadingzeros < otherword.getNumberofleadingzeros()){
					return 1;
				}else if(this.numberofleadingzeros > otherword.getNumberofleadingzeros()){
					return -1;
				}else {
					// if both leading zeros is equal compare with native compareTo.
					return this.secondarykey.compareTo(otherword.getSecondarykey());
				}
			}
		}else{
			// if doesn't has any primary key, put it on the bottom of everything
			if(this.isHasAPrimaryKey() && !otherword.isHasAPrimaryKey()){
				return -1;
			}else if(!this.isHasAPrimaryKey() && otherword.isHasAPrimaryKey()){
				return 1;
			}else{
				// if all word doesnt has any primary key , sort them using compare to string method
				return this.secondarykey.compareTo(otherword.getSecondarykey());
			}
		}
	}
	
	@Override
	public String toString() {
		return leadingzeros+primarykey+secondarykey;
	}
	
	// --- Setters & Getters ---
	public String getSecondarykey() {
		return secondarykey;
	}

	public void setSecondarykey(String secondarykey) {
		this.secondarykey = secondarykey;
	}

	public Integer getNumberofleadingzeros() {
		return numberofleadingzeros;
	}

	public void setNumberofleadingzeros(Integer numberofleadingzeros) {
		this.numberofleadingzeros = numberofleadingzeros;
	}

	public Integer getPrimarykeynumber() {
		return primarykeynumber;
	}

	public void setPrimarykeynumber(Integer primarykeynumber) {
		this.primarykeynumber = primarykeynumber;
	}
	
	
	
	// --- Boolean Functions --
	/**
	 * @param	void
	 * @return	boolean	whether the word has Primary key or not 
	 */
	public boolean isHasAPrimaryKey(){
		return (!this.primarykey.equals(""));
	}
	
	
	
	// --- Other Functions ---
	/**
	 * @param	void
	 * @return	void	Print all properties of the word 
	 */
	public void printProperties(){
		System.out.print("NUMBER["+this.toString()+"]");
		System.out.print("Leading Zero	: [" + this.leadingzeros+"]");
		System.out.print("Primary Key 	: [" +this.primarykey+"]");
		System.out.println("Secondary Key 	: [" + this.secondarykey+"]");
		System.out.println("IsHasPrimKey	: [" + this.isHasAPrimaryKey()+"]");
	}
	
	
	
	// --- Private functions ---
	/**
	 * @param	String	string to be extracted
	 * @return	Void 	extracted word component assign automatically  
	 * 					EXAMPLE
	 * 					if thestring value is "00230.hoge9", after calling this function, it makes 
	 * 					this.leadingzeros = "00" ; this.primarykey = "230" ; this.secondarykey = ".hoge9"
	 */
	private void extract(String thestring){
		boolean 	firstPrimaryFounded = false; // some flag to mark that some character is the first character seen on Zero or Primary key category. 
		int 		startIndexZero = -1, startIndexPrimary = -1, startIndexSecondary = -1 ; // Indicating the start index of Zero number or Primary key or Secondary key, if the key doesn't exist, set it to be -1
		
		// Scan the number from left (Most Significat Number) to the right (Less Significant Number)
		for (int i = 0; i < thestring.length(); i++) {
			Character character 	= thestring.charAt(i);
			if(!isNumber(character)){
				startIndexSecondary = i;
				break;
			}else{
				if(isPositiveNumber(character) && !firstPrimaryFounded ){
					startIndexPrimary=i;
					firstPrimaryFounded = true;
				}else if(isZero(character) && i==0){
					startIndexZero = i;
				}
			}
		}
		
		
		// Substract the text according to indices on the process above
		//System.out.println("NUM : ["+  thestring+"]  Idx" + startIndexZero + " Prim " + startIndexPrimary + " Second " + startIndexSecondary );
		if(startIndexZero!=-1){
			if(startIndexPrimary!=-1){
				if(startIndexSecondary!=-1){
					// If all kind of the key found on the text Ex: 00230.095hoge91 will be Leadingzeros = 00 primarykey = 230 secondary key .095hoge91
					this.leadingzeros 	= thestring.substring(startIndexZero,startIndexPrimary);
					this.primarykey 	= thestring.substring(startIndexPrimary,startIndexSecondary);
					this.secondarykey 	= thestring.substring(startIndexSecondary);
				}else{
					// if only the Secondary key start index NOT found Ex: 00230 will be Leadingzeros = 00 primarykey =230
					this.leadingzeros 	= thestring.substring(startIndexZero,startIndexPrimary); 
					this.primarykey 	= thestring.substring(startIndexPrimary);   
					this.secondarykey	= "";
				}
			}else{
				if(startIndexSecondary!=-1){
					// if only the Primary key start index  NOT found
					if(thestring.substring(startIndexZero,startIndexSecondary).length()==1){
						// if only one leading zero it become primary key and the leading zero become empty Ex: 0.095hoge91 will be leadingzeros = "" primary key= 0 secondary key .095hoge91
						this.primarykey 	= "0"; 
						this.leadingzeros 	= "";
						this.secondarykey 	= thestring.substring(startIndexSecondary);
					}else{
						// if MORE THAN ONE leading zero the leading zero become Length-1 Ex: 0000.095hoge91 will be leading zeros = 000 primary key= 0 secondary key = .095hoge91
						this.primarykey		= "0";
						this.leadingzeros 	= thestring.substring(startIndexZero,startIndexSecondary-1);
						this.secondarykey 	= thestring.substring(startIndexSecondary);
					}
				}else{
					// if BOTH the Primary and the secondary NOT found Ex:
					if(thestring.substring(startIndexZero).length()==1){ 
						// if the length of leading zero equal to 1 the zero will be the primary key without any leading zero the only case in is 0 so it will be leading zeros ="" primary key=0 secondary key="" 
						this.primarykey 	= "0";
						this.leadingzeros 	= "";
						this.secondarykey 	= "";
					}else{
						// if the length of leading zero MORE THAN 1 the zero will be the primary key with Length-1 leading zero Ex : 00000 will be leading zeros =0000 primary key=0 secondary key=""
						this.primarykey 	= "0";
						this.leadingzeros 	= thestring.substring(startIndexZero,thestring.length()-1);
						this.secondarykey 	= "";
					}
				}
			}
		}else{
			if(startIndexPrimary!=-1){
				if(startIndexSecondary!=-1){
					// If only the Leading Zero Not found Ex: 9hoge, it will be leading zeros = "" primary key = 9 secondary key= hoge 
					this.leadingzeros 	= "";
					this.primarykey 	= thestring.substring(startIndexPrimary,startIndexSecondary); 
					this.secondarykey 	= thestring.substring(startIndexSecondary);
				}else{
					// if BOTH the Leading zero and the Secondary key start index NOT found Ex: 98 , it will be leading zeros = "" primary key = 98 secondary key =""
					this.leadingzeros 	= "";
					this.primarykey 	= thestring.substring(startIndexPrimary); 
					this.secondarykey 	= "";
				}
			}else{
				if(startIndexSecondary!=-1){
					// if BOTH Leading Zero and The Primary key start index  NOT found Ex: hoge , it will be leading zeros = "" primary key =Integer.MAX_VALUE seoncdary key = hoge   
					this.leadingzeros 	= "";
					this.primarykey 	= "";  // to simplify the problem, the next step it will be assigned by inteer maximum value to "trick" the comparator so it always put at the end of listing
					this.secondarykey 	= thestring.substring(startIndexSecondary);
				}else{
					// if all the Indices is not found Ex: "" 
					this.leadingzeros 	= "";
					this.primarykey 	= ""; 
					this.secondarykey 	= "";
				}
			}
		}
	}
	
	
	/**
	 * @param	Char	character to be examined
	 * @return	boolean	whether the character is Positive number (input > 0) or not
	 * 			EXAMPLE
	 * 			if the input is '0' it will return false
	 * 			if the input is '7' it will return true 
	 */
	private boolean isPositiveNumber(Character character){
		return (character.equals('1') || character.equals('2') || character.equals('3') || character.equals('4') || character.equals('5') || character.equals('6') || character.equals('7') || character.equals('8') || character.equals('9')); 
	}
	
	/**
	 * @param	Char	character to be examined
	 * @return	boolean	whether the character is zero or not 
	 * 			EXAMPLE
	 * 			if the input is '0' it will return true
	 * 			if the input is '7' it will return false 
	 */
	private boolean isZero(Character character){
		return (character.equals('0')); 
	}
	
	/**
	 * @param	Char	character to be examined
	 * @return	boolean	whether the character is Number or not 
	 * 			EXAMPLE
	 * 			if the input is '0' or '9' it will return true
	 * 			if the input is 'a' it will return false 
	 */
	private boolean isNumber(Character character){
		return (isZero(character) || isPositiveNumber(character));
	}
	

	// --- Static Class ---
	public static Comparator<Word> WordComparator = new Comparator<Word>() {
		public int compare(Word word1, Word word2) {
			return word1.compareTo(word2);
		};
	};
}
