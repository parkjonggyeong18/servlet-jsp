package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Orders;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository<Orders> {

    private String query;

    @Override
    public Optional<Orders> findById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "SELECT * FROM Orders WHERE OrderID = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Orders Orders = new Orders(
                            rs.getInt("OrderID"),
                            rs.getString("userId"),
                            rs.getTimestamp("OrderDate").toLocalDateTime(),
                            rs.getTimestamp("ShipDate").toLocalDateTime()
                    );
                    return Optional.of(Orders);
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Orders Orders) {
        if(Orders == null){
            throw new DomainNullPointerException(Orders);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "INSERT INTO Orders(UserID, OrderDate, ShipDate) VALUES(?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, Orders.getUserID());
            ps.setTimestamp(2, Timestamp.valueOf(Orders.getOrderDate()));
            ps.setTimestamp(3, Timestamp.valueOf(Orders.getShipDate()));
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Orders Orders) {
        if(Orders == null){
            throw new DomainNullPointerException(Orders);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "UPDATE Orders SET UserID = ? , OrderDate = ? , ShipDate =? ";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, Orders.getUserID());
            ps.setTimestamp(2, Timestamp.valueOf(Orders.getOrderDate()));
            ps.setTimestamp(3, Timestamp.valueOf(Orders.getShipDate()));
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "DELETE FROM Orders WHERE OrderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(int id) {
        return findById(id).isPresent() ? 1 : 0;
    }

    public Optional<Integer> totalCount(){
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "SELECT count(*) AS total FROM Orders";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int result = rs.getInt("total");
                return Optional.of(result);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int saveAndGetOrderId(Orders orders) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String query = "INSERT INTO Orders(UserID, OrderDate, ShipDate) VALUES(?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, orders.getUserID());
            ps.setTimestamp(2, Timestamp.valueOf(orders.getOrderDate()));
            ps.setTimestamp(3, Timestamp.valueOf(orders.getShipDate()));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated primary key (orderID)
                } else {
                    throw new SQLException("Inserting order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int saveAndGetPrimaryKey(Orders orders) {
        if (orders == null) {
            throw new DomainNullPointerException(orders);
        }

        Connection connection = DbConnectionThreadLocal.getConnection();
        String query = "INSERT INTO Orders(UserID, OrderDate, ShipDate) VALUES(?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, orders.getUserID());
            ps.setTimestamp(2, Timestamp.valueOf(orders.getOrderDate()));
            ps.setTimestamp(3, Timestamp.valueOf(orders.getShipDate()));

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated primary key
                } else {
                    throw new SQLException("Inserting order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> getOrdersByUserId(String userId) {
        List<Orders> orders = new ArrayList<>();
        Connection connection = DbConnectionThreadLocal.getConnection();
        String query = "SELECT * FROM Orders WHERE UserID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setOrderID(rs.getInt("OrderID"));
                    order.setUserID(rs.getString("UserID"));
                    order.setOrderDate(rs.getTimestamp("OrderDate").toLocalDateTime());
                    order.setShipDate(rs.getTimestamp("ShipDate").toLocalDateTime());
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
}
