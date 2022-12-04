package model;



import java.util.Date;



public class Order {

	

	/*

	 * This class is a representation of the bid table in the database

	 * Each instance variable has a corresponding getter and setter

	 */



	private int id;

    private double transcation_fee;

    private int numShares;

    private long Cus_Acc_Num;

    private Date datetime;

    private String Price_type;

    private String Employee_Id;

    private String buySellType;

    private String customerName;

    private String stocksymbol;

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

    

    public String getCustomerName() {

        return customerName;

    }



    public void setCustomerName(String customerName) {

        this.customerName = customerName;

    }

    

    public String getStockSymbol() {

        return stocksymbol;

    }



    public void setStockSymbol(String stocksymbol) {

        this.stocksymbol = stocksymbol;

    }

    

    public String getEmployee_Id() {

    	return Employee_Id;

    }

    public void setEmployee_Id(String val) {

    	this.Employee_Id=val;

    }



    

    

    public String getPrice_type() {

    	return Price_type;

    }

    public void setPrice_type(String val) {

    	this.Price_type = val;

    }

    

    public long getCustomerID() {

    	return Cus_Acc_Num;

    }

    public void setCustomerID(long value) {

    	this.Cus_Acc_Num = value;

    }



    public double getTranscationFee() {

        return transcation_fee;

    }



    public void setTranscationFee(double transcation_fee) {

        this.transcation_fee = transcation_fee;

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

