package model;



public class Employee extends Person {

	

	/*

	 * This class is a representation of the employee table in the database

	 * Each instance variable has a corresponding getter and setter

	 */

	

	public String getPassword() {

    	return password;

    }

    public void setPassword(String password) {

    	this.password=password;

    }

    

    public String getEmployeeType() {

    	return employee_type;

    }

    public void setEmployeeType(String employee_type) {

    	this.employee_type=employee_type;

    }

	

	public String getEmployeeID() {

		return employeeID;

	}



	public void setEmployeeID(String employeeID) {

		this.employeeID = employeeID;

	}



	public String getStartDate() {

		return startDate;

	}



	public void setStartDate(String startDate) {

		this.startDate = startDate;

	}



	public float getHourlyRate() {

		return hourlyRate;

	}



	public void setHourlyRate(float hourlyRate) {

		this.hourlyRate = hourlyRate;

	}



	public String getLevel() {

		return level;

	}



	public void setLevel(String level) {

		this.level = level;

	}



	private String employeeID;

	private String startDate;

	private float hourlyRate;

	private String level;

	private String password = null;

	private String employee_type = null;







}
