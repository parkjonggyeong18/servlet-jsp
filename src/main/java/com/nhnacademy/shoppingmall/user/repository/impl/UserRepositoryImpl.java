package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =String.format("select UserID, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLoginAt from Users where UserID=? and UserPassword =?");

        log.debug("sql:{}", sql);
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            psmt.setString(2, userPassword);

            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("UserID"),
                            rs.getString("UserName"),
                            rs.getString("UserPassword"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLoginAt")) ? rs.getTimestamp("LatestLoginAt").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        if (userId == null) {
            throw new NullPointerException("등록되지 않은 회원입니다!");
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =String.format("select UserID, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLoginAt from Users where UserID=?",
                userId
        );

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getString("UserID"),
                            rs.getString("UserName"),
                            rs.getString("UserPassword"),
                            rs.getString("UserBirth"),
                            User.Auth.valueOf(rs.getString("UserAuth")),
                            rs.getInt("UserPoint"),
                            Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("LatestLoginAt")) ? rs.getTimestamp("LatestLoginAt").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        if (user == null) {
            throw new NullPointerException("사용자가 존재하지 않습니다.");
        }
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO Users (UserID, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLoginAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, user.getUserId());
            psmt.setString(2, user.getUserName());
            psmt.setString(3, user.getUserPassword());
            psmt.setString(4, user.getUserBirth());
            psmt.setString(5, user.getUserAuth().name());
            psmt.setInt(6, user.getUserPoint());
            psmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            psmt.setTimestamp(8, Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null);

            return psmt.executeUpdate();
        }   catch (SQLIntegrityConstraintViolationException e) {
            log.error("사용자 중복 등록", e);
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Users where UserID= ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.]
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Users set UserID=?, UserName=?, UserPassword=?, UserBirth=?, UserAuth=?, UserPoint=?, CreatedAt=?, LatestLoginAt=? " +
                "where UserId=?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, user.getUserId());
            psmt.setString(2, user.getUserName());
            psmt.setString(3, user.getUserPassword());
            psmt.setString(4, user.getUserBirth());
            psmt.setString(5, user.getUserAuth().name());
            psmt.setInt(6, user.getUserPoint());
            psmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            psmt.setTimestamp(8, Objects.nonNull(user.getLatestLoginAt()) ? Timestamp.valueOf(user.getLatestLoginAt()) : null);

            psmt.setString(9, user.getUserId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =  "update Users set LatestLoginAt=? where UserId=?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            psmt.setString(2, userId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT count(*) AS count FROM Users where UserId=?";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    int countId = rs.getInt("count");
                    return countId;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT UserID, UserName, UserPassword, UserBirth, UserAuth, UserPoint, CreatedAt, LatestLoginAt FROM Users ORDER BY CreatedAt DESC";

        try (PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                User userss = new User(
                        rs.getString("UserID"),
                        rs.getString("UserName"),
                        rs.getString("UserPassword"),
                        rs.getString("UserBirth"),
                        User.Auth.valueOf(rs.getString("UserAuth")),
                        rs.getInt("UserPoint"),
                        Objects.nonNull(rs.getTimestamp("CreatedAt")) ? rs.getTimestamp("CreatedAt").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("LatestLoginAt")) ? rs.getTimestamp("LatestLoginAt").toLocalDateTime() : null
                );
                userList.add(userss);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


}
