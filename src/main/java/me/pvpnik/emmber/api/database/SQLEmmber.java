package me.pvpnik.emmber.api.database;

import me.pvpnik.emmber.utils.OUT;
import me.pvpnik.emmber.utils.Pair;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * https://stackoverflow.com/a/36789060
 */
public abstract class SQLEmmber {

    private String table;

    public SQLEmmber(String table) {
        this.table = table;
        if (!tableExist()) {
            createTable();
        }
    }

    /**
     * Executed at: {@link SQLEmmber#SQLEmmber(String)}
     */
    public abstract void createTable();

    /**
     * Deletes the table
     */
    public void deleteTable() {
        executeUpdate("DROP TABLE IF EXISTS `" + table + "`");
    }

    /**
     * @return <code>true</code> if table already exist in the database
     */
    public boolean tableExist() {
        try (Connection con = DataSource.getConnection();
             ResultSet resultSet = con.getMetaData().getTables(null, null, table, new String[]{"TABLE"})) {
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delets a row
     *
     * @param primaryKey - {@link Pair<String, Object>} fst - columnLabel, snd - row's identification
     */
    protected boolean delete(Pair<String, Object> primaryKey) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM " + table + " WHERE `" + primaryKey.fst + "` = ?")) {
            ps.setObject(1, primaryKey.snd);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean delete(Pair<String, Object> key, Pair<String, Object> value) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM " + table + " WHERE `" + key.fst + "` = ? AND `" + value.fst + "` = ?")) {
            ps.setObject(1, key.snd);
            ps.setObject(2, value.snd);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if row exist
     *
     * @param primaryKey - {@link Pair<String, Object>} fst - columnLabel, snd - row's identification
     * @return True if row found
     */
    protected boolean exist(Pair<String, Object> primaryKey) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE `" + primaryKey.fst + "` = ?")) {
            ps.setObject(1, primaryKey.snd);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if row with setted value exist
     *
     * @param key   - {@link Pair<String, Object>} fst - columnLabel, snd - row's identification
     * @param value - {@link Pair<String, Object>} fst - columnLabel, snd - value
     * @return <code>true</code> if row with setted value exist
     */
    protected boolean exist(Pair<String, Object> key, Pair<String, Object> value) {
        try (Connection connection = DataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE `" + key.fst + "` = ? AND `" + value.fst + "` = ?")) {
            ps.setObject(1, key.snd);
            ps.setObject(2, value.snd);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserts a new row into the table
     * Number of pairs (@param pairs.lenght) MUST be equal to number of columns in the table
     *
     * @param pairs - {@link Pair<String, Object>} fst - columnLabel, snd - value
     */
    protected boolean insert(Pair<String, Object>... pairs) {
        String sqlQuery = getInsertQuery(pairs);
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlQuery)) {
            for (int i = 1; i <= pairs.length; i++) {
                pst.setObject(i, pairs[i - 1].snd);
            }
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates insert query from the pairs
     *
     * @param pairs - {@link Pair<String, Object>} fst - columnLabel, snd - ?
     * @return the sql query
     */
    protected String getInsertQuery(Pair<String, Object>... pairs) {
        // "INSERT INTO " + table + "(`id`, `mob`, `maxspawn`, `cooldown`, `locations`) VALUES (?,?,?,?,?)"
        String sqlQuery = "INSERT INTO " + table + "(";
        for (Pair pair : pairs) {
            sqlQuery += "`" + pair.fst + "`,";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
        sqlQuery += ") VALUES (";
        for (Pair pair : pairs) {
            sqlQuery += "?,";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
        sqlQuery += ")";
        //OUT.toConsole("1sqlQuery: " + sqlQuery);
        return sqlQuery;
    }

    /**
     * Updates existing row
     * Number of pairs (@param pairs.lenght) MUST be equal to:  number of columns in the table - 1
     *
     * @param primaryKey - {@link Pair<String, Object>} fst - columnLabel, snd - row's identification
     * @param pairs      - {@link Pair<String, Object>} fst - columnLabel, snd - value
     */
    protected boolean update(Pair<String, Object> primaryKey, Pair<String, Object>... pairs) {
        String sqlQuery = getUpdateQuery(primaryKey, pairs);
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlQuery)) {
            for (int i = 1; i <= pairs.length; i++) {
                pst.setObject(i, pairs[i - 1].snd);
            }
            pst.setObject(pairs.length + 1, primaryKey.snd);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates update query from the pairs
     *
     * @param primaryKey - {@link Pair<String, Object>} fst - columnLabel, snd - row's identification
     * @param pairs      - {@link Pair<String, Object>} fst - columnLabel, snd - ?
     * @return the sql query
     */
    protected String getUpdateQuery(Pair<String, Object> primaryKey, Pair<String, Object>... pairs) {
        // "UPDATE " + table + " SET `mob`=?,`maxspawn`=?,`cooldown`=?,`locations`=? WHERE `id` = ?"
        String sqlQuery = "UPDATE " + table + " SET ";
        for (Pair pair : pairs) {
            sqlQuery += "`" + pair.fst + "`=?,";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);
        sqlQuery += " WHERE `" + primaryKey.fst + "`=?";
        return sqlQuery;
    }

    /*
     * Gets specific information
     *
     * @param primaryKey  - {@link Pair<String, Object>} key - columnLabel, value - which row (for example player's uuid)
     * @param columnLabel - what info you want
     * @return

    public Object get(Pair<String, Object> primaryKey, String columnLabel) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM " + table + " WHERE `" + primaryKey.fst + "` = ?")) {
            pst.setObject(1, primaryKey.snd);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getObject(columnLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    protected Object get(Pair<String, Object> primaryKey, String columnLabel) {
        try {
            Pair<Connection, ResultSet> t = getRows(primaryKey);
            ResultSet resultSet = t.snd;
            Object object = null;
            if (resultSet.next()) {
                object = resultSet.getObject(columnLabel);
            }
            t.fst.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected List<Object> getAll(Pair<String, Object> primaryKey, String columnLabel) {
        List<Object> objects = new ArrayList<>();
        try {
            Pair<Connection, ResultSet> t = getRows(primaryKey);
            ResultSet resultSet = t.snd;
            while (resultSet.next()) {
                objects.add(resultSet.getObject(columnLabel));
            }
            t.fst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return objects;
        }
    }

    private Pair<Connection, ResultSet> getRows(Pair<String, Object> primaryKey) throws SQLException {
        Connection con = DataSource.getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM " + table + " WHERE `" + primaryKey.fst + "` = ?");
        pst.setObject(1, primaryKey.snd);
        return new Pair<>(con, pst.executeQuery());
    }

    protected ResultSet getRow(Pair<String, Object> primaryKey) {
        try {
            Pair<Connection, ResultSet> t = getRows(primaryKey);
            ResultSet resultSet = t.snd;
            if (resultSet.next()) {
                return resultSet;
            }
            t.fst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return null;
        }
    }

    protected Collection<Object> getColumn(String columnLabel) {
        Collection<Object> collection = Collections.EMPTY_LIST;
        try {
            ResultSet rs = executeQuery("SELECT `" + columnLabel + "` FROM " + table);
            while (rs.next()) {
                collection.add(rs.getObject(columnLabel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * @param columnLabel - selected column
     * @return the largest value of the selected column
     */
    protected Object max(String columnLabel) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT MAX(`" + columnLabel + "`) FROM " + table)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * @param columnLabel - selected column
     * @return the smallest value of the selected column
     */
    protected Object min(String columnLabel) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT MIN(`" + columnLabel + "`) FROM " + table)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * //ONLY SELECT\\
     * SQL statements which retrieve some data from the table
     *
     * @param SQL_QUERY - The query
     * @return ResultSet object which contains the results returned by the query
     */
    @Nullable
    public ResultSet executeQuery(String SQL_QUERY) {
        /*if (!SQL_QUERY.startsWith("SELECT")) {
            return null;
        }*/
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY);
             ResultSet rs = pst.executeQuery()) {
            return rs;
        } catch (Exception e) {
            OUT.toConsole("SQL_QUERY: " + SQL_QUERY);
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * //ONLY NON-SELECT queries\\
     * SQL statements which update or modify the databse
     *
     * @param SQL_QUERY
     * @return int value which represents the number of rows affected by the query.
     * This value will be the 0 for the statements with return nothing.
     */
    @Nullable
    public Integer executeUpdate(String SQL_QUERY) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            return pst.executeUpdate();
        } catch (Exception e) {
            OUT.toConsole("SQL_QUERY: " + SQL_QUERY);
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    public String getTable() {
        return table;
    }
}
