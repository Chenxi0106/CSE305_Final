package dao;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {

	public Order getDummyTrailingStopOrder() {
		TrailingStopOrder order = new TrailingStopOrder();

		order.setId(1);
		order.setDatetime(new Date());
		order.setNumShares(5);
		order.setPercentage(12.0);
		return order;
	}

	public Order getDummyMarketOrder() {
		MarketOrder order = new MarketOrder();

		order.setId(1);
		order.setDatetime(new Date());
		order.setNumShares(5);
		order.setBuySellType("buy");
		return order;
	}

	public Order getDummyMarketOnCloseOrder() {
		MarketOnCloseOrder order = new MarketOnCloseOrder();

		order.setId(1);
		order.setDatetime(new Date());
		order.setNumShares(5);
		order.setBuySellType("buy");
		return order;
	}

	public Order getDummyHiddenStopOrder() {
		HiddenStopOrder order = new HiddenStopOrder();

		order.setId(1);
		order.setDatetime(new Date());
		order.setNumShares(5);
		order.setPricePerShare(145.0);
		return order;
	}

	public List<Order> getDummyOrders() {
		List<Order> orders = new ArrayList<Order>();

		for (int i = 0; i < 3; i++) {
			orders.add(getDummyTrailingStopOrder());
		}

		for (int i = 0; i < 3; i++) {
			orders.add(getDummyMarketOrder());
		}

		for (int i = 0; i < 3; i++) {
			orders.add(getDummyMarketOnCloseOrder());
		}

		for (int i = 0; i < 3; i++) {
			orders.add(getDummyHiddenStopOrder());
		}

		return orders;
	}

	public String submitOrder(Order order, Customer customer, Employee employee, Stock stock) {

		/*
		 * Student code to place stock order Employee can be null, when the order is
		 * placed directly by Customer
		 */

		/* Sample data begins */
		System.out.println("Start submitOrder Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			System.out.println("successfully connect to database");
			String query = "insert into Orders values(" + order.getId() + "," + '\'' + stock.getSymbol() + '\'' + ","
					+ '\'' + order.getBuySellType() + '\'' + "," + order.getNumShares() + "," + order.getCustomerID()
					+ "," + '\'' + new java.sql.Date(order.getDatetime().getTime()) + '\'' + ","
					+ order.getTranscationFee() + "," + '\'' + order.getPrice_type() + '\'' + "," + 
					(employee==null?"NULL":('\''+employee.getEmployeeID() + '\'')) + "," + order.getPricePerShare() + "," + order.getPercentage()
					+ "," + '\'' + order.getCustomerName() + '\'' + ")";

			System.out.println(query);
			int result = stmt.executeUpdate(query);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failure";
		/* Sample data ends */

	}

	public List<Order> getOrderByStockSymbol(String stockSymbol) {
		/*
		 * Student code to get orders by stock symbol
		 */
		System.out.println("Start getOrderByStockSymbol Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			System.out.println("successfully connect to database");
			String query = "select * from Orders where StockSymbol=" +'\''+stockSymbol+'\'';
			;
			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			List<Order> orders = new ArrayList<Order>();
			while (result.next()) {
				Order order = new Order();
				order.setId(result.getInt("OrderId"));
				order.setStockSymbol(result.getString("StockSymbol"));
				order.setBuySellType(result.getString("OrderType"));
				order.setNumShares(result.getInt("NOF_Shares"));
				order.setCustomerID(result.getLong("Cus_Acc_Num"));
				order.setDatetime(result.getDate("Date_time"));
				order.setTranscationFee(result.getDouble("Transaction_Fee"));
				order.setPrice_type(result.getString("Price_type"));
				order.setEmployee_Id(result.getString("Employee_ID"));
				order.setPricePerShare(result.getDouble("pricePerShare"));
				order.setPercentage(result.getDouble("Percentage"));
				order.setCustomerName(result.getString("Customer_name"));
				orders.add(order);
			}
			return orders;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Order> getOrderByCustomerName(String customerName) {
		/*
		 * Student code to get orders by customer name
		 */
		System.out.println("Start getOrderByCustomerName Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			System.out.println("successfully connect to database");
			String query = "select * from Orders where Customer_name=" +'\''+customerName+'\'';
			;
			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			List<Order> orders = new ArrayList<Order>();
			while (result.next()) {
				Order order = new Order();
				order.setId(result.getInt("OrderId"));
				order.setStockSymbol(result.getString("StockSymbol"));
				order.setBuySellType(result.getString("OrderType"));
				order.setNumShares(result.getInt("NOF_Shares"));
				order.setCustomerID(result.getLong("Cus_Acc_Num"));
				order.setDatetime(result.getDate("Date_time"));
				order.setTranscationFee(result.getDouble("Transaction_Fee"));
				order.setPrice_type(result.getString("Price_type"));
				order.setEmployee_Id(result.getString("Employee_ID"));
				order.setPricePerShare(result.getDouble("pricePerShare"));
				order.setPercentage(result.getDouble("Percentage"));
				order.setCustomerName(result.getString("Customer_name"));
				orders.add(order);
			}
			return orders;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Order> getOrderHistory(String customerId) {
		/*
		 * The students code to fetch data from the database will be written here Show
		 * orders for given customerId
		 */
		System.out.println("Start getOrderHistory Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			System.out.println("successfully connect to database");
			String query = "select * from Orders where Cus_Acc_Num=" + customerId;
			;
			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			List<Order> orders = new ArrayList<Order>();
			while (result.next()) {
				Order order = new Order();
				order.setId(result.getInt("OrderId"));
				order.setStockSymbol(result.getString("StockSymbol"));
				order.setBuySellType(result.getString("OrderType"));
				order.setNumShares(result.getInt("NOF_Shares"));
				order.setCustomerID(result.getLong("Cus_Acc_Num"));
				order.setDatetime(result.getDate("Date_time"));
				order.setTranscationFee(result.getDouble("Transaction_Fee"));
				order.setPrice_type(result.getString("Price_type"));
				order.setEmployee_Id(result.getString("Employee_ID"));
				order.setPricePerShare(result.getDouble("pricePerShare"));
				order.setPercentage(result.getDouble("Percentage"));
				order.setCustomerName(result.getString("Customer_name"));
				orders.add(order);
			}
			return orders;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<OrderPriceEntry> getOrderPriceHistory(String orderId) {

		/*
		 * The students code to fetch data from the database will be written here Query
		 * to view price history of hidden stop order or trailing stop order Use
		 * setPrice to show hidden-stop price and trailing-stop price
		 */

		System.out.println("Start getOrderPriceHistory Function");
		final String DB_URL = "jdbc:mysql://localhost:3306/CSE305";
		final String USER = "root";
		final String PASS = "2002318";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			System.out.println("successfully connect to database");

			String condition = "SUBSTRING(Price_type,1,13)=" + '\'' + "trailing stop" + '\''
					+ " or SUBSTRING(Price_type,1,11)=" + '\'' + "hidden stop" + '\'';

			String query = "select * from Orders where " + condition;
			;
			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			List<OrderPriceEntry> orderPriceHistory = new ArrayList<OrderPriceEntry>();
			while (result.next()) {
				OrderPriceEntry entry = new OrderPriceEntry();
				entry.setOrderId(orderId);
				entry.setDate(result.getDate("Date_time"));
				entry.setStockSymbol(result.getString("StockSymbol"));
				entry.setPricePerShare(result.getDouble("pricePerShare"));
				entry.setPrice(result.getDouble("Transaction_Fee"));
				orderPriceHistory.add(entry);
			}
			return orderPriceHistory;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
