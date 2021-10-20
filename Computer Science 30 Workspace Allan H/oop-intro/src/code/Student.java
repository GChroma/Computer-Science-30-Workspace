package code;

public class Student {
	//CLASS ATTRIBUTES
	private String firstName;
	private String lastName;
	private int age;
	private int grade;
	private double average;
	private int studentID;
	private boolean enrolled = false;
	
	//CONSTRUCTOR: The special "method" used to initialize the object
	
	public Student() {//default constructor
		firstName = "John";
		lastName = "Doe";
		grade = 10;
		average = 0;
		enrolled = false;
		age = 15;
	}
	public Student(String firstName, int grade) {
		this.firstName = firstName;
		lastName = "Doe";
		this.grade = grade;
		average = 0;
		enrolled = false;
		age = 15;
	}
	
	//ACCESS MODIFIERS
	/* public - by everyone
	 * private - by only itself
	 * protected - by itself or any other object of the same type
	 */
	
	//if you dont assign a modifier it's automatically public
	
	
	//ACCESSORS AND MODIFIERS (GETTERS AND SETTERS)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setAge(int age) {
		if(age >= 0) {
			this.age = age;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	
}
