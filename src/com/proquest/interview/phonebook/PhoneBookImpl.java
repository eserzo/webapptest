package com.proquest.interview.phonebook;

import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.dao.PhoneBookDAO;
import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	private List<Person> people=new ArrayList<Person>();
	
	@Override
	public void addPerson(Person myPerson) throws Exception {
		people.add(myPerson);

		// Save to the database
		boolean status=PhoneBookDAO.createPerson(myPerson);
		if( status ) {
			System.out.println( "Saved " + myPerson );
		} else{
			throw new Exception( "Unable to save " + myPerson );
		}

	}
	
	@Override
	public Person findPerson(String firstName, String lastName) throws PersonNotFoundException {
		// Do we want to have our caching? -- is that the intention of the list
		//for( Person current: people ) {
			// check first name and last
	//	}

		Person person=PhoneBookDAO.retrievePerson(firstName, lastName);

		if( person == null ) {
			throw new PersonNotFoundException( "Unable to find " + firstName + " " + lastName );
		}

		return person;
	}
	
	public static void main(String[] args) {
		// Context: the basic idea is that the phone book lives in ("is persisted to") an
		// SQL database.  For this exercise, we're using a simulated database (that really just
		// lives in memory), but pretend that it is a "real", persisted-on-disk database.
		//
		// But ALL of the data should live in-memory in an instance of
		// the PhoneBookImpl class, AS WELL AS being persisted to the database.
		
		System.out.println( "Step 1 : Creating (two) new Person objects, and put them in both PhoneBook and Database." );
		Person johnSmith = new Person( "John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");

		Person cynthiaSmith = new Person( "Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");

		try {
			PhoneBook myBook = new PhoneBookImpl();
			myBook.addPerson(johnSmith);
			myBook.addPerson(cynthiaSmith);

			System.out.println( "Step 2 : Print the whole phonebook out." );
			for (Person current : ((PhoneBookImpl) myBook).getPeople()) {
				System.out.println(current);
			}
			// TODO 3: Find Cynthia Smith and print just her entry to System.out.
			System.out.println( "Step 3 : Find Cynthia Smith and print just her entry to System.out." );
			Person myPerson=myBook.findPerson("Cynthia", "Smith");
			System.out.println( myPerson );
		} catch( Exception pnfe0 ) {
			System.err.println(pnfe0.toString() );
			pnfe0.printStackTrace();
		}
	}

	public List<Person> getPeople() {
		return people;
	}
}
