package code;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student Adam;
		Student[] Students;
		Adam = new Student();
		Student Max = new Student();
		
		//Max = Adam; Max now refers to Adam. Previous Max is an orphan and destroyed.
		
		Student Allan = new Student("Allan", 12);
		Adam.setFirstName("Adam");
		System.out.println(Adam.getFirstName());
		System.out.println(Allan.getFirstName());
		System.out.println(Allan.getGrade());
		
		//static = unchanging location in memory. The spot is assigned right away.
		
	}

}
