package application;

public class Student {
	
	private String studentNo;
	private String name;
	private int entryYear;
	private String programmeCode;
	
	
	public Student() {
		this.studentNo = "";
		this.name = "";
		this.entryYear = 0;
		this.programmeCode = "ERROR";
		
	}
	
	public Student(String s, String n, int y, String c) {
	    this.studentNo = s;
	    this.name = n;
	    if (c != null && c.startsWith("TU")) {
	        this.programmeCode = c;
	    } else {
	        System.out.println("Error: Invalid programme code.");
	        this.programmeCode = "ERROR";
	    }
	    
	}

	public String getStudentNo() {
		return this.studentNo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getEntryYear() {
		return this.entryYear;
	}

	public String getProgrammeCode() {
		return this.programmeCode;
	}
	public void setStudentNo(String s) {
		this.studentNo = s;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	//the year of entry to the program is before 2015 or after 2021, the year should be updated to the default
	//0. Error messages should also be displayed
	public void setEntryYear(int y) {
	    if (y >= 2015 && y <= 2021) {
	        this.entryYear = y;
	    } else {
	        System.out.println("Error: Invalid year of entry. Setting to default (0).");
	        this.entryYear = 0;
	    }
	}

	//The constructors should contain error checking so that if the first two characters of the
	//program code are not TU then the program code is updated to ERROR
	public void setProgrammeCode(String c) {
	    if (c != null && c.startsWith("TU")) {
	        this.programmeCode = c;
	    } else {
	        System.out.println("Error: Invalid programme code.");
	        this.programmeCode = "ERROR";
	    }
	}
	
	 public String toString() {
	        return "Student{" +
	                "studentNo='" + studentNo + '\'' +
	                ", name='" + name + '\'' +
	                ", entryYear=" + entryYear +
	                ", programmeCode='" + programmeCode + '\'' +
	                '}';
	}
	
	// Method to check if two students are the same person
    public boolean equals(Student otherStudent) {
        return this.studentNo.equals(otherStudent.getStudentNo());
    }

    // Method to check if the student number is greater than another student
    public boolean isGreater(Student otherStudent) {
        return this.studentNo.compareTo(otherStudent.getStudentNo()) > 0;
    }

    // Method to check if the student number is less than another student
    public boolean isLess(Student otherStudent) {
        return this.studentNo.compareTo(otherStudent.getStudentNo()) < 0;
    }
}


