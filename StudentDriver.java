package application;

import java.util.Scanner;
import generics.LinkedList; 
import java.util.Iterator;

public class StudentDriver {

	LinkedList<Student> list;
	
	public StudentDriver()
    {
    	Scanner scan = new Scanner(System.in);
        list = new LinkedList<Student>();
        inputStudents();
        displayAllStudents();
        scan.nextLine();
        
        removeStudentByNo();
        displayStudents();
        scan.nextLine();
        
        removeStudentByProgram();
        displayRemainingStudents();
    }
	
	//1. allow user to input up to 10 students and add them to the list. The user should first be
	//asked for the number of students (cannot be more than 10) and then asked if they wish
	//to add the students in sorted order of student number or in the order they are input.
	
	public void inputStudents() {
	    Scanner scan = new Scanner(System.in);
	    Student s;
	    String studentNo, name, programmeCode;
	    int entryYear;

	    System.out.print("Enter the number of students (up to 10): ");
	    int numberOfStudents = scan.nextInt();
	    scan.nextLine();

	    if (numberOfStudents > 10) {
	        System.out.println("Number of students cannot exceed 10.");
	        return;
	    }

	    System.out.print("Do you want to add students in sorted order? (yes/no): ");
	    boolean addSorted = scan.nextLine().equalsIgnoreCase("yes");

	    for (int count = 1; count <= numberOfStudents; count++) {
	        System.out.print("\nEnter the Student number " + count + " : ");
	        studentNo = scan.nextLine();
	        System.out.print("Enter the name of Student " + count + " : ");
	        name = scan.nextLine();
	        System.out.print("Enter the year of entry " + count + " : ");
	        entryYear = scan.nextInt();
	        scan.nextLine(); // consume the newline character
	        System.out.print("Enter programme code for the Student " + count + " : ");
	        programmeCode = scan.nextLine();

	        s = new Student(studentNo, name, entryYear, programmeCode);

	        if (addSorted) {
	            addStudentInSortedOrder(s);
	        } else {
	            list.add(s);
	        }
	    }
	}

	//to add the students in sorted order of student number or in the order they are input.
	private void addStudentInSortedOrder(Student newStudent) {
	    Iterator<Student> iterator = list.iterator();
	    int index = 0;

	    while (iterator.hasNext()) {
	        Student currentStudent = iterator.next();
	        if (newStudent.getStudentNo().compareTo(currentStudent.getStudentNo()) < 0) {
	            
	            list.add(newStudent);
	            return;
	        }
	        index++;
	    }
	    list.add(newStudent);
	}
             
	        //2. displays all student details. 
	        public void displayAllStudents() {
	           System.out.println("\nAll Students: ");

	           Iterator<Student> iterator = list.iterator();
	           while (iterator.hasNext()) {
	               Student student = iterator.next();
	           System.out.println(student);
	    }
	}
	        
	        
	        //3. delete a student from a program as specified by their student number. 
	        
	        public void removeStudentByNo() {
	            Scanner scan = new Scanner(System.in);
	            System.out.println("\nDeleting students based on student number");

	            System.out.print("Enter the student number to delete: ");
	            String studentNumberToDelete = scan.nextLine();

	            boolean studentFound = false;
	            LinkedList<Student> newList = new LinkedList<Student>();

	            while (!list.isEmpty()) {
	                Student currentStudent = list.remove();
	                if (currentStudent.getStudentNo().equals(studentNumberToDelete)) {
	                    System.out.println("\n" + currentStudent.getStudentNo() + " has been deleted");
	                    studentFound = true;
	                } else {
	                    newList.add(currentStudent);
	                }
	            }

	            if (!studentFound) {
	                System.out.println("\nStudent with number " + studentNumberToDelete + " not found");
	            }

	            list = newList;
	            scan.close();
	        }
	        //4. display all details of remaining students in the college,
	       
	        public void displayStudents() {
	    	    System.out.println("\nAll Students: ");

	    	    Iterator<Student> iterator = list.iterator();
	    	    while (iterator.hasNext()) {
	    	        Student student = iterator.next();
	    	        System.out.println(student);
	    	    }
	    	}
	    	   
	        
	        // 5. delete all students from a specified program.
	      
	        public void removeStudentByProgram() {
	            try (Scanner scan = new Scanner(System.in)) {
					System.out.print("\nEnter the program code to delete students: ");
					String programToDelete = scan.nextLine();

					LinkedList<Student> newList = new LinkedList<Student>();

					while (!list.isEmpty()) {
					    Student s = list.remove();
					    if (!s.getProgrammeCode().equals(programToDelete)) {
					        newList.add(s);
					    } else {
					        System.out.println("\n" + s.getStudentNo() + " has been deleted");
					    }
					}

					list = newList;
				}
	        }
	        
	        //6. display the remaining students.

	        public void displayRemainingStudents() {
	    	    System.out.println("\nAll Students: ");

	    	    Iterator<Student> iterator = list.iterator();
	    	    while (iterator.hasNext()) {
	    	        Student student = iterator.next();
	    	        System.out.println(student);
	    	    }
	    	}
	    	   

	        public static void main(String[] args) {
	            new StudentDriver();
	        }
}
 
	
	
	
	

