/* TODO: Add your name, login, and ID as specified in the instructions */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable implements MyHashTableInterface {
	//Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;

	LinkedList<String>[] array;//Array that stores linkedlists
	int nelems;  //Number of element stored in the hash table
	int expand;  //Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	String statsFileName;     //FilePath for the file to write statistics
	//upon every rehash
	boolean printStats = false;   //Boolean to decide whether to write
	//stats to file or not after rehashing

	//Feel free to add more :)

	public MyHashTable(int size) {

		//TODO Initialize
	}

	public MyHashTable(int size, String fileName){

		//TODO
	}

	@Override
	public boolean insert(String value) {

		//TODO
		return false;
	}

	@Override
	public boolean delete(String value) {

		//TODO
		return false;
	}

	@Override
	public boolean contains(String value) {
		//TODO
		return false;
	}

	@Override
	public void printTable() {
		//TODO
		return;
	}

	@Override
	public int getSize() {
		//TODO
		return -1;
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public void rehash() {
		//TODO
		return;
	}

	/**
	* Calculate the hash value of a given string
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str){
		//TODO
		return 0;
	}

	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}

}
