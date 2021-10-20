package code;

public class Human {

	private String name;
	private int age;
	private boolean healthy;
	
	public Human() {
		this.age = 0;
		this.healthy = true;
	}
	
	public Human(String name) {
		this.name = name;
		this.age = 0;
		this.healthy = true;
	}
	public Human(int age, boolean healthy) {
		this.age = age;
		this.healthy = healthy;
	}
	
	public int add(int num1, int num2) {
		int sum = num1 + num2;
		return sum;
	}
	public int add(String String1, String String2) {
		String1.toLowerCase();
		String2.toLowerCase();
		int num1 = 0;
		int num2 = 0;
		if(String1.equals("one")) {
			num1 = 1;
		}else if(String1.equals("two")) {
			num1 = 2;
		}else if(String1.equals("three")) {
			num1 = 3;
		}else if(String1.equals("four")) {
			num1 = 4;
		}else if(String1.equals("five")) {
			num1 = 5;
		}else if(String1.equals("six")) {
			num1 = 6;
		}else if(String1.equals("seven")) {
			num1 = 7;
		}else if(String1.equals("eight")) {
			num1 = 8;
		}else if(String1.equals("nine")) {
			num1 = 9;
		}
		if(String2.equals("one")) {
			num2 = 1;
		}else if(String2.equals("two")) {
			num2 = 2;
		}else if(String2.equals("three")) {
			num2 = 3;
		}else if(String2.equals("four")) {
			num2 = 4;
		}else if(String2.equals("five")) {
			num2 = 5;
		}else if(String2.equals("six")) {
			num2 = 6;
		}else if(String2.equals("seven")) {
			num2 = 7;
		}else if(String2.equals("eight")) {
			num2 = 8;
		}else if(String2.equals("nine")) {
			num2 = 9;
		}
		int sum = num1 + num2;
		return sum;
	}
	public void printVariables() {
		System.out.println(name);
		System.out.println(age);
		System.out.println(healthy);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		if(age > 30) {
			if(Math.random() <= 0.2) {
				return age-5;
			}
		}
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
	
}
