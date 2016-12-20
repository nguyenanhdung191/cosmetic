package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAL extends GeneralDAL {
    public OrderDAL() {
        super();
    }

    public ArrayList<Order> getCurrentOrder() {
        ArrayList<Order> list = new ArrayList<Order>();
        String query = "SELECT * FROM \"order\" WHERE OrderStateCode = -1";
        ResultSet result = runQuery(query);
        try {
            while (result.next()) {
                Order o = new Order();
                o.setOrderID(result.getInt(1));
                Timestamp orderDate = result.getTimestamp(6);
                o.setOrderDate(orderDate);
                o.setOrderStateCode(result.getInt(2));
                o.setOrderCustomerName(result.getString(3));
                o.setOrderAddress(result.getString(4));
                o.setOrderPhoneNumber(result.getString(5));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Order getOrderByID(int orderID) {
        String query = "SELECT * FROM \"order\" WHERE OrderID = " + orderID;
        Order o = new Order();
        ResultSet result = runQuery(query);
        try {
            while (result.next()) {
                o.setOrderID(result.getInt(1));
                Timestamp orderDate = result.getTimestamp(6);
                o.setOrderDate(orderDate);
                o.setOrderStateCode(result.getInt(2));
                o.setOrderCustomerName(result.getString(3));
                o.setOrderAddress(result.getString(4));
                o.setOrderPhoneNumber(result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public int addOrderItem(String orderCustomerName, String orderAddress, String orderPhoneNumber) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String query = "INSERT INTO \"order\" (OrderStateCode, OrderCustomerName, OrderAddress, OrderPhoneNumber, OrderDate) ";
        query += "VALUES (-1,";
        query += "N'" + orderCustomerName + "',";
        query += "N'" + orderAddress + "',";
        query += "N'" + orderPhoneNumber + "',";
        query += "'" + sdf.format(d) + "')";
        return runCRUD(query);
    }
}
