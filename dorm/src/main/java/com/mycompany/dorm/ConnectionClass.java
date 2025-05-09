package com.mycompany.dorm;

import java.sql.*;
public class ConnectionClass {
    Connection conn;
    /*String driver="com.mysql.cj.jdbc.Driver";
    String dbUrl="jdbc:mysql://localhost:3306/dorm?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String un="kati";
    String pass="kati";
    //Xampp server
    */
    String driver="oracle.jdbc.driver.OracleDriver";
    String dbUrl="jdbc:oracle:thin:@//AtsewKaleb:1521/xe";
    String un="dorm";
    String pass="dorm";
    public Connection openConnection()
    {
        try{
        Class.forName(driver);
        conn=DriverManager.getConnection(dbUrl, un, pass);
        }catch(Exception ex){
            System.out.println("connection cannot be found");
        }
        return conn;
    }

    public void close()  {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("close not working");
            throw new RuntimeException(e);
        }
    }
}

