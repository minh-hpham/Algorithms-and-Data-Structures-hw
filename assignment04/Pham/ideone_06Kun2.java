package assignment04.Pham;

import java.util.Comparator;

class Student {
	String firstName;
	String lastName;
	public String toString(){ return firstName + " " + lastName; }
	public class OrderStudentByName implements Comparator<Student>
	{
		@Override
		public int compare(Student arg0, Student arg1)
		{
			if (arg0 == arg1)
				return 0;
			int result = arg0.lastName.compareTo(arg1.lastName);
			if (result == 0)
				result = arg0.firstName.compareTo(arg1.firstName);
			return result;
		}
	}
}

class CollegeStudent extends Student{
	String major;
	public String toString(){ return super.toString() + ", " + major; }
}

class TestStudents {
	public static void main(String[] args){
		CollegeStudent s1 = new CollegeStudent();
		s1.firstName = "John";
		s1.lastName = "Doe";
		s1.major = "math";
		CollegeStudent s2 = s1;
		s2.firstName = "Jane";
		s2.major = "computer science";
		System.out.println(s1.toString());
		System.out.println(s2.toString());
	}
	
}
