package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.RevenueItem;
import model.Stock;

public class StockDao {

    public Stock getDummyStock() {
        Stock stock = new Stock();
        stock.setName("Apple");
        stock.setSymbol("AAPL");
        stock.setPrice(150.0);
        stock.setNumShares(1200);
        stock.setType("Technology");

        return stock;
    }

    public List<Stock> getDummyStocks() {
        List<Stock> stocks = new ArrayList<Stock>();

		/*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            stocks.add(getDummyStock());
        }
		/*Sample data ends*/

        return stocks;
    }

    public List<Stock> getActivelyTradedStocks() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the stocks has to be implemented
		 * Return list of actively traded stocks
		 */

    	System.out.println("Start getActivelyTradedStocks Function");
        final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
        final String USER = "root";
        final String PASS = "2002318";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            System.out.println("successfully connect to database");
            
            String query = "select  * from\n" + 
            		"Stock S, orders O\n" + 
            		"where S.StockSymbol = O.StockSymbol and O.OrderType = " + '\'' + "sell" + '\'';
            
            System.out.println(query);
            ResultSet result=stmt.executeQuery(query);
            List<Stock> stocks = new ArrayList<Stock>();
            while (result.next()) {
            	Stock stock = new Stock();
                stock.setName(result.getString("StockName"));
                stock.setSymbol(result.getString("StockSymbol"));
                stock.setPrice(result.getDouble("SharePrice"));
                stock.setNumShares(result.getInt("NumberofShares"));
                stock.setType(result.getString("StockType"));
                stocks.add(stock);
            }
            return stocks;


         } catch (Exception e) {
            e.printStackTrace();
         }

        return null;

    }

	public List<Stock> getAllStocks() {
	
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Return list of stocks
		 */
		
		System.out.println("Start getAllStocks Function");
        final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
        final String USER = "root";
        final String PASS = "2002318";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            System.out.println("successfully connect to database");
            
            String query = "select * from Stock";
            
            System.out.println(query);
            ResultSet result=stmt.executeQuery(query);
            List<Stock> stocks = new ArrayList<Stock>();
            while (result.next()) {
            	Stock stock = new Stock();
                stock.setName(result.getString("StockName"));
                stock.setSymbol(result.getString("StockSymbol"));
                stock.setPrice(result.getDouble("SharePrice"));
                stock.setNumShares(result.getInt("NumberofShares"));
                stock.setType(result.getString("StockType"));
                stocks.add(stock);
            }            
            
            return stocks;


         } catch (Exception e) {
            e.printStackTrace();
         }

        return null;

	}

    public Stock getStockBySymbol(String stockSymbol)
    {
        /*
		 * The students code to fetch data from the database will be written here
		 * Return stock matching symbol
		 */
    	System.out.println("Start getStockBySymbol Function");
        final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
        final String USER = "root";
        final String PASS = "2002318";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            System.out.println("successfully connect to database");
            
            String query = "select * from Stock where StockSymbol = " + '\'' + stockSymbol + '\'';
            
            System.out.println(query);
            ResultSet result=stmt.executeQuery(query);
            while (result.next()) {
            	Stock stock = new Stock();
                stock.setName(result.getString("StockName"));
                stock.setSymbol(result.getString("StockSymbol"));
                stock.setPrice(result.getDouble("SharePrice"));
                stock.setNumShares(result.getInt("NumberofShares"));
                stock.setType(result.getString("StockType"));
                return stock;
            }


         } catch (Exception e) {
            e.printStackTrace();
         }

        return null;
    }

    public String setStockPrice(String stockSymbol, double stockPrice) {
        /*
         * The students code to fetch data from the database will be written here
         * Perform price update of the stock symbol
         */

    	System.out.println("Start setStockPrice Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        
	        String content = "SharePrice = " + '\'' + stockPrice + '\'';
	        
	        String query = "update Stock set " + content + " where StockSymbol = " + '\'' + stockSymbol + '\'';
	        System.out.println(query);
	        int result = stmt.executeUpdate(query);
	        return "success";
	        
	        
	        
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 return "failure";
    }
	
	public List<Stock> getOverallBestsellers() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Get list of bestseller stocks
		 */

		System.out.println("Start getOverallBestsellers Function");
        final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
        final String USER = "root";
        final String PASS = "2002318";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            System.out.println("successfully connect to database");
            
            String query = "select * from Stock S,Orders O, Customer C\n" + 
            		"where O.Cus_Acc_Num = C.AccountNumber and C.Rating > 0.75 and S.StockSymbol=O.StockSymbol;";
            
            System.out.println(query);
            ResultSet result=stmt.executeQuery(query);
            List<Stock> stocks = new ArrayList<Stock>();
            while (result.next()) {
            	Stock stock = new Stock();
                stock.setName(result.getString("StockName"));
                stock.setSymbol(result.getString("StockSymbol"));
                stock.setPrice(result.getDouble("SharePrice"));
                stock.setNumShares(result.getInt("NumberofShares"));
                stock.setType(result.getString("StockType"));
                stocks.add(stock);
            }
            return stocks;


         } catch (Exception e) {
            e.printStackTrace();
         }

        return null;

	}

<<<<<<< HEAD
	public List<Stock> getCustomerBestsellers(String customerID) {
=======
 public List<Stock> getCustomerBestsellers(String customerID) {
>>>>>>> c2837a19fe010bd9323d039cd8ed8c7ae9311e53

		/*
		 * The students code to fetch data from the database will be written here.
		 * Get list of customer bestseller stocks
		 */
 		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";   
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       			Statement stmt = conn.createStatement();		
			System.out.println("successfully connect to database");	
			String query="select distinct * from Stock where StockSymbol in (select StockSymbol from Orders where Cus_Acc_Num="+customerID+") order by SharePrice desc;";
	       		ResultSet result=stmt.executeQuery(query);
			List<Stock> res=new ArrayList<Stock>();
			while (result.next()){
				Stock stock = new Stock();
		        	stock.setName(result.getString("StockName"));
		        	stock.setSymbol(result.getString("StockSymbol"));
		        	stock.setPrice(result.getInt("SharePrice"));
		        	stock.setNumShares(result.getInt("NumberofShares"));
		        	stock.setType(result.getString("StockType"));
		        	res.add(stock);	

			}
			return res;
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		

		return null;
	    
    }

	public List getStocksByCustomer(String customerId) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Get stockHoldings of customer with customerId
		 */
		
 		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";   
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       			Statement stmt = conn.createStatement();		
			System.out.println("successfully connect to database");	
			String query="select distinct * from Stock where StockSymbol in (select StockSymbol from Orders where Cus_Acc_Num="+customerId+");";
	       		ResultSet result=stmt.executeQuery(query);
			List<Stock> res=new ArrayList<Stock>();
			while (result.next()){
				Stock stock = new Stock();
		        	stock.setName(result.getString("StockName"));
		        	stock.setSymbol(result.getString("StockSymbol"));
		        	stock.setPrice(result.getInt("SharePrice"));
		        	stock.setNumShares(result.getInt("NumberofShares"));
		        	stock.setType(result.getString("StockType"));
		        	res.add(stock);	

			}
			return res;
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		

		return null;
	}

    public List<Stock> getStocksByName(String name) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Return list of stocks matching "name"
		 */
	    
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";   
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       			Statement stmt = conn.createStatement();		
			System.out.println("successfully connect to database");	
			String query="select * from Stock where stockName="+name;
	       		ResultSet result=stmt.executeQuery(query);
			List<Stock> res=new ArrayList<Stock>();
			while (result.next()){
				Stock stock = new Stock();
		        	stock.setName(result.getString("StockName"));
		        	stock.setSymbol(result.getString("StockSymbol"));
		        	stock.setPrice(result.getInt("SharePrice"));
		        	stock.setNumShares(result.getInt("NumberofShares"));
		        	stock.setType(result.getString("StockType"));
		        	res.add(stock);	

			}
			return res;
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		

		return null;
    }

    public List<Stock> getStockSuggestions(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Return stock suggestions for given "customerId"
		 */
    	List<Stock> res=new ArrayList<Stock>();
        System.out.println("Start getStockSuggestions Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        Statement stmt = conn.createStatement();
	        System.out.println("successfully connect to database");
	        String query="select * from Stock S, Orders O where O.Cus_Acc_Num="+customerID+" and O.StockSymbol=S.StockSymbol";
;		    System.out.println(query);
	        ResultSet result=stmt.executeQuery(query);
			while (result.next()) {
				Stock stock = new Stock();
		        stock.setName(result.getString("StockName"));
		        stock.setSymbol(result.getString("StockSymbol"));
		        stock.setPrice(result.getInt("SharePrice"));
		        stock.setNumShares(result.getInt("NumberofShares"));
		        stock.setType(result.getString("StockType"));
		        res.add(stock);
				
			}
			return res;
			
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return null;

    }

    public List<Stock> getStockPriceHistory(String stockSymbol) {

		/*
		 * The students code to fetch data from the database
		 * Return list of stock objects, showing price history
		 */

    	final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";   
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       		Statement stmt = conn.createStatement();		
			System.out.println("successfully connect to database");	
			String query="select * from Stock where stockSymbol="+stockSymbol;
	       	ResultSet result=stmt.executeQuery(query);
			List<Stock> res=new ArrayList<Stock>();
			while (result.next()){
				Stock stock = new Stock();
		        	stock.setName(result.getString("StockName"));
		        	stock.setSymbol(result.getString("StockSymbol"));
		        	stock.setPrice(result.getInt("SharePrice"));
		        	stock.setNumShares(result.getInt("NumberofShares"));
		        	stock.setType(result.getString("StockType"));
		        	res.add(stock);	

			}
			return res;
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		

		return null;     
	}

    public List<String> getStockTypes() {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Populate types with stock types
		 */

        List<String> types = new ArrayList<String>();
        types.add("technology");
        types.add("finance");
        return types;

    }

    public List<Stock> getStockByType(String stockType) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Return list of stocks of type "stockType"
		 */

		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";   
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       		Statement stmt = conn.createStatement();		
			System.out.println("successfully connect to database");	
			String query="select * from Stock where StockType="+stockType;
	       	ResultSet result=stmt.executeQuery(query);
			List<Stock> res=new ArrayList<Stock>();
			while (result.next()){
				Stock stock = new Stock();
		        	stock.setName(result.getString("StockName"));
		        	stock.setSymbol(result.getString("StockSymbol"));
		        	stock.setPrice(result.getInt("SharePrice"));
		        	stock.setNumShares(result.getInt("NumberofShares"));
		        	stock.setType(result.getString("StockType"));
		        	res.add(stock);	

			}
			return res;
		
		}catch(Exception e){
			e.printStackTrace();	
		}
		

		return null;    
    }
}