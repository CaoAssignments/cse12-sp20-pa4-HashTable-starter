# CSE 12 Spring 2020 PA4 - Hash Table (100 Points)
**Due date: Monday, April 27 @ 11:59PM PDT**  
(Tuesday, April 28 @ 11:59pm PDT w/ slip day. If you submit your assignment late, the autograder will automatically use your slip day if you have any remaining.)

### Useful Resources:
Throughout this assignment, you may find the following resources helpful.  Refer to them BEFORE posting questions on Piazza.

- [Reference guide for Linux/Vim/SSH/scp](https://hackmd.io/@hl99/ByE0dv-PU)
- [Connecting to the lab machines remotely](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing)
- [Running bash on Windows](https://docs.google.com/document/d/1SMnopsKw0lHWCxkQ0ETgkfLFdfonjGGh4CgfagnFcSU/edit)
- [Unix reference sheet](https://files.fosswire.com/2007/08/fwunixref.pdf)
- [JUnit testing tutorial](https://www.tutorialspoint.com/junit/junit_environment_setup.htm)
### Provided Files:
- MyHashTableInterface.java
- MyHashTable.java
- MyHashtableTester.java

### Files to Submit:
- MyHashTable.java
- MyHashtableTester.java

### Goal
In Programming Assignment 4, you will implement a data structure similar to Java's HashTable and create JUnit tests to verify proper operation and implementation. In order to implement MyHashTable, you will implement the MyHashTableInterface and additional methods. You will use existing JUnit tests and also write your own JUnit tests to test your implementation of the MyHashTable.

**As you get started, please pay attention to the following:**
* Please read the **ENTIRE write-up** before getting started.
* For this homework and all homework in CSE 12, you may not change any public class/method or variable names given in the write-up or starter code.

### Logistics:
In EACH AND EVERY FILE that you turn in, we need the following in comments at the top of each file.  These are essential so that we can more easily process your submissions and ensure that you receive proper credit. This is a very large class with over 700 students when combining all lectures. **No name, no points**. 

NAME: <your name>  
ID: <your student ID>  
EMAIL: <your email>  


## Getting Started
We recommend that you work on the lab machines (for remote access--see instructions [here](https://drive.google.com/file/d/1I7b7QXRVCL2rjgiLU9XbWDFa03g9AIjc/view?usp=sharing)), but if you choose to work on your own machine, make sure your code runs correctly on the ieng6 server, as we will be using the same environment to test your code.

**What is the ieng6 server?** The ieng6 server is a network of computers at UCSD. When you "ssh" to the ieng6 server, it means you are connected to a remote computer system. Your cs12 account has all of the necessary programs and correct versions installed.   
**Do I have to use the ieng6 server?** No, you can also work on your personal computer locally.  

The reason we reccomend the ieng6 server is so that your results are consistent with what we will see when we run it. One thing you could do is write and test your code locally and then before you submit check to see if your code will run on the ieng6 server.  

If you are still unsure about the ieng6 server ask a tutor or TA and they can help! Please also refer to discussion slides and provided documentation. 


#### Getting the Starter Code
Get the starter code from GitHub [here](https://github.com/CaoAssignments/cse12-sp20-pa4-HashTable-starter). You have two options: 
1.  Use the command line. In your terminal, run the following: `git clone https://github.com/CaoAssignments/cse12-sp20-pa4-HashTable-starter.git`  
2.  Download the repo as a zipped folder.
![](https://i.imgur.com/SjXKMV7.png)


When you run 'javac' and 'java' make sure you are in the same directory where the files are located. This is called your working directory. For this assignment, you will need to utilize the .jar files in the 'libs' directory in the starter code. This will be similar to how you compiled and executed in PA1, PA2, etc. 



## Part 1 - Understanding and Testing First

In this programming assignment, you will be implementing a basic Hash Table. We have provided you with an interface (MyHashTableInterface.java) and starter code that implements the given interface (MyHashTable.java). You will also need to test your code by creating your own tests to ensure that your implementation functions correctly.

**First Understand what MyHashTable will do**
In order to write a good tester, you will need a deep understanding of how the classes and methods you are testing are supposed to work.  So before you start writing your tester, you should read part 2 in order to understand what MyHashTable classes are supposed to do. You may refer to the documentation for [hash table](https://docs.oracle.com/javase/10/docs/api/java/util/Hashtable.html). 

**Test MyHashTable with MyHashTableTester.java**
* The repository will contain MyHashTableTester.java. This is a starter file that defines a small number of tests against MyHashTable using the JUnit testing framework introduced in previous PA's. Observe and check how each test is written and what it tests for.
* You are strongly encouraged to add more tests, as the provided starter test is not comprehensive, and your program will later be tested against our master test. You may refer to the [Testing section](#Testing) in this write-up when you write your unit tests.
* You should make sure to also include tests to check that your method throws the correct exceptions when they are expected to throw them. There are more sophisticated ways to do this (feel free to investigate and use them), but the simple approach is to do the following:
```
@Test
public void testInsertNullPointerException(){
	try{
		testHashTable.insert(null);
		fail("Expected a NullPointerException to be thrown");
	}catch(NullPointerException e){
		assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
	}
}
```
* After final submission, we will be running more extensive tests on your code. The points awarded from our tests will make up your final grade.
* You must not change any method names/signatures provided in the starter code.

## Part 2 - Hash Table Implementation
**Once you understand the behavior of the HashTable defined by MyHashTableInterface, your next task is to start implementing your HashTable, MyHashTable.**
*  If you look carefully over the file, MyHashTable.java, you will see that the MyHashTable class implements MyHashTableInterface. This MyHashTableInterface interface will only be a subset of the Java Collection’s Framework HashTable and you only **need to implement the methods stubbed out in the interface and the hash function**. The methods to be implemented are also listed in the table below, along with brief descriptions.
* There are various ways to implement a HashTable and various ways to resolve HashTable collisions, so make sure that you meet the requirements outlined for this assignment. In this assignment, you will be using **separate chaining** to resolve collisions. In separate chaining, entries in the hash table are linked lists that reference the head of a linked list (“chain”). Elements of the linked lists contain the keys. Below is an example illustration of how separate chaining is represented in a hash table.
![](https://i.imgur.com/jnzpr5H.png)


* Your program will be graded using an automatic script. Please implement more JUnit tests to push your HashTable to be perfect because we sincerely promise that our automatic script will do our worst!
**Note that you will also be graded on how well written your JUnit tests are.** 
<!-- * **Instance variables:** There are **6** instance variables. An Object array that holds all the elements in the ArrayList and an int that keeps track of how many elements are in the ArrayList. Implement them based on specifications below. -->
* **Constructors:** There are **two** constructors. They should all update the appropriate instance variables based on the specifications below. 
* **Methods:** There are **7** methods. See below for descriptions.
    * **Method comments:** You must provide method/class/inline comments, following the style guidelines posted on Piazza. 

### Instance variables:
**Note: Do not make these instance variables private. They should have the default access modifier.**

| Instance variable | Description |
|-------------|--------------------|
| `LinkedList<String>[] array` | The underlying data structure of the MyHashTable. The index of this array should correspond to the hash values of the Strings that you insert.<br/><br/>**Note**: You may assume that no one (other than possibly your own code) will change this array to be `null` even though it's not private. |
| `int nelems` | Keeps track of the number of elements in MyHashTable. |
| `int expand` | Keeps track of the number of times that the table has been expanded. |
| `int collision` | Keeps track of the number of collisions since the last expansion. |
| `String statsFileName` | File path for the file to write the statistics to. |
| `boolean printStats` | Keeps track of whether to write statistics to file or not. |
    
* **Do not change the 6 instance variables above and do not add any extra instance variables. You can still have `private static final` variables for your constants.**  


### Public methods and constructors:
| Method Name | Description | Exceptions to Throw |
|-------------|-------------|---------------------|
| `public MyHashTable(int size)`| The constructor that creates a hash table of a given size. If a user uses this constructor, the instance variable `printStats` should be set to false and you should not use `printStatistics()` to print the statistics before each resizing. | `IllegalArgumentException` if the size is less than zero.
| `public MyHashTable(int size, String fileName)`|The constructor that creates a hash table of a given size. If a user uses this constructor, the instance variable `printStats` should be set to true and you should use `printStatistics()` to print the statistics to the given file before each resizing.<br><br>**Note:** You may throw those exceptions in any order. |`IllegalArgumentException` if the size is less than zero.<br/><br/>`NullPointerException` if the filename is null.
|`public int hashString(String str)`|Compute the hash value of the given String. <br><br>You must implement **one of the hash functions given in "Hash Functions.pdf" Please follow the instructions on the pdf**.<br><br>**Note:** You should call this function to compute the hash value before inserting a new element into the hash table, deleting an element, or checking existence of an element.|None|
| `public boolean insert(String value)`|Inserts element value in the hash table.<br><br> Your program should return true or false, depending on whether it was inserted or not. Return true if the item was successfully inserted (you can insert the element to the linked list in any order) and false if it already exists. <br><br>**Note:** Before insertion, check whether the load factor reaches 2/3 with current insert. If so, call rehash() before insertion. | `NullPointerException` if a null value is passed. |
| `public boolean delete(String value)`| Uses the hash table to determine where the element value is and delete it from the hash table.<br><br> Your program should return true if the item was successfully deleted and false if it can’t be deleted (an item can’t be deleted if it does not exist in the hash table). If the element deleted is the last element of the linked list at that index, remove the linked list from the index by setting the index back to null. |`NullPointerException` if a null value is passed.
| `public boolean contains(String value)` | Uses the hash table to determine if elem is in the hash table.<br><br> Your program should return true or false, depending on whether the item exists. |`NullPointerException` if a null value is passed. |
| `public void printTable()` | Print out the hash table to stdout. For each index in the LinkedList array, you need to first print out the index number, followed by a colon. After each index, print out each String element in the LinkedList with a space in front. If there is more than one element in the LinkedList, separate each element by a comma. If there is an empty slot in the LinkedList array, simply print out an empty line. <br><br>**Note:** Please refer to the sample output format for `printTable()` in the write up below. You will have to match the format exactly to receive credit.  | None |
| `public int getSize()` | Returns the number of elements currently stored in the hash table. | None
|`public void rehash()`|Expand the array and rehash all values. <br><br>When the load factor becomes **greater than or equal to ⅔**, you must expand the size of the array to the smallest prime number that is greater than the doubled size and rehash all the values. <br><br>You may use `primeGen()` (provided in the starter code) to generate the new size. <br>**Note:** Make sure to call hashString() to rehash the elements back to the MyHashTable|None|

* **The implementation of the printStatistics method has already been given to you. Please do NOT modify the method.**

**Some implementation notes:**
* All elements to be hashed are of type **String**. Our HashTable will not take generic types.
* For this assignment, we are using **separate chaining** to resolve collisions. Refer to the section on separate chaining above.
* Note that in the contains/delete methods, **you shouldn’t need to search through the entire table to find an element.**
* You must keep track of the load factor of your table and when the load factor becomes **greater than or equal to ⅔** due to an insertion, you must expand the size of the array to the smallest prime number that is greater than the doubled size and rehash all the values.
* Choose one of the hash functions from **"Hash Functions.pdf"** and use that for your MyHashTable class implementation. **Do NOT use java's built-in methods (i.e. String.hashCode()).**
* Your rehash method should not take too long to insert and rehash.


**The method printStatistics() will keep track and report the following information for the hash table.** 

1. The number of times you had to expand the table.
1. The load factor in the table (before resizing), trimmed to 2 decimal places.
1. The number of insertions that encountered a collision (before resizing).
1. The length of the longest known collision chain (before resizing).

Note that 2, 3, 4 will need to be reset whenever you expand the table.  


Each time you do a resize and rehash, write the result out to the text file (Specify in the second constructor) in the following format, where  r, c, n are integers and alpha is a floating point number. Append a new set of values before each rehash **(r resizes, load factor alpha, c collisions, n longest chain)**.



**Note**: There are two constructors in the HashTable class in the starter code provided. One takes in the initial size (any value >= 0), while the other constructor takes in the initial size and a filename as well. The boolean `printStats` is set to false by default, and is only changed to true in the constructor that takes in a filename. Each time you expand and rehash your table, you would need to check if this boolean is set to true. If yes, you would write the current statistics to the file before rehash.

	For example, the file will have the following lines:

	0 resizes, load factor 0.62, 0 collisions, 1 longest chain
	1 resizes, load factor 0.66, 3 collisions, 2 longest chain
	2 resizes, load factor 0.66, 7 collisions, 3 longest chain
	3 resizes, load factor 0.66, 17 collisions, 4 longest chain
	4 resizes, load factor 0.67, 24 collisions, 3 longest chain

There are no "correct statistics" and your numbers will change depending on what hash function and initial table size you chose. These values will only indicate how good your chosen hash function is.

**Please note that to receive full credit for your hash table, your printTable() function should be exactly like the format specified.**

Sample output format for printTable():

0:<br>
1: 10<br>
2: 11, 1<br>
3: 12, 2<br>
4: 13, 3<br>
5: 14, 4, 5<br>
6: 15<br>
7: 16, 6<br>
8: 17<br>
9: 18, 7<br>
10: 19<br>
11:<br>
12: 9<br>
13:<br>
14: 8<br>







## Part 3 - Testing
<!-- The testing cases and score distribution below should provide guidance on your own testing.

We have provided some basic test cases in the MyArrayListTester.java starter code but these are different from the Sanity Check. You should use these tests as a guide and also write your own additional unit tests to comprehensively test MyArrayList.

**Your code must first compile in order to receive credit for the different test cases.**

|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity check (1 test per method) | <ul><li>Constructors: instantiate MyArrayList with no arguments. Default lenght is 10 and size is 0</li></ul><ul><li>append: size increments and last value is equal to the new element</li></ul><ul><li>prepend: size increments and the first value is equal to the new element </li></ul><ul><li>insert: insert element at the end</li></ul><ul><li>remove: size decrements by one</li></ul><ul><li>get: index is in bound and returns the correct element</li></ul><ul><li>checkCapacity: required capacity is the current capacity + 1. Size stays the same and capacity doubles</li></ul><ul><li>trimToSize: size stays the same </li></ul>| 19 |
| 2 | Constructors | MyArrayList objects instantiated with the three different constructors should have the correct properties specified. If argument passed into constructor is invalid, throw the correct exception. | 5 |
| 3 | append | Test if an element is correctly inserted at the end of the ArrayList, while preserving existing elements. Size and capacity should be updated to reflect the change to the ArrayList where applicable.<ul><li>MyArrayList is empty</li><li>MyArrayList contains existing elements</li><li>MyArrayList is at capacity</li></ul> | 6 |
| 4 | prepend | Test if an element is correctly inserted at the beginning of the ArrayList, while preserving existing elements. Size and capacity should be updated to reflect the change to the ArrayList where applicable.<ul><li>MyArrayList is empty</li><li>MyArrayList contains existing elements</li><li>MyArrayList is at capacity</li></ul> | 6 |
| 5 | insert | Test if an element is inserted at correct location and if size and capacity are updated appropriately to account for the newly inserted element<ul><li>Insert at index in the range [0, size - 1]</li><li>MyArrayList is at capacity</li><li>Insert out-of-bounds</li></ul> | 15 |
| 6 | get | Test if the value returned is correct. Size and capacity should remain unchanged.<ul><li>Get an element at index in the range [0, size - 1]</li><li>Get an element at an out-of-bounds index</li></ul> | 5 |
| 7 | set | Test if the element is changed correctly. Size and capacity should remain unchanged.<ul><li>Set an element at index in the range [0, size - 1]</li><li>Set an element at an out-of-bounds index</li></ul> | 6 |
| 8 | remove | Test if the correct element is removed. Capacity remains unchanged.<ul><li>Remove an element at index in the range [0, size - 1]</li><li>Remove from an empty list</li></ul> | 15 |
| 9 | size | Test if the correct size is returned. The ArrayList should remain unchanged after call to `size()` | 1 |
| 10 | checkCapacity | Test if capacity is large enough to include an additional element.<ul><li>If it is large enough, capacity remains unchanged.</li><li>If it is not large enough, capacity is updated correctly to be double the previous amount</li><li>If capacity reaches 0, it should be reset to the default of 10</li></ul> | 6 |
| 11 | getCapacity | Test that the correct capacity size is being returned for empty and populated ArrayList. The ArrayList should remain unchanged after call to `getCapacity()` | 1 |
| 12 | trimToSize | Check that the capacity is trimmed to the correct value. <ul><li>empty list</li><li>populated list such that the capacity has not been reached</li><li>populated list such that capacity has been reached</li></ul> | 4 |
| 13 | Student tests | Passing your own JUnit tests in MyArrayListTester.java | 5 |

## Survey
You can find the weekly reflection survey [here](https://forms.gle/EjTHUhRuLjBF1DdP6).   
Please fill out the survey, it will count towards 1 point of your PA2 score. 

## Submission
[//]: # (TODO: Update this to Gradescope submission instructions)

**Turning in your code**

Look through your files and make sure you've included your name, ID, and cs12 account at the top of each  of them. Submit the following files to **Gradescope**:
* MyArrayList.java
* MyArrayListTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit. 

**How your assignment will be evaluated**
* **Coding Style** (5 points)
We will grade your code style thoroughly. Namely, there are a few things you must have in each file / class / method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
* **Weekly Reflection Survey** (1 point)
* **Correctness** (94 points)
    * Does your code compile?
    * Does it pass all of the provided unit tests (MyArrayListTester.java)?
        * We only provided you with a small subset of our tests, you are responsible for making sure that your program runs correctly under all possible situations. -->
    

|   | Test Cases   | Description | Points |
|---|--------------|-------------|--------|
| 1 | Sanity check | <ul><li>Constructors: Create the MyHashTable with size 10. </li></ul><ul><li>insert: Insert a String into the empty hash table.</li></ul><ul><li>delete: Delete a String from a hash table with a single element. </li></ul><ul><li>contains: Search for a String that is the only String in the hash table. </li></ul><ul><li>printTable: Print out a hash table with a single element.</li></ul><ul><li>getSize: Check the number of elements after insert/delete/contains operations. </li></ul><ul><li>rehash: Expand the capacity of a size 10 MyHashTable to size 23 and rehash the elements correctly. </li></ul><ul><li>hash: Hash the String "CSE12 Rocks!" correctly.  </li></ul>| 19 |
| 2 | Constructors | Your constructors should initialize all the instance variables to the correct values. If invalid arguments are passed into the constructors, your constructors should throw the appropriate exceptions. | 5 |
| 3 | insert | Test that a String is correctly added to the hash table while preserving the original elements. Note that to test this, you will have to compute the hash value of the String to check that your String was inserted into the correct index. <ul><li>(Fail to) Insert an invalid String. </li><li>Insert a String that already exists in the hash table.</li><li>Insert a String at an index with no other elements.</li><li>Insert a String at an index with other elements.</li><li>Insert a String that causes the load factor to be exceeded.</li></ul> | 13 |
| 4 | delete | Test that a String is correctly removed from the hash table while preserving all other elements. Note that to test this, you will have to compute the hash value of the String to check that your String was inserted into the correct index. <ul><li>(Fail to) Delete an invalid String.</li><li>Delete a String that doesn't exist in the hash table.</li><li>Delete a String at an index with no other elements.</li><li>Delete a String at an index with other elements.</li></ul> | 13 |
| 5 | contains | Test that a String is correctly said to be contained in the hash table. This should not change the structure of the hash table at all. <ul><li>(Fail to) Locate an invalid String.</li><li>Check for membership of a String that does not exist in the hash table.</li><li>Check for membership of a String that exists in the hash table.</li></ul> | 13 |
| 6 | printTable | Test that the hash table is correctly printed to stdout (print to `System.out`). No need to write JUnit tests for this one, just make sure that you try printing out an index with no elements and index with multiple elements and check that the format matches. | 4 |
| 7 | getSize | Test that this returns the number of elements in the hash table. | 1 |
| 8 | rehash | Test that this method correctly resizes the hashtable and reinserts all the elements correctly. To test this, you may want to loop through all the elements to check them all. This [list of prime numbers](https://en.wikipedia.org/wiki/List_of_prime_numbers#The_first_1000_prime_numbers) should be helpful. | 10 |
| 9 | hashString | Test that your hash function matches whichever one you chose to implement. You might want to write out a few small test cases on paper to verify what you expect the hash value of specific Strings to be. | 10 |
| 10 | Student tests | Write valid JUnit tests in MyHashTableTester.java. This is like in PA2, the JUnit tests you write should pass against our solution code. To ensure that your testers for methods like `insert()` are correct, make sure that, in those testers, you are checking the indices at the hashvalues returned by `hashString()`, not what you calculated by hand. | 0 |

## Surveys
**Weekly Reflection Survey**

You can find the weekly reflection survey [here](https://forms.gle/uzm5HzUetDMv5kbs8).   
Please fill out the survey, it will count towards **1 point** of your PA4 score. 

**CSE 12 - Sp20 Mid-quarter Feedback**

You can find the weekly reflection survey [here](https://forms.gle/CAcU3JKQTvqEKE9m7).
Please take a moment this week to fill out the mid-quarter feedback for this class. This form will be worth **1 point**. This form is entirely **anonymous** and we will trust your words on if you have filled in the form. You should write in your file header of **MyHashTable.java** that "I have completed the mid-quarter feedback" so you can receive credit for PA4. This form is due at the same time as PA4. We hope to have meaningful, constructive feedback so that we can improve the course as we go.


## Submission

**Turning in your code**

Look through your files and make sure you've included your name, ID, and cs12 account at the top of each  of them. Submit the following files to **Gradescope**:
* MyHashTable.java
* MyHashTableTester.java

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. ⚠️ Make sure your code compiles in order to receive partial credit. ⚠️

**How your assignment will be evaluated**
* **Coding Style** (10 points)
We will grade your code style thoroughly. Namely, there are a few things you must have in each file / class / method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full [style guide](https://sites.google.com/view/cse12spr20/style-guide) can be found here. If you need any clarifications, feel free to ask on Piazza.
* **Weekly Reflection Survey** (1 point)
* **Mid-quarter Feedback** (1 point)
* **Correctness** (88 points)
    * Does your code compile? If not, you will get 0 points.
    * Does it pass all of the provided unit tests (MyHashTableTester.java)?
        * We only provided you with a small subset of our tests, you are responsible for making sure that your program runs correctly under all possible situations.
