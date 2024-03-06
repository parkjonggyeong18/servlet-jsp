package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.order.repository.OrderDetailsRepository;
import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository<OrderDetails> {

    private String query;

    @Override
    public Optional<OrderDetails> findById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    OrderDetails orderDetails = new OrderDetails(
                            rs.getInt("OrderID"),
                            rs.getInt("ProductID"),
                            rs.getInt("Quantity"),
                            rs.getInt("UnitCost")
                    );
                    return Optional.of(orderDetails);
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(OrderDetails orderDetails) {
        if(orderDetails == null){
            throw new DomainNullPointerException(orderDetails);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "INSERT INTO OrderDetails(OrderID, ProductID, Quantity, UnitCost) VALUES(?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderDetails.getOrderID());
            ps.setInt(2, orderDetails.getProductID());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getUnitCost());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(OrderDetails orderDetails) {
        if(orderDetails == null){
            throw new DomainNullPointerException(orderDetails);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "UPDATE Orders SET OrderID = ?, ProductID = ? , Quantity = ? , UnitCost =? ";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderDetails.getOrderID());
            ps.setInt(2, orderDetails.getProductID());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getUnitCost());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        query = "DELETE FROM OrderDetails WHERE OrderID = ?";
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
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        Connection connection = DbConnectionThreadLocal.getConnection();
        String query = "SELECT * FROM OrderDetails WHERE OrderID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrderID(rs.getInt("OrderID"));
                    orderDetails.setProductID(rs.getInt("ProductID"));
                    orderDetails.setQuantity(rs.getInt("Quantity"));
                    orderDetails.setUnitCost(rs.getInt("UnitCost"));
                    orderDetailsList.add(orderDetails);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderDetailsList;
    }
}
