/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrinnova.api.schoolfees.jdbcprop;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author modupealadeojebi
 */
public class JDCConnectionDriver implements Driver {

    public static final String URL_PREFIX = "jdbc:jdc:";
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    private JDCConnectionPool pool;

    public JDCConnectionDriver(String driver, String url,
            String user, String password)
            throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            SQLException {


        DriverManager.registerDriver(this);
        Class.forName(driver).newInstance();
        pool = new JDCConnectionPool(url, user, password);
    }

    @Override
    public Connection connect(String url, Properties props)
            throws SQLException {

        if (!url.startsWith(URL_PREFIX)) {
            return null;
        }

        return (Connection) pool.getConnection();
    }

    @Override
    public boolean acceptsURL(String url) {
        return url.startsWith(URL_PREFIX);
    }

    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @Override
    public int getMinorVersion() {
        return MINOR_VERSION;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String str, Properties props) {
        return new DriverPropertyInfo[0];
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
