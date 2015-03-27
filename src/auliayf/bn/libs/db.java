/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auliayf.bn.libs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database engine
 *
 * @author auliayf
 */
public class db {

    private Connection mConn = null;
    private ArrayList<db_model> mRecords;

    /**
     * Get Data From Database
     *
     * @param query Provided SQL Query
     * @return db
     */
    public db get(String query) {
        this.mConn = db_driver.getConnection();
        if (this.mConn != null) {
            this.mRecords = null;
            Statement mStatement = null;
            ResultSet mResultSet = null;
            String mColumns[];

            try {
                this.mRecords = new ArrayList<>();
                mStatement = this.mConn.createStatement();
                mResultSet = mStatement.executeQuery(query);
                mColumns = this.getColumns(mResultSet);

                while (mResultSet.next()) {
                    db_model model = new db_model();

                    for (String column : mColumns) {
                        model.set(column, mResultSet.getObject(column));
                    }

                    this.mRecords.add(model);
                }
            } catch (SQLException ex) {
                Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (mStatement != null) {
                        mStatement.close();
                    }
                    if (mResultSet != null) {
                        mResultSet.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("DATABASE IS NOT PROPERLY CONFIGURED");
        }
        return this;
    }

    /**
     * Get Result Array
     *
     * @return db_models collection
     */
    public ArrayList<db_model> result() {
        return this.mRecords;
    }

    /**
     * Get First data from the array
     *
     * @return db_model
     */
    public db_model row() {
        return (this.mRecords != null) ? this.mRecords.get(0) : null;
    }

    /**
     * Column Names Getter from ResultSet
     *
     * @param resultSet Provided ResultSet
     * @return String array contains column names
     */
    private String[] getColumns(ResultSet resultSet) {
        ResultSetMetaData rsMeta = null;
        String columnNames[] = null;
        int columnCount = 0, counter = 0;
        try {
            rsMeta = resultSet.getMetaData();
            columnCount = rsMeta.getColumnCount();
            columnNames = new String[columnCount];

            for (counter = 1; counter <= columnCount; counter++) {
                columnNames[counter - 1] = rsMeta.getColumnLabel(counter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnNames;
    }
}
