package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

/**
 * This Java class represents an unordered collection of University of Utah students enrolled in CS 2420.
 * 
 * NOTE: The word "Class" in the name of this Java class means a collection of students and should not 
 *       be confused with the Java term class, which is a blueprint for making objects.
 * 
 * @author Erin Parker and Ranbir Singh and Anuvesha Chilwal
 * @version January 20, 2022
 */
public class CS2420ClassGeneric<Type> {

	private ArrayList<CS2420StudentGeneric> studentList;
	
	/**
	 * Creates an empty CS 2420 class.
	 */
	public CS2420ClassGeneric() {
		this.studentList = new ArrayList<CS2420StudentGeneric>();
	}
	
	/**
	 * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
	 * 
	 * @param student - student to be added to this CS 2420 class
	 * @return true if the student was added, 
	 *         false if the student was not added because they already exist in the collection
	 */
	public boolean addStudent(CS2420StudentGeneric student) {
		if(studentList.contains(student)){
			return false;
		} else {
			studentList.add(student); 
			return true; 
		}
	}
	
	/**
	 * Retrieves the CS 2420 student with the given uNID.
	 * 
	 * @param uNID - uNID of student to be retrieved
	 * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
	 */
	public CS2420StudentGeneric lookup(int uNID) {
		// FILL IN -- do not return null, unless appropriate
		for(CS2420StudentGeneric student : studentList){
			if(student.getUNID() == uNID)
				return student; 
		}
		return null;
	}
	
	/**
	 * Retrieves the CS 2420 student(s) with the given contact information.
	 * 
	 * @param contactInfo - contact information of student(s) to be retrieved
	 * @return a list of the CS 2420 student(s) with the given contact information (in any order), 
	 * 	     or an empty list if no such students exist in the collection
	 */
	public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
		// FILL IN -- do not return null
		ArrayList<CS2420StudentGeneric<Type>> l = new ArrayList<>();
		for(CS2420StudentGeneric student : studentList){
			if(student.getContactInfo().equals(contactInfo))
				l.add(student); 
		}

		if(l.size() > 0)
			return l; 
		return l; 

	}
	
	/**
	 * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
	 * 
	 * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
	 *       if no student with the uNID exists in the collection, then this method has no effect.
	 * 
	 * @param uNID - uNID of student whose score is to be added
	 * @param score - the score to be added
	 * @param category - the category in which to add the score
	 */
	public void addScore(int uNID, double score, String category) {
		CS2420StudentGeneric s = lookup(uNID); 
		
		if(s == null){
			return;  
		} else {
			s.addScore(score, category);
		}

		
			
		
	}
	
	/**
	 * Computes the average score of all CS 2420 students in the collection.
	 * 
	 * @return the average score, or 0 if there are no students in the collection
	 */
	public double computeClassAverage() {
		double total = 0; 

		if(studentList.size() < 1){
			return total; 
		}

		for(CS2420StudentGeneric student : studentList){
			total += student.computeFinalScore(); 
		}

		return total / studentList.size();//returns class average 

	}
	
	/**
	 * Creates a list of contact information for all CS 2420 students in the collection.
	 *
	 * @return the duplicate-free list of contact information, in any order
	 */
	public ArrayList<Type> getContactList() {
		ArrayList<Type> emails = new ArrayList<>(); 

		for(CS2420StudentGeneric<Type> student : studentList){
			boolean duplicate = false; 

			for(Type email : emails){
				if(email.equals(student.getContactInfo())){
					duplicate = true; 
				}
			}
			if(!duplicate)
				emails.add(student.getContactInfo()); 
		}
		return emails;
	}


		/**
	 * Returns the list of CS 2420 students in this class, sorted by uNID in ascending order.
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
	    ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
	    for(CS2420StudentGeneric<Type> student : studentList)
	   	 studentListCopy.add(student);

	    sort(studentListCopy, new OrderByUNID());

	    return studentListCopy;
	}

	/**
	 * Returns the list of CS 2420 students in this class, sorted by last name in lexicographical order.
	 * Breaks ties in last names using first names (lexicographical order).
	 * Breaks ties in first names using uNIDs (ascending order).
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
		ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
		for(CS2420StudentGeneric<Type> student : studentList)
	   		studentListCopy.add(student);

		sort(studentListCopy, new OrderByName());  

	    return studentListCopy;
	}

	/**
	 * Returns the list of CS 2420 students in this class with a final score of at least cutoffScore, 
	 * sorted by final score in descending order.  
	 * Breaks ties in final scores using uNIDs (ascending order).
	 * 
	 * @param cutoffScore - value that a student's final score must be at or above to be included
	 *                      in the returned list
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByScore(double cutoffScore) {
		ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
		for(CS2420StudentGeneric<Type> student : studentList)
	   		if(student.computeFinalScore() >= cutoffScore)
			   studentListCopy.add(student);

		
		sort(studentListCopy, new OrderByScore()); 

	    return studentListCopy;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for(int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < list.size(); j++)
				if(c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	

	/**
	 * Comparator that defines an ordering among CS 2420 students using their uNIDs.
	 * uNIDs are guaranteed to be unique, making a tie-breaker unnecessary.
	 */
	protected class OrderByUNID implements Comparator<CS2420StudentGeneric<Type>> {

		/**
		 * Returns a negative value if lhs (left-hand side) is smaller than rhs (right-hand side). 
		 * Returns a positive value if lhs is larger than rhs. 
		 * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
			return lhs.getUNID() - rhs.getUNID();
		}
	}

	/**
	 * Comparator that defines an ordering among CS 2420 students using their scores.
	 * 
	 */
	private class OrderByScore implements Comparator<CS2420StudentGeneric<Type>> {

		/**
		 * Returns a negative value if lhs (left-hand side) is smaller than rhs (right-hand side). 
		 * Returns a positive value if lhs is larger than rhs. 
		 * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
			if (lhs.computeFinalScore() - rhs.computeFinalScore() > 0)
				return -1; 
			else if (lhs.computeFinalScore() - rhs.computeFinalScore() < 0)
				return 1; 
			else 
				return 0; 
		}
	}


	/**
	 * Comparator that defines an ordering among CS 2420 students using their names.
	 * Compares by last name, then first name (if last names are the same), then uNID 
	 * (if both names are the same).  uNIDs are guaranteed to be unique.
	 */
	protected class OrderByName implements Comparator<CS2420StudentGeneric<Type>> {
		public int compare(CS2420StudentGeneric<Type> l, CS2420StudentGeneric<Type> r)
    {
		//Integer obj of uNID
		 Integer leftVal = Integer.valueOf(l.getUNID()); 
		 Integer rightVal = Integer.valueOf(r.getUNID());
		 
		//compare lastname first, then first name, then uNID
		if(l.getLastName().compareTo(r.getLastName()) != 0)
        	return l.getLastName().compareTo(r.getLastName());
		else if (l.getFirstName().compareTo(r.getFirstName()) != 0)
			return l.getFirstName().compareTo(r.getFirstName());
		else 
			return leftVal.compareTo(rightVal);
   	 }
	}
	
	
}
