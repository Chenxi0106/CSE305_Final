package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Location;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */

    public Customer getDummyCustomer() {
        Location location = new Location();
        location.setZipCode(11790);
        location.setCity("Stony Brook");
        location.setState("NY");

        Customer customer = new Customer();
        customer.setId("111-11-1111");
        customer.setAddress("123 Success Street");
        customer.setLastName("Lu");
        customer.setFirstName("Shiyong");
        customer.setEmail("shiyong@cs.sunysb.edu");
        customer.setLocation(location);
        customer.setTelephone("5166328959");
        customer.setCreditCard("1234567812345678");
        customer.setRating(1);

        return customer;
    }
    public List<Customer> getDummyCustomerList() {
        /*Sample data begins*/
        List<Customer> customers = new ArrayList<Customer>();

        for (int i = 0; i < 10; i++) {
            customers.add(getDummyCustomer());
        }
		/*Sample data ends*/

        return customers;
    }

    /**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */
		

		/*
		 * The students code to fetch data from the database based on searchKeyword will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
//		System.out.println("Start to getCustomers function ");
//		return getDummyCustomerList();
        System.out.println("Start getCustomerMailingList Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select * from Customer";
;		    System.out.println(query);
	        ResultSet result=stmt.executeQuery(query);
	        List<Customer> res=new ArrayList<Customer>();
			while (result.next()) {

				Location location = new Location();
				location.setZipCode(result.getInt("ZipCode"));
				location.setCity(result.getString("City"));
				location.setState(result.getString("State"));
				Customer customer = new Customer();
				customer.setId(result.getString("AccountNumber"));
				customer.setAddress(result.getString("Address"));
				customer.setLastName(result.getString("LastName"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setEmail(result.getString("Email"));
				customer.setPassword(result.getString("PassWord"));
				customer.setLocation(location);
				customer.setTelephone(result.getString("Telephone"));
				customer.setCreditCard(result.getString("CreditCardNumber"));
				customer.setRating(result.getInt("Rating"));
				res.add(customer);
			}
			return res;
			
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return null;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		System.out.println("Start to getHighestRevenueCustomer function ");
		return getDummyCustomer();
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		System.out.println("Start getCustomer Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select * from Customer where AccountNumber="+customerID;
	        ResultSet result=stmt.executeQuery(query);
	        while(result.next()) {
	        	Location location = new Location();
		        location.setZipCode(result.getInt("ZipCode"));
		        location.setCity(result.getString("City"));
		        location.setState(result.getString("State"));
		        Customer customer = new Customer();
		        customer.setId(result.getString("AccountNumber"));
		        customer.setAddress(result.getString("Address"));
		        customer.setLastName(result.getString("LastName"));
		        customer.setFirstName(result.getString("FirstName"));
		        customer.setEmail(result.getString("Email"));
		        customer.setPassword(result.getString("PassWord"));
		        customer.setLocation(location);
		        customer.setTelephone(result.getString("Telephone"));
		        customer.setCreditCard(result.getString("CreditCardNumber"));
		        customer.setRating(result.getInt("Rating"));
		        return customer;
	        }
	        
	        


	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return null;
	}
	
	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/
		System.out.println("Start deleteCustomer Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="delete from Customer where AccountNumber="+customerID;
	        System.out.println(query);
	        int result=stmt.executeUpdate(query);
	        return "success";
	        


	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 return "failure";
		
		/*Sample data ends*/
		
	}


	public String getCustomerID(String email) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
		System.out.println("Start getCustomerID Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select AccountNumber from Customer where Email="+email;
	        ResultSet result=stmt.executeQuery(query);
	        while(result.next()) {
	        	return result.getString("AccountNumber");
	        }
	    
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	    return null;
	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
       
        System.out.println("Start addCustomer Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="insert into Customer values("+'\''+customer.getLastName()+'\''+","+'\''+customer.getFirstName()+'\''+","+'\''+customer.getAddress()+'\''+","+'\''+customer.getLocation().getCity()+'\''+","+
	        '\''+customer.getLocation().getState()+'\''+","+customer.getLocation().getZipCode()+","+'\''+customer.getPassword()+'\''+","+customer.getTelephone()+","+'\''+customer.getEmail()+'\''+","+customer.getId()+","+"NULL"+","+customer.getCreditCard()+","+customer.getRating()+","+"NULL"+")";
	        System.out.println(query);
	        int result=stmt.executeUpdate(query);
	        return "success";


	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return "failure";
		
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		  System.out.println("Start editCustomer Function");
			final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
			final String USER = "root";
			final String PASS = "2002318";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		        Statement stmt = conn.createStatement();
		        System.out.println("successfully connect to database");
		        String value="FirstName="+'\''+customer.getFirstName()+'\''+",LastName="+'\''+customer.getLastName()+'\''+",Address="+'\''+customer.getAddress()+'\''+",City="+'\''+customer.getLocation().getCity()+'\''+
		        		",State="+'\''+customer.getLocation().getState()+'\''+",ZipCode="+customer.getLocation().getZipCode()+",Telephone="+customer.getTelephone()+",Email="+'\''+customer.getEmail()+'\''
		        				+ ",AccountNumber="+customer.getId()+",CreditCardNumber="+customer.getCreditCard()+",Rating="+customer.getRating();
		        String query="update Customer set "+value+" where AccountNumber="+customer.getId();
;		        System.out.println(query);
		        int result=stmt.executeUpdate(query);
		        return "success";


		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
			return "failure";
		/*Sample data ends*/

	}

    public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 */


        System.out.println("Start getCustomerMailingList Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select * from Customer";
;		    System.out.println(query);
	        ResultSet result=stmt.executeQuery(query);
	        List<Customer> res=new ArrayList<Customer>();
			while (result.next()) {

				Location location = new Location();
				location.setZipCode(result.getInt("ZipCode"));
				location.setCity(result.getString("City"));
				location.setState(result.getString("State"));
				Customer customer = new Customer();
				customer.setId(result.getString("AccountNumber"));
				customer.setAddress(result.getString("Address"));
				customer.setLastName(result.getString("LastName"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setEmail(result.getString("Email"));
				customer.setPassword(result.getString("PassWord"));
				customer.setLocation(location);
				customer.setTelephone(result.getString("Telephone"));
				customer.setCreditCard(result.getString("CreditCardNumber"));
				customer.setRating(result.getInt("Rating"));
				res.add(customer);
			}
			return res;
			
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return null;

//        return getDummyCustomerList();
    }

    public List<Customer> getAllCustomers() {
        /*
		 * This method fetches returns all customers
		 */
        System.out.println("Start getCustomerMailingList Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select * from Customer";
;		    System.out.println(query);
	        ResultSet result=stmt.executeQuery(query);
	        List<Customer> res=new ArrayList<Customer>();
			while (result.next()) {

				Location location = new Location();
				location.setZipCode(result.getInt("ZipCode"));
				location.setCity(result.getString("City"));
				location.setState(result.getString("State"));
				Customer customer = new Customer();
				customer.setId(result.getString("AccountNumber"));
				customer.setAddress(result.getString("Address"));
				customer.setLastName(result.getString("LastName"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setEmail(result.getString("Email"));
				customer.setPassword(result.getString("PassWord"));
				customer.setLocation(location);
				customer.setTelephone(result.getString("Telephone"));
				customer.setCreditCard(result.getString("CreditCardNumber"));
				customer.setRating(result.getInt("Rating"));
				res.add(customer);
			}
			return res;
			
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return null;
    }
}
