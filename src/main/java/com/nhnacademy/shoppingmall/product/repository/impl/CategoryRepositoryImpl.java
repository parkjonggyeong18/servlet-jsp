package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.product.repository.CategorytRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategorytRepository<Category> {
    private String sql;

    @Override
    public Optional<Category> findById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Category category = new Category(
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName")
                    );
                    return Optional.of(category);
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Category category) {
        if(category == null){
            throw new DomainNullPointerException(category);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "INSERT INTO Categories(CategoryName) VALUES(?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        if(category == null){
            throw new DomainNullPointerException(category);
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "UPDATE Categories SET CategoryID = ? , CategoryName = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, category.getCategoryID());
            ps.setString(2, category.getCategoryName());
            return ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(int id) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        sql = "DELETE FROM Categories WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
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
        sql = "SELECT count(*) AS total FROM Categories";

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
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
}
