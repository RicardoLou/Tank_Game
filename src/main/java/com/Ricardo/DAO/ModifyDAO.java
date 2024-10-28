package com.Ricardo.DAO;

import com.Ricardo.bean.user;
import com.Ricardo.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyDAO {
    static user currUser = null;
    static Connection con = DBUtil.getConnection();
    static ResultSet rs = null;
    static Statement statement = null;

    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @param password 要修改的密码
     * @return
     */
    public static boolean modify(String username, String password) {
        System.out.println(username);
        //要找的到这个用户才可以更改
        if(selectByName(username)) {
            System.out.println("没有找到用户名");
            return false;
        } else {
            try {

                String sql = "UPDATE user SET password = '" + password + "'  WHERE userName = '" + username + "';";

                statement = con.createStatement();
                int flag = statement.executeUpdate(sql);
                if(flag == 0)
                    return false;
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
        }
        return true;
    }

    public static boolean removeByName(String name) {
        if(selectByName(name)) {
            return false;
        } else {
            String sql1 = "SET foreign_key_checks = 0";
            String sql2 = "DELETE FROM user WHERE username = '" + name + "';";
            String sql3 = "SET foreign_key_checks = 1";
            try {
                statement = con.createStatement();
                statement.executeUpdate(sql1);
                int flag = statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
                if (flag == 0) {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

    public static boolean selectByName(String name) {
        String sql = "SELECT * FROM user WHERE userName='" + name + "';";
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if(rs.next())
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
