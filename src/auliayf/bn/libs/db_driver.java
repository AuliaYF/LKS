/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auliayf.bn.libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database Connection Handler
 *
 * @author auliayf
 */
public class db_driver {

    private static Connection mConn = null;
    private static String mUrl;
    private static String mUsername;
    private static String mPassword;

    /**
     * Constructor and Setter for required variables
     *
     * @param url MySQL Database URL
     * @param username MySQL Username
     * @param password MySQL Password
     */
    public db_driver(String url, String username, String password) {
        db_driver.mUrl = url;
        db_driver.mUsername = username;
        db_driver.mPassword = password;
    }

    /**
     * Getter for MySQL connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        if (mConn != null) {
            try {
                Class.forName("com.jdbc.mysql.DriverManager");
                mConn = DriverManager.getConnection(mUrl, mUsername, mPassword);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(db_driver.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mConn;
        }
        return mConn;
    }
}
