import java.util.*;


public class CourseAL {

	private static final int MIN_COURSE_LIST_SIZE = 0;
	
	private String courseName;
	private ArrayList<Student> roster;
	private ArrayList<Student> waitlist;
	private final int MAX_NUMBER_ROSTER_SIZE;
	private final int MAX_NUMBER_WAITLIST_SIZE;
	
	
	public CourseAL(String courseName, int maxNumberOfAllowedRoster, int maxNumberOfAllowedWaitlist ) {
		 
		
		 if (maxNumberOfAllowedRoster <= MIN_COURSE_LIST_SIZE || maxNumberOfAllowedWaitlist <= MIN_COURSE_LIST_SIZE) {
		      System.out.println("Roster & waitlist size must be greater than " + MIN_COURSE_LIST_SIZE);
		      System.exit(1);
		    }
		
		this.courseName = courseName;
		this.MAX_NUMBER_ROSTER_SIZE = maxNumberOfAllowedRoster;
		this.MAX_NUMBER_WAITLIST_SIZE = maxNumberOfAllowedWaitlist;
		this.roster = new ArrayList<Student>();
		this.waitlist = new ArrayList<Student>();
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public boolean isCourseEmpty() {
		return (roster.isEmpty() && waitlist.isEmpty());
	}
	
	
	// I did not add another getters or setters because there is no need for another for this program's requirements.

		public String toString() {
		
		
			Iterator<Student> iterator = roster.iterator();
			String studentDataRoster;
			if (!iterator.hasNext()) {
				studentDataRoster = "0" ;  // to avoid printing null
			}
			else {
				studentDataRoster = "" ;	// to avoid printing null
			}
			
			while(iterator.hasNext()) {
		
				String tuition;
				Student element = iterator.next()  ;
				if(element.isTuitionPaid())
					tuition = "Tuition Paid";
				else 
					tuition = "Tuition is not Paid";
				studentDataRoster += element.getID() + " || " +element.getName() + " || " + tuition + "\n";
				
		}
		
		
			Iterator<Student> iterator1 = waitlist.iterator();
			String studentDataWaitlist ;
			
			if (!iterator1.hasNext()) {
				studentDataWaitlist = "0" ;  // to avoid printing null
			}
			else {
				studentDataWaitlist = "" ;	// to avoid printing null
			}
			
			while(iterator1.hasNext()) {
				
				String tuition;
				Student element = iterator1.next()  ;
				
				if(element.isTuitionPaid())
					tuition = "Tuition Paid";
				else 
					tuition = "Tuition is not Paid";
				studentDataWaitlist += element.getID() + " || " +element.getName() + " || " + tuition + "\n" ;
				
			}
		
		
		
		
			String s ="The name of the course: " + courseName 
					+ "\n\tThe number of students enrolled in the course and the maximum number that can be enrolled: " 
					+ roster.size() + ", " + MAX_NUMBER_ROSTER_SIZE
					+ "\n\tThe roster of enrolled students: \n" +
					"== ID ||  NAME || TUITION PAID? || ==\n"+ studentDataRoster 
					+ "\n\tThe number of students on the waitlist and the maximum number that can be on the waitlist: "
					+ waitlist.size() + ", " + MAX_NUMBER_WAITLIST_SIZE
					+ "\n\tThe students on the waitlist: \n" 
					+ "== ID ||  NAME || TUITION PAID? || ==\n"+ studentDataWaitlist;
					
			return s;
	}
	
	public boolean addStudent(Student student) {
		
	
		if (student.isTuitionPaid() && !(roster.contains(student) || waitlist.contains(student))) {
			
			if (roster.size() < MAX_NUMBER_ROSTER_SIZE) {
				return	roster.add(student);
		
			}
			
			else if (waitlist.size() < MAX_NUMBER_WAITLIST_SIZE) {
					return 	waitlist.add(student);
				
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
		
			 if ((roster.contains(student))) {
				 roster.remove(student);
				 
				 	if (!waitlist.isEmpty()) {
				 		roster.add(waitlist.get(0));
				 		waitlist.remove(0);
				 	}
				 return true;
			 } 		
			 else if(waitlist.contains(student)) {
				 
				return waitlist.remove(student);
		}
		else {
			return false;
		}
	}
	
	
}
