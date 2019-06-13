package com.proquest.interview.dao;

import com.proquest.interview.phonebook.Person;
import com.proquest.interview.phonebook.PersonNotFoundException;
import com.proquest.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhoneBookDAO {
    /**
     * Save the person to the database
     *
     * @param myPerson
     * @return
     */
    public static boolean createPerson( Person myPerson ) {
        Connection myConn=null;

        try {
            myConn=DatabaseUtil.getInstance().getConnection();

            String sql="insert into phonebook( name, phonenumber, address ) values ( ?, ?, ?) ";
            PreparedStatement stmt = myConn.prepareStatement(sql);
            stmt.setString(1, myPerson.getName() );
            stmt.setString( 2, myPerson.getPhoneNumber());
            stmt.setString( 3, myPerson.getAddress());

            int results=stmt.executeUpdate();
            if( results != 1 ) return false;
        } catch( Exception e0 ) {
            e0.printStackTrace();
        } finally {
            if( myConn != null ) {
                try {
                    myConn.close();
                } catch( Exception e0 ) { }
            }
        }

        return true;
    }

    /**
     * Retrieve the person from the database
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public static Person retrievePerson( String firstName, String lastName ) throws PersonNotFoundException {
        Connection myConn=null;

        Person myPerson=null;

        try {
            myConn=DatabaseUtil.getInstance().getConnection();

            String sql="select name, phonenumber, address from phonebook where name=?";
            PreparedStatement stmt = myConn.prepareStatement(sql);
            stmt.setString( 1, firstName + " " + lastName );

            ResultSet rs=stmt.executeQuery();
            if( rs.next()) {
                myPerson = new Person( rs.getString("name"), rs.getString( "phonenumber" ), rs.getString( "address" ));
            } else {
                throw new PersonNotFoundException( "Unable to find " + firstName + " " + lastName );
            }
        } catch( PersonNotFoundException pnfe0 ) {
            throw pnfe0;
        }
        catch( Exception e0 ) {
            e0.printStackTrace();
        } finally {
            if( myConn != null ) {
                try {
                    myConn.close();
                } catch( Exception e0 ) { }
            }
        }
        return myPerson;
    }

}
