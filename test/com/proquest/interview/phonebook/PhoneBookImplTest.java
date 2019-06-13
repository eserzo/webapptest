package com.proquest.interview.phonebook;

import org.junit.Test;

public class PhoneBookImplTest {
	@Test
	public void shouldAddFindPerson() throws Exception {
		PhoneBookImpl myBook = new PhoneBookImpl();

		Person myPerson = new Person( "Mary Smith", "(248) 555-1212", "10 Smith Lane, Smithville, 48105" );
		myBook.addPerson(myPerson);

		try {
			myBook.findPerson("Mary", "Smith");
			System.out.println( "We created and found our person, exciting!" );
		} catch( Exception e0 ) {
			e0.printStackTrace();
		}

	}

}
