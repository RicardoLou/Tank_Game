package com.Ricardo.DAO;

import com.Ricardo.bean.user;
import com.Ricardo.util.DBUtil;

import java.sql.*;

public class RegisterDAO {
    static user currUser = null;
    static Connection con = DBUtil.getConnection();
    static PreparedStatement preparedStatement = null;
    static ResultSet rs = null;
    static Statement statement = null;
    public static boolean register(int id, String name, String password) {
        if(id <= 0 || !selectById(id)) {
            return false;
        }
        if(!selectByName(name)) {
            return false;
        }
        System.out.println("OK");
        String sql = "insert into user(userId,username,password) values(" + id + ",'" + name + "','" + password + "');";
        try {
            statement = con.createStatement();
            int i = statement.executeUpdate(sql);
            if(i > 0) {
                System.out.println("注册成功");
                return true;
            } else {
                System.out.println("注册失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param id 用户id
     * @return true：没有找到这个人 false：找到了这个人
     */
    public static boolean selectById(int id) {
        String sql = "SELECT * FROM user WHERE userId=" + id + ";";
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

    /**
     *
     * @param name 用户姓名
     * @return true：没找到这个人 false：找到了这个人
     */
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
