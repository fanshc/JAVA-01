package com.fanshc.homework.transaction;

import java.sql.*;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/03/13/9:20 下午
 */
public class DbUtil {
    static String user = "root";
    static String password = "root";
    static String url = "jdbc:mysql://localhost:3307/sharding_db?serverTimezone=UTC&characterEncoding=utf8&rewriteBatchedStatements=true";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn) {
        try{
            try{
                if(rs != null) {
                    rs.close();
                }
            }finally {
                try{
                    if(stmt != null){
                        stmt.close();
                    }
                }finally {
                    if(conn != null){
                        conn.close();
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
