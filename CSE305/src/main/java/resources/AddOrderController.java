package resources;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.OrderDao;
import dao.StockDao;
import model.*;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddOrderController
 */
public class AddOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String employeeId = null;
        Employee employee = null;
        String customerId = request.getParameter("customerId");

        // submitted by customer
        if (customerId == null) {
            customerId = (String) request.getSession(false).getAttribute("customerID");
        }
        else
        {
            EmployeeDao employeeDao = new EmployeeDao();
            employeeId = (String) request.getSession(false).getAttribute("employeeID");
            employee = employeeDao.getEmployee(employeeId);
        }
        String numShares = request.getParameter("orderNumShares");
        String type = request.getParameter("orderType");
        String buySellType = request.getParameter("orderBuySellType");
        String orderStockPercentage = request.getParameter("orderPercentage");
        String pricePerShare = request.getParameter("orderPricePerShare");
        String stockSymbol = request.getParameter("stockSymbol");

        OrderDao orderDao = new OrderDao();
        CustomerDao customerDao = new CustomerDao();
        StockDao stockDao = new StockDao();
        
        System.out.println("customer id="+customerId+" and Employee id="+employeeId);
        Customer customer = customerDao.getCustomer(customerId);
        Stock stock = stockDao.getStockBySymbol(stockSymbol);
        String result = "success";
        if (type.equals("Market"))
        {
            MarketOrder order = new MarketOrder();
            order.setDatetime(new Date());
            order.setPrice_type("Market");
            if(customer!=null)
            	order.setCustomerName(customer.getLastName()+customer.getFirstName());
            order.setBuySellType(buySellType);
            if(customerId!=null)
            	order.setCustomerID(Long.parseLong(customerId));
            if(employee!=null&&employee.getEmployeeID()!=null)
            	order.setEmployee_Id(employee.getEmployeeID());
            order.setNumShares(Integer.parseInt(numShares));
            result = orderDao.submitOrder(order, customer, employee, stock);
        }
        else if(type.equals("MarketOnClose"))
		{
            MarketOnCloseOrder order = new MarketOnCloseOrder();
            order.setDatetime(new Date());
            if(customer!=null)
            	order.setCustomerName(customer.getLastName()+customer.getFirstName());
            order.setPrice_type("MarketOnClose");
            order.setBuySellType(buySellType);
            if(employee!=null&&employee.getEmployeeID()!=null)
            	order.setEmployee_Id(employee.getEmployeeID());
            order.setNumShares(Integer.parseInt(numShares));
            if(customerId!=null)
            	order.setCustomerID(Integer.parseInt(customerId));
            result = orderDao.submitOrder(order, customer, employee, stock);
        }
		else if(type.equals("TrailingStop"))
		{
            TrailingStopOrder order = new TrailingStopOrder();
            if(customer!=null)
            	order.setCustomerName(customer.getLastName()+customer.getFirstName());
            order.setDatetime(new Date());
            order.setPrice_type("TrailingStop");
            order.setPercentage(Double.parseDouble(orderStockPercentage));
            if(employee!=null&&employee.getEmployeeID()!=null)
            	order.setEmployee_Id(employee.getEmployeeID());
            order.setNumShares(Integer.parseInt(numShares));
            if(customerId!=null)
            	order.setCustomerID(Long.parseLong(customerId));
            result = orderDao.submitOrder(order, customer, employee, stock);

        }
		else if(type.equals("HiddenStop"))
		{
            HiddenStopOrder order = new HiddenStopOrder();
            order.setDatetime(new Date());
            if(customer!=null)
            	order.setCustomerName(customer.getLastName()+customer.getFirstName());
            order.setPricePerShare(Double.parseDouble(pricePerShare));
            order.setPercentage(Double.parseDouble(orderStockPercentage));
            order.setNumShares(Integer.parseInt(numShares));
            if(customer!=null)
            	order.setCustomerID(Long.parseLong(customerId));
            if(employee.getEmployeeID()!=null)
            	order.setEmployee_Id(employee.getEmployeeID());
            result = orderDao.submitOrder(order, customer, employee, stock);
        }
        RequestDispatcher rd;

        if (result.equals("success")) {
            rd = request.getRequestDispatcher("home.jsp?result=success");
        }
        else
        {
            rd = request.getRequestDispatcher("home.jsp?result=error");
        }
        rd.forward(request, response);


    }

}
