package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Location;

public class EmployeeDao {
/*
* This class handles all the database operations related to the employee table
*/

    public Employee getDummyEmployee()
    {
        Employee employee = new Employee();

        Location location = new Location();
        location.setCity("Stony Brook");
        location.setState("NY");
        location.setZipCode(11790);

/*Sample data begins*/
        employee.setEmail("shiyong@cs.sunysb.edu");
        employee.setFirstName("Shiyong");
        employee.setLastName("Lu");
        employee.setLocation(location);
        employee.setAddress("123 Success Street");
        employee.setStartDate("2006-10-17");
        employee.setTelephone("5166328959");
        employee.setEmployeeID("631-413-5555");
        employee.setHourlyRate(100);
/*Sample data ends*/

        return employee;
    }

    public List<Employee> getDummyEmployees()
    {
       List<Employee> employees = new ArrayList<Employee>();

        for(int i = 0; i < 10; i++)
        {
            employees.add(getDummyEmployee());
        }

        return employees;
    }

public String addEmployee(Employee employee) {
/*
* All the values of the add employee form are encapsulated in the employee object.
* These can be accessed by getter methods (see Employee class in model package).
* e.g. firstName can be accessed by employee.getFirstName() method.
* The sample code returns "success" by default.
* You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
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
       
       String content = '\'' + employee.getEmail() + '\'' + "," + '\'' + employee.getEmployeeID() + '\'' + "," + '\'' + employee.getLastName()+'\'' + "," + '\'' + employee.getFirstName() + '\'' + "," + '\'' + employee.getAddress() + '\'' + "," + '\'' + employee.getLocation().getCity() + '\'' + "," + '\'' + employee.getLocation().getState() + '\'' + "," +
        employee.getLocation().getZipCode() + "," + '\'' + employee.getPassword() + '\'' + "," + '\'' + employee.getEmployeeType() + '\'' + "," + employee.getTelephone() + "," + '\'' + employee.getStartDate() + '\'' + "," + employee.getHourlyRate();
       
       
       String query = "insert into Employee values(" + content + ")";
    	 System.out.println(query);
       int result=stmt.executeUpdate(query);
       return "success";


       
     } catch (Exception e) {
        e.printStackTrace();
     }
return "failure";
/*Sample data ends*/

}

public String editEmployee(Employee employee) {
/*
* All the values of the edit employee form are encapsulated in the employee object.
* These can be accessed by getter methods (see Employee class in model package).
* e.g. firstName can be accessed by employee.getFirstName() method.
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
       String value="Email=" + '\''+employee.getEmail() + '\'' + ",SSN=" + '\'' + employee.getEmployeeID() + '\'' + ",LastName=" + '\'' + employee.getLastName() + '\'' + ",FirstName=" + '\'' + employee.getFirstName() + '\'' + ",Address=" + '\'' + employee.getAddress() + '\'' + ",City=" + '\'' + employee.getLocation().getCity() + '\'' +
        ",State=" + '\''+employee.getLocation().getState() + '\'' + ",ZipCode=" + employee.getLocation().getZipCode() + ",EmployeeType=" + '\'' + employee.getEmployeeType() + '\'' + ",Telephone=" + employee.getTelephone() + ",StartDate="+'\'' + employee.getStartDate() + '\''
          + ",HourlyRate=" + employee.getHourlyRate();
       String query="update Employee set " + value + " where SSN=" + employee.getEmployeeID();
;    System.out.println(query);
       int result=stmt.executeUpdate(query);
       return "success";


       
     } catch (Exception e) {
        e.printStackTrace();
     }
return "failure";
/*Sample data ends*/

}

public String deleteEmployee(String employeeID) {
/*
* employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
* The sample code returns "success" by default.
* You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
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
       String query = "delete from Employee where SSN=" + employeeID;
       System.out.println(query);
       int result = stmt.executeUpdate(query);
       return "success";
       


       
     } catch (Exception e) {
        e.printStackTrace();
     }
return "failure";
/*Sample data ends*/

}


public List<Employee> getEmployees() {

/*
* The students code to fetch data from the database will be written here
* Query to return details about all the employees must be implemented
* Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
*/

System.out.println("Start getEmployees Function");
final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
final String USER = "root";
final String PASS = "2002318";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       Statement stmt = conn.createStatement();
       System.out.println("successfully connect to database");
       String query="select * from Employee";
;    System.out.println(query);
       ResultSet result=stmt.executeQuery(query);
       List<Employee> employees = new ArrayList<Employee>();
while (result.next()) {

	 Location location = new Location();
       location.setZipCode(result.getInt("ZipCode"));
       location.setCity(result.getString("City"));
       location.setState(result.getString("State"));
       Employee employee = new Employee();
       employee.setLocation(location);
       employee.setEmail(result.getString("Email"));
       employee.setEmployeeID(result.getString("SSN"));
       employee.setAddress(result.getString("Address"));
       employee.setLastName(result.getString("LastName"));
       employee.setFirstName(result.getString("FirstName"));
       employee.setPassword(result.getString("PassWord"));
       employee.setEmployeeType(result.getString("EmployeeType"));
       employee.setTelephone(result.getString("Telephone"));
       employee.setStartDate(result.getString("StartDate"));
       employee.setHourlyRate(result.getFloat("HourlyRate"));
employees.add(employee);
}
return employees;

       
     } catch (Exception e) {
        e.printStackTrace();
     }

return null;

}

public Employee getEmployee(String employeeID) {

/*
* The students code to fetch data from the database based on "employeeID" will be written here
* employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
* The record is required to be encapsulated as a "Employee" class object
*/

System.out.println("Start getEmployee Function");
final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
final String USER = "root";
final String PASS = "2002318";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       Statement stmt = conn.createStatement();
       System.out.println("successfully connect to database");
       String query="select * from Employee where SSN=" + '\''+employeeID+'\'';
       System.out.println(query);
       ResultSet result = stmt.executeQuery(query);
       while(result.next()) {
        Location location = new Location();
       location.setZipCode(result.getInt("ZipCode"));
       location.setCity(result.getString("City"));
       location.setState(result.getString("State"));
       Employee employee = new Employee();
       employee.setLocation(location);
       employee.setEmail(result.getString("Email"));
       employee.setEmployeeID(result.getString("SSN"));
       employee.setAddress(result.getString("Address"));
       employee.setLastName(result.getString("LastName"));
       employee.setFirstName(result.getString("FirstName"));
       employee.setPassword(result.getString("PassWord"));
       employee.setEmployeeType(result.getString("EmployeeType"));
       employee.setTelephone(result.getString("Telephone"));
       employee.setStartDate(result.getString("StartDate"));
       employee.setHourlyRate(result.getFloat("HourlyRate"));
       return employee;
       }
       
       


       
     } catch (Exception e) {
        e.printStackTrace();
     }
return null;
}

public Employee getHighestRevenueEmployee() {
    System.out.println("Start getHighestRevenueEmployee Function");
    final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
    final String USER = "root";
    final String PASS = "2002318";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        System.out.println("successfully connect to database");

        String query = "SELECT * \r\n"
         + "FROM Employee\r\n"
         + "WHERE SSN = ( \r\n"
         + "    SELECT Employee_ID\r\n"
         + "              FROM Orders\r\n"
         + "              GROUP BY Employee_ID\r\n"
         + "              ORDER BY sum(Transaction_Fee) desc\r\n"
         + "              LIMIT 1\r\n"
         + ")";
        System.out.println(query);

        ResultSet result = stmt.executeQuery(query);
        while(result.next()){
           Location location = new Location();
           location.setZipCode(result.getInt("ZipCode"));
           location.setCity(result.getString("City"));
           location.setState(result.getString("State"));
           Employee employee = new Employee();
           employee.setLocation(location);
           employee.setEmail(result.getString("Email"));
           employee.setEmployeeID(result.getString("SSN"));
           employee.setAddress(result.getString("Address"));
           employee.setLastName(result.getString("LastName"));
           employee.setFirstName(result.getString("FirstName"));
           employee.setPassword(result.getString("PassWord"));
           employee.setEmployeeType(result.getString("EmployeeType"));
           employee.setTelephone(result.getString("Telephone"));
           employee.setStartDate(result.getString("StartDate"));
           employee.setHourlyRate(result.getFloat("HourlyRate"));
           return employee;
       }


         } catch (Exception e) {
            e.printStackTrace();
         }

    return null;
}

public String getEmployeeID(String username) {
/*
* The students code to fetch data from the database based on "username" will be written here
* username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
* The Employee ID is required to be returned as a String
*/

System.out.println("Start getEmployeeID Function");
final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
final String USER = "root";
final String PASS = "2002318";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	Statement stmt = conn.createStatement();
	System.out.println("successfully connect to database");

	String query = "select * from Employee where Email=" + '\'' + username + '\'';
	System.out.println(query);
	ResultSet result = stmt.executeQuery(query);
	while(result.next()) {
		return result.getString("SSN");
	}

} catch (Exception e) {
	e.printStackTrace();
}
return null;
}

}
