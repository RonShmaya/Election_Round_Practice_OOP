package Exseptions;

import java.time.LocalDate;

import Kind_Of_Persons.Person;

public class AgeException extends Exception {
	private int age;

	public AgeException(int age) {
		super("Age must be 18 and more,It's between ---> " + Person.MIN_BIRTHDAY_INPUT + " to "
				+ (LocalDate.now().getYear() - 18) + ", the age " + age + " cant vote");

	}
}
