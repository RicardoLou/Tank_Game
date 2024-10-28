package com.Ricardo.DAO;

import com.Ricardo.bean.user;
import com.Ricardo.util.DBUtil;

import java.sql.*;

public class informationDAO {
    static user currUser = null;
    static Connection con = DBUtil.getConnection();
    static PreparedStatement preparedStatement = null;
    static ResultSet rs = null;
    static Statement statement = null;
    /**
     *
     * @param username 用户姓名
     * @return 返回的是封装好的对象的信息
     */
    public static user selectByUserName(String username) {
        String sql = "SELECT * FROM user WHERE userName='" + username + "';";
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                currUser = new user();
                currUser.setUserId(rs.getInt("userId"));
                currUser.setUserName(rs.getString("userName"));
                currUser.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currUser;
    }
    public static int selectScoreByID(int id) {
        String sql = "SELECT score FROM userRank WHERE userId = " + id + ";";
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next()) {
                return rs.getInt("score");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
