package com.proquest.interview.phonebook;

import org.junit.Test;
import org.junit.Assert;

public class PhoneBookImplTest {
	@Test
	public void shouldAddFindPerson() throws Exception {
		PhoneBookImpl myBook = new PhoneBookImpl();

		Person myPerson = new Person( "Mary Smith", "(248) 555-1212", "10 Smith Lane, Smithville, 48105" );
		myBook.addPerson(myPerson);

		myBook.findPerson("Mary", "Smith");
	}

}
