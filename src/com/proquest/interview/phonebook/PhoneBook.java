package com.proquest.interview.phonebook;

public interface PhoneBook {
	public Person findPerson(String firstName, String lastName) throws PersonNotFoundException;
	public void addPerson(Person newPerson) throws Exception;
}
