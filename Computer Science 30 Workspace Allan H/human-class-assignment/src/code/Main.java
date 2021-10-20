package code;

public class Main {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Human allan = new Human("Allan");
		Human ian = new Human("Ian");
		Human karen = new Human(33, true);
		allan.setAge(16);
		ian.setAge(17);
		allan.printVariables();
		ian.printVariables();
		
		checkName(allan);
		birthday(allan);
		System.out.println(allan.getAge());
		System.out.println("Testing add method: ");
		//testing add method
		System.out.println(allan.add(5, 3));
		System.out.println(allan.add("one", "three"));
		
		//test getAge
		System.out.println("getAge Karen test: ");
		for(int i = 0; i < 10; i++) {
			System.out.println(karen.getAge());
		}
	}
	
	public static void checkName(Human h) {
		System.out.println(h.getName());
	}
	
	public static void birthday(Human h) {
		int age = h.getAge();
		age++;
		h.setAge(age);
		System.out.println(h.getAge());
	}

}
