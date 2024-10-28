package com.Ricardo.DAO;

import com.Ricardo.bean.user;
import com.Ricardo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {
    static user currUser = null;
    static Connection con = DBUtil.getConnection();
    static ResultSet rs = null;
    static Statement statement = null;

    public static boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE userName = '" + username + "';";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()){
                currUser = new user();
                currUser.setUserId(rs.getInt("userId"));
                currUser.setUserName(rs.getString("userName"));
                currUser.setPassword(rs.getString("password"));
                return password.equals(currUser.getPassword());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
