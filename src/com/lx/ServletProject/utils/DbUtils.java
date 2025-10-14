package com.lx.ServletProject.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {
    private static DruidDataSource ds;
    private static final ThreadLocal<DruidDataSource> THREAD_LOCAL = new ThreadLocal<>();
    static {
        Properties p = new Properties();
        InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("/database.properties");
        try {
            p.load(in);
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()  {
        Connection connection = (Connection) THREAD_LOCAL.get();
        try {
            if (connection == null) {
                connection = ds.getConnection();
                THREAD_LOCAL.set((DruidDataSource) connection);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void begin(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void commit(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(connection,null,null);
        }
    }
    public static void rollback(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll(connection,null,null);
        }
    }
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
