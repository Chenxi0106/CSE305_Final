package model;

import java.util.Date;

public class Order {
	
	/*
	 * This class is a representation of the bid table in the database
	 * Each instance variable has a corresponding getter and setter
	 */

	private int id;
    private Date datetime;
    private int numShares;
    private int Cus_Acc_Num;
    private String Price_type;
    private String Employee_Id;
    private String buySellType;
    private double percentage;
    private double pricePerShare;

    public double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    

    public String getBuySellType() {
        return buySellType;
    }

    public void setBuySellType(String buySellType) {
        this.buySellType = buySellType;
    }
    
    public String getEmployee_Id() {
    	return Employee_Id;
    }
    public void setEmployee_Id(String val) {
    	Employee_Id=val;
    }

    
    
    public String getPrice_type() {
    	return Price_type;
    }
    public void setPrice_type(String val) {
    	Price_type=val;
    }
    
    public int getCus_Acc_Num() {
    	return Cus_Acc_Num;
    }
    public void setCus_Acc_Num(int value) {
    	Cus_Acc_Num=value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }
}
