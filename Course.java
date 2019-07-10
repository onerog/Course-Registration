import java.util.*;

public class Course {

	private static final int MIN_COURSE_LIST_SIZE = 0;
	
	private String courseName;
	private Student[] roster;
	private Student[] waitlist;
	private final int ROSTER_SIZE;
	private final int WAITLIST_SIZE;
	
	public Course(String courseName, int rosterSize, int waitlistSize) {
		 if (rosterSize <= MIN_COURSE_LIST_SIZE || waitlistSize <= MIN_COURSE_LIST_SIZE) {
		      System.out.println("Roster & waitlist size must be greater than " + MIN_COURSE_LIST_SIZE);
		      System.exit(1);
		    }
		 this.courseName = courseName;
		 this.ROSTER_SIZE = rosterSize;
		 this.WAITLIST_SIZE = waitlistSize;
		 this.roster = new Student[ROSTER_SIZE];
		 this.waitlist = new Student[WAITLIST_SIZE];
		 
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getRosterSize() {
		return ROSTER_SIZE;
	}
	
	public int getWaitlistSize() {
		return WAITLIST_SIZE;
	}
	


	private int nullCounter(Student[] s) {
		int counter = 0;
		for (int i= 0; i< s.length ; i++ ) {
			if (s[i] == null) {
				counter++;
			}
			
		}
		return counter;
	}
	
	public boolean contain(Student[] s, Student toAdd) {
		
			for (int i = 0; i < s.length; i++) {
				if (s[i] != null && toAdd != null) {
					if (s[i].getID().equalsIgnoreCase(toAdd.getID())) {
						return true;
					}
				}				
			}
			return false;
	}
	
	private int getIndex(Student[] s, Student toFindIndex) {
		

			int index = 0;
			for (int i = 0; i < s.length; i++) {
				if (s[i] != null && toFindIndex != null) {
					if (s[i].getID().equalsIgnoreCase(toFindIndex.getID())) {
						index = i;
						return index;
					}
				}				
			}
			System.out.println("This student is not in a list!");
			return -1;
	}
	
	private int getNumberOfStudentsEnrolled() {
		int numberOfStudentsEnrolled = (getRosterSize() - nullCounter(this.roster) );
		return numberOfStudentsEnrolled;
	}
	
	private int getNumberOfStudentsWaitlist() {
		int numberOfStudentsWaitlist = (getWaitlistSize() - nullCounter(this.waitlist));
		return numberOfStudentsWaitlist;
	}
	
	public boolean isCourseEmpty() {

		return (getNumberOfStudentsEnrolled() == 0 && getNumberOfStudentsWaitlist() == 0);
	}
				
	public String toString() {
		
		
		String rosterData = "";
		String waitlistData = "";
		if (!(nullCounter(this.roster) == getRosterSize())) {
			for (int i = 0; i < getRosterSize(); i++) {
				if (this.roster[i] != null) {
					String tuition ;
					if(roster[i].isTuitionPaid()) {
						tuition = "Tuition Paid";
					}
					else {
						tuition = "Tuition is not Paid";
					}
					
					rosterData += roster[i].getID() + " || " +roster[i].getName() + " || " + tuition + "\n";
				}
			}
		}
		else
			rosterData = "Roster is empty!";
		
		if (!(nullCounter(this.waitlist) == getWaitlistSize())) {
			for (int i = 0; i < getWaitlistSize(); i++) {
				if (this.waitlist[i] != null) {
					String tuition ;
					if(waitlist[i].isTuitionPaid()) {
						tuition = "Tuition Paid";
					}
					else {
						tuition = "Tuition is not Paid";
					}
					
					waitlistData += waitlist[i].getID() + " || " +waitlist[i].getName() + " || " + tuition + "\n";
				}
			}
		}
		else
			waitlistData = "Waitlist is empty!";
		
		String s ="The name of the course: " + courseName 
				+ "\n\tThe number of students enrolled in the course and the maximum number that can be enrolled: " 
				+ getNumberOfStudentsEnrolled() + ", " + getRosterSize()
				+ "\n\tThe roster of enrolled students: \n" +
				"== ID ||  NAME || TUITION PAID? || ==\n"+ rosterData 
				+ "\n\tThe number of students on the waitlist and the maximum number that can be on the waitlist: "
				+ getNumberOfStudentsWaitlist() + ", " + getWaitlistSize()
				+ "\n\tThe students on the waitlist: \n" 
				+ "== ID ||  NAME || TUITION PAID? || ==\n"+ waitlistData;
				
		return s;
	
	}
	
	public boolean addStudent(Student student) {
		
		
		if (student.isTuitionPaid() && !(contain(roster, student) || contain(waitlist, student))) {
			
			if (getNumberOfStudentsEnrolled() < getRosterSize()) {
				roster[getNumberOfStudentsEnrolled()] = student;
				
				return true;
			}
			
			else if (getNumberOfStudentsWaitlist() < getWaitlistSize()) {
				waitlist[getNumberOfStudentsWaitlist()] = student;
				
				return true;
			}
			
			else {
				System.out.println("There are no more space in roster or waitlist!");
				return false;
			}
		}
		else { 
			if (!student.isTuitionPaid()) {
				System.out.println("You need to pay the tuition first to register for classses!");
				return false;
			}
			
			else {
				System.out.println("Another student with this ID already exists in the system!");
				return false;
			}
		}
	}
	
	public boolean dropStudent(Student student) {
				
			 if (contain(roster, student)) {
				 int index = getIndex(roster, student);
				 roster[index] = null;
				 
				 	if (getNumberOfStudentsWaitlist() != 0) {
				 		
				 		roster[index] = waitlist[0];
				 		Student[] newWaitlist = new Student[getWaitlistSize()];
				 		
				 			for (int i = 0; i+1 < getWaitlistSize() ; i++ ) {
				 				
				 					newWaitlist[i] = waitlist[i+1];
				 			}
				 			
				 			for (int i = 0; i < getWaitlistSize(); i++) {
				 				
				 				waitlist[i] = newWaitlist[i];
				 			}
				 	}
				 return true;
			 } 		
			 else if(contain(waitlist, student)) {
				 int index = getIndex(waitlist, student);
				 waitlist[index] = null;
				 Student[] newWaitlist = new Student[getWaitlistSize()];
				 int newWaitlistIndex = 0;
				 for(int i = 0; i < getWaitlistSize(); i++ ) {
					 if (waitlist[i] != null) {
						 
						 newWaitlist[newWaitlistIndex] = waitlist[i];
						 newWaitlistIndex++;
					 }
				 }
				 
				 for (int i = 0; i < getWaitlistSize(); i++) {
		 				
		 				waitlist[i] = newWaitlist[i];
		 			}
				 
				 return true;
			 }
			 else {
				 return false;
			 }
	}
	
	
	
	
}
