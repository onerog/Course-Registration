import java.util.*;

public class Main {
	
	private static Scanner scan = new Scanner(System.in); 
	
	public static String choice() {

		
		System.out.println("Enter \"1\" to add student, \"2\" to drop student, \"3\" to see class info, \"4\" to exit.   ");
		String answer = scan.nextLine();
		
		while (!(answer.equalsIgnoreCase("1") || answer.equalsIgnoreCase("2") || answer.equalsIgnoreCase("3") || answer.equalsIgnoreCase("4"))) {
			System.out.println("Wrong entry. Please try again.");
			answer = scan.nextLine();
		}
		
		return answer;
		
	
	}
	
	public static boolean choiceBool() {
	
		String answer1 = scan.nextLine();
		
		while (!(answer1.equalsIgnoreCase("y") || answer1.equalsIgnoreCase("n"))) {
			System.out.println("Wrong entry! Please enter \"y\" or \"n\" ");
			answer1 = scan.nextLine();
		} 
				
		
		if (answer1.equalsIgnoreCase("y")) {
		return true;
		}
		else {
			return false;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Course course1 = new Course("Artificial Intelligence", 2, 2);
		String choiceOfUser;

		
		boolean keep = true;
		
		
		do {
			
			choiceOfUser = choice();
				
						if(choiceOfUser.equalsIgnoreCase("1")) {
							
		//					Scanner scan1 = new Scanner(System.in);
							System.out.println("Enter name info.");
							String name = scan.nextLine();
							
							
							System.out.println("Enter  id.");
							String id = scan.nextLine();
							
							
							System.out.println("Enter tuitionpaid info.");
							boolean paid = choiceBool();
							
							Student student = new Student(name, id, paid);
							
							boolean studentAdded = course1.addStudent(student);
							
							if (studentAdded) {
							
								System.out.println("Student " + student.toString()  + " is added succesfully");
								
							}
							else {
								System.out.println("There has been an error while adding the student " + student.toString()  );
							}
								
							keep = true;	
						}
						
						else if (choiceOfUser.equalsIgnoreCase("2")) {
		
							
									if(!course1.isCourseEmpty()) {
									
									System.out.println("Enter name info.");
									String nameToDrop = scan.nextLine();
									
									
									System.out.println("Enter  id.");
									String idToDrop= scan.nextLine();
									
									System.out.println("Enter pay info");
									boolean paid = choiceBool();
									
									Student toDrop = new Student(nameToDrop, idToDrop, paid);
																					
										boolean studentDropped = course1.dropStudent(toDrop);
																		
										if (studentDropped) {
										
											System.out.println("Student " + toDrop.toString()  + " is removed succesfully");
											
										}
										else {
											System.out.println("There has been an error while removing the student " + toDrop.toString() );
										}
																					
								}
									else {
										System.out.println("There is nobody to drop");
									}
									keep = true;
				
						}
						
						else if (choiceOfUser.equalsIgnoreCase("3")) {
							System.out.println(course1 + "\n");
							keep = true;
						}
						else {
							
								keep = false;	
						}
			
			
		} while(keep);
		
		System.out.println("Goodbye");
	}

}

