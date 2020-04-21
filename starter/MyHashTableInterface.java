/**
* Filename: MyHashtableInterface.java
* Author: CSE12 sp20 staffs
* Description: File that contains MyHashTableInterface which defines the
* behaviors of HashTable that contains only a subset of the Java Collection's
* Framework HashTable.
*/

/**
 * Interface that defines functionality that MyHashTableInterface should
 * support.
 */
public interface MyHashTableInterface {

	/** Insert the string value into the hash table
	 *
	 * @param value value to insert
	 * @throws NullPointerException if value is null
	 * @return true if the value was inserted, false if the value was already
	 * present
	 */
	boolean insert(String value);

	/** Delete the given value from the hash table
	 *
	 * @param value value to delete
	 * @throws NullPointerException if value is null
	 * @return true if the value was deleted, false if the value was not found
	 */
	boolean delete(String value);

	/** Check if the given value is present in the hash table
	 *
	 * @param value value to look up
	 * @throws NullPointerException if value is null
	 * @return true if the value was found, false if the value was not found
	 */
	boolean contains(String value);

	/** Print the contents of the hash table. Print nothing if table is empty
	 *
	 * Example output for this function:
	 *
	 * 0:
	 * 1:
	 * 2: marina, fifty
	 * 3: table
	 * 4:
	 *
	 */
	void printTable();

	/**
	 * Return the number of elements currently stored in the MyHashTable
	 * @return nelems
	 */
	int getSize();

	/**
	 * Expands the array and rehashes all values.
	 */
	void rehash();

	/**
	 * Print statistics to the given file.
	 * @return True if successfully printed statistics, false if the file
	 *         could not be opened/created.
	 */
	boolean printStatistics();
}
