/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auliayf.bn.libs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Database Query Handler
 *
 * @author auliayf
 */
public class db_query {

    private String mTable;
    private final ArrayList<String> mSelects = new ArrayList<>();
    private final ArrayList<String[]> mWheres = new ArrayList<>();
    private final ArrayList<String[]> mJoins = new ArrayList<String[]>();

    /**
     * Constructor and Setter for table name
     *
     * @param tableName Database Table Name
     */
    public db_query(String tableName) {
        this.mTable = tableName;
    }

    /**
     * Permits you to write the SELECT portion of your query
     *
     * @param selects SELECT
     * @return db_query
     */
    public db_query select(String... selects) {
        this.mSelects.addAll(Arrays.asList(selects));
        return this;
    }

    /**
     * Writes a "MAX(field)" portion for your query
     *
     * @param field Field Name
     * @return db_query
     */
    public db_query select_max(String field) {
        this.mSelects.add("MAX(" + field + ")");
        return this;
    }

    /**
     * This function is identical to the one above, except that you can include
     * a second parameter to rename the resulting field
     *
     * @param field Field Name
     * @param as Alias
     * @return db_query
     */
    public db_query select_max(String field, String as) {
        this.mSelects.add("MAX(" + field + ") as " + as);
        return this;
    }

    /**
     * Writes a "MIN(field)" portion for your query
     *
     * @param field Field Name
     * @return db_query
     */
    public db_query select_min(String field) {
        this.mSelects.add("MIN(" + field + ")");
        return this;
    }

    /**
     * This function is identical to the one above, except that you can include
     * a second parameter to rename the resulting field
     *
     * @param field Field Name
     * @param as Alias
     * @return db_query
     */
    public db_query select_min(String field, String as) {
        this.mSelects.add("MIN(" + field + ") as " + as);
        return this;
    }

    /**
     * Writes a "AVG(field)" portion for your query
     *
     * @param field Field Name
     * @return db_query
     */
    public db_query select_avg(String field) {
        this.mSelects.add("AVG(" + field + ")");
        return this;
    }

    /**
     * This function is identical to the one above, except that you can include
     * a second parameter to rename the resulting field
     *
     * @param field Field Name
     * @param as Alias
     * @return db_query
     */
    public db_query select_avg(String field, String as) {
        this.mSelects.add("AVG(" + field + ") as " + as);
        return this;
    }

    /**
     * Writes a "SUM(field)" portion for your query
     *
     * @param field Field Name
     * @return db_query
     */
    public db_query select_sum(String field) {
        this.mSelects.add("SUM(" + field + ")");
        return this;
    }

    /**
     * This function is identical to the one above, except that you can include
     * a second parameter to rename the resulting field
     *
     * @param field Field Name
     * @param as Alias
     * @return db_query
     */
    public db_query select_sum(String field, String as) {
        this.mSelects.add("SUM(" + field + ") as " + as);
        return this;
    }

    public db_query join(String table, String on, String type) {
        this.mJoins.add(new String[]{type, table, on});
        return this;
    }

    /**
     * Permits you to write the WHERE portion of your query
     *
     * @param wheres WHERE
     * @return db_query
     */
    public db_query where(String... wheres) {
        for (String where : wheres) {
            this.mWheres.add(new String[]{" AND ", where});
        }
        return this;
    }

    /**
     * Permits you to write the WHERE portion of your query, except that
     * multiple instances are joined by OR
     *
     * @param wheres WHERE
     * @return db_query
     */
    public db_query or_where(String... wheres) {
        for (String where : wheres) {
            this.mWheres.add(new String[]{" OR ", where});
        }
        return this;
    }

    /**
     * Generates an sql string based on the data you supply,
     *
     * @return Query
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.mSelects.size() > 0) {
            builder.append("SELECT ");

            boolean first = true;
            for (String select : this.mSelects) {
                if (!first) {
                    builder.append(", ");
                }
                builder.append(select);
                first = false;
            }
            builder.append(" FROM ").append(this.mTable);
        } else {
            builder.append("SELECT * FROM ").append(this.mTable);
        }

        if (this.mJoins.size() > 0) {
            for (String[] join : this.mJoins) {
                if (join[0].length() > 0) {
                    builder.append(" ");
                    builder.append(join[0]);
                }
                builder.append(" JOIN ");
                builder.append(join[1]);
                builder.append(" ON ");
                builder.append(join[2]);
            }
        }

        if (this.mWheres.size() > 0) {
            builder.append(" WHERE ");

            boolean first = true;
            for (String[] where : this.mWheres) {
                if (!first) {
                    builder.append(where[0]);
                }
                builder.append(where[1]);
                first = false;
            }
        }

        return builder.toString();
    }
}
