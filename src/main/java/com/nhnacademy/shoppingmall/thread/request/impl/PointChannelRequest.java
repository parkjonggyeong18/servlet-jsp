package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class PointChannelRequest extends ChannelRequest {
    private final String userId;
    private final int pointsToAdd;

    public PointChannelRequest(String userId, int pointsToAdd) {
        this.userId = userId;
        this.pointsToAdd = pointsToAdd;
    }
    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.

        log.debug("pointChannel execute");

        Connection connection = DbConnectionThreadLocal.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE Users SET UserPoint = UserPoint + ? WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pointsToAdd);
            preparedStatement.setString(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("User {} earned {} points.", userId, pointsToAdd);
            } else {
                log.debug("Failed to earn points for user {}.", userId);
            }
        } catch (SQLException e) {
            log.error("Error while earning points for user {}: {}", userId, e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error closing PreparedStatement: {}", e.getMessage());
            }
            DbConnectionThreadLocal.reset();
        }
    }
}
