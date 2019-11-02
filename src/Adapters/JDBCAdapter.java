/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import objects.OperationFather;
import util.Utilities;
/**
 * create a new connection to a data base using the constructor
 * JDBCAdapter (String url, String driverName,
            String user, String passwd)
 * @author DELL
 */
public class JDBCAdapter extends  AbstractTableModel{

/**
 * An adaptor, transforming the JDBC interface to the TableModel interface.
 *
 * @author Philip Milne modified by BENHADDOU MOHAMED AMINE
 */
    
@SuppressWarnings("serial")
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String SQL;
    private String[] columnNames = {};
    private List<List<Object>> rows = new ArrayList<List<Object>>();
    private ResultSetMetaData metaData;
    private boolean editableTable = true;
    private boolean ErrorExists = false;
    private String ErrorMessage = "";
    private String ErrorCause = "";
    private int ErrorNumber;
    
    /**
     * Constructor of the class
     * @param url
     * @param driverName
     * @param user
     * @param passwd 
     */
    public JDBCAdapter(String url, String driverName,
            String user, String passwd) {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, passwd);
            statement = connection.createStatement();
        }catch (ClassNotFoundException ex) {
            ErrorExists = true;
            ErrorCause = ex.toString();
            ErrorNumber = 0;
            System.err.println("le pilote de la base de donnée ne peut être trouvé.");
            System.err.println(ex);
        }catch (SQLException ex) {
            ErrorExists = true;
            ErrorCause = ex.toString();
            ErrorNumber = ex.getErrorCode();
            System.err.println(ErrorNumber);
            System.err.println("Connexion a la base de donnée impossible.");
            System.err.println(ex.toString() + ex.getErrorCode());
        }
    }

    public static JDBCAdapter connect(){
        JDBCAdapter result = new JDBCAdapter(Utilities.URL,
                                            Utilities.DRIVER_NAME,
                                            Utilities.USER,
                                            Utilities.PASSWORD);
        return result;
    }
    
    public static JDBCAdapter connect_C1(){
        JDBCAdapter result = new JDBCAdapter(Utilities.URL_C,
                                            Utilities.DRIVER_NAME,
                                            Utilities.USER,
                                            Utilities.PASSWORD);
        return result;
    }
    
    public boolean ErrorExists(){
        return ErrorExists;
    }
    
    public int getErrorNumber(){
        return ErrorNumber;
    }
    
    public String SQL(String columnName, String tableName, String clause){
        String SQL = "SELECT " + columnName + " FROM " + tableName + " WHERE name='" + clause +"'";
        return SQL;
    }
	
    public String SQL(String[] columnNames, String tableName) {
        int i;
        String SQL = "SELECT ";
        for (i = 0; i <= columnNames.length - 1; i++) {
            SQL += columnNames[i];
            if (i < columnNames.length - 1) {
                SQL += ",";
            }
        }
        SQL += " FROM " + tableName ;
		return SQL;
    }
	
    public String SQL(String[] columnNames, String tableName,
                            String clauseName, String clause){
	String sql = this.SQL(columnNames, tableName);
	sql += " WHERE " + clauseName +" LIKE '" + clause ;
	return sql;
    }
	
    public String SQL(String[] columnNames, String tableName,
            String clauseName,String clause, String orderColumn){
	String sql = this.SQL(columnNames, tableName,clauseName, clause);
	sql += "%' ORDER BY " + orderColumn;
	return sql;
    }
	
    public String insertSQL(Object [] values, String[] columnNames, String tableName){
	String SQL = "INSERT INTO " + tableName + " (";
	for (int i = 0; i < columnNames.length; i++){
            SQL += columnNames[i];
            if (i < columnNames.length - 1){
            	SQL += ",";
            }
	}
	SQL += ") VALUES (";
	for (int i = 0; i < values.length; i++){
            SQL  += "'";
            SQL += values[i];
            SQL  += "'";
            if (i < values.length -  1){
            	SQL += ",";
            }
	}
	SQL += ")";
	return SQL;
    }
	
    public String insertSQL(Object value, String columnName, 
            String tableName, String clause){
		String SQL = "UPDATE " + tableName + " SET " + columnName + "='" + value + "' WHERE name ='" + clause + "'"; 
		return SQL;
	}
    
    public void executeQuery(String columnName, String tableName,
                                String clause){
	String sql = this.SQL(columnName, tableName, clause);
	this.executeQuery(sql);
    }

    public void executeQuery(String[] nomColonne, String nomTable,
            String clauseName,
            String clause, String orderColumn) {
	String sql = this.SQL(nomColonne, nomTable, clauseName, 
                                clause, orderColumn);
	this.executeQuery(sql);
    }
	
    public void executeQuery(String[] nomColonne, String nomTable, 
                            String clauseName, String clause) {
	String sql = this.SQL(nomColonne, nomTable,clauseName, clause);
	this.executeQuery(sql);
    }
	
    public void executeQuery(String[] nomColonne, String nomTable) {
        String SQL = this.SQL(nomColonne, nomTable);
        this.executeQuery(SQL);
    }

    public void executeQuery(String query) {
        if (connection == null || statement == null) {
            System.err.println("aucune base de donnée ouverte pour exécuter la requête.");
            return;
        }
		
        try {
            resultSet = statement.executeQuery(query);
            metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();
            columnNames = new String[numberOfColumns];
            // Get the column names and cache them.
            // Then we can close the connection.
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames[column] = metaData.getColumnLabel(column + 1);
            }

            // Get all rows.
            rows = new ArrayList<List<Object>>();
            
                    
            while (resultSet.next()) {
                List<Object> newRow = new ArrayList<Object>();
                for (int i = 1; i <= getColumnCount(); i++) {
                    newRow.add(resultSet.getObject(i));
                }
                rows.add(newRow);
            }
            //  close(); Need to copy the metaData, bug in jdbc:odbc driver.

            // Tell the listeners a new table has arrived.
            fireTableChanged(null);
            ErrorExists = false;
        } catch (SQLException ex) {
            ErrorNumber = ex.getErrorCode();
            ErrorCause = ex.toString();
            ErrorMessage = "la requete ne peut être executer";
            ErrorExists = true;
            //System.err.println(query);
            //System.err.println(ErrorMessage);
            //System.err.println(ex);
        } 
    }
	
    public void executeUpdate(Object[] values, String[] columnNames, String tableName){
	if (connection == null || statement == null) {
            System.err.println("aucune base de donnée ouverte pour exécuter la mise à jour.");
            return;
        }
        try{
                String sql = this.insertSQL(values, columnNames, tableName);
                statement.executeUpdate(sql);

        }catch (SQLException e){
                System.err.println("la mise a jour ne peut être executer.");
                System.err.println(e.getErrorCode());
                System.err.println(e.getSQLState());
        }
    }
	
    public void executeUpdate(Object value, String columnName, String tableName, String clause){
	if (connection == null || statement == null) {
            System.err.println("aucune base de donnée ouverte pour exécuter la mise à jour.");
            return;
        }
        try{
                String sql = this.insertSQL(value, columnName, tableName, clause);
                statement.executeUpdate(sql);
        }catch (SQLException e){
                System.err.println("la mise a jour ne peut être executer.");
                System.err.println(e.getErrorCode());
                System.err.println(e.getSQLState());
        }		
    }
    
    public void executeUpdate(String sql)
    {
        if (connection == null || statement == null) {
            System.err.println("aucune base de donnée ouverte pour exécuter la mise à jour.");
            return;
        }
        try{
            SQL = sql;
            statement.executeUpdate(sql);
            ErrorExists = false;
            ErrorMessage = "";
            ErrorCause = "";
            ErrorNumber = 0;
        }catch (SQLException e){
            ErrorExists = true;
            ErrorMessage = "la mise a jour ne peut être executer.";
            ErrorCause = e.toString();
            ErrorNumber = e.getErrorCode();
            System.err.println(ErrorMessage);
            System.err.println(sql);
            System.err.println(e);
        }
    }
    
    public String getErrorMessage(){
        return ErrorMessage;
    }
    public String getErrorCause(){
        return ErrorCause;
    }
    public boolean getUpdateError(){
        return ErrorExists;
    }
    public void findBeginWith(String tableName, String [] columnNames,
        String columnToLookFor, String columnCondition ){
        String columnContent = columnToLookFor + " LIKE '" + 
                            columnCondition +"%'";
        this.executeQuery(columnNames,tableName,columnToLookFor,
                                        columnCondition);
    }
    
    /**
     * prints the sql command saved in the class.
     */
    public void printSql(){
        System.out.println(SQL);
    }
	
    public void close() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
	
    public void setItNotEditable(){
		editableTable = false;
	}
    //////////////////////////////////////////////////////////////////////////
    //
    //             Implementation of the TableModel Interface
    //
    //////////////////////////////////////////////////////////////////////////
    // MetaData
    
    public TableModel getModel(){
        return new AbstractTableModel() {
            public String getColumnName(int col) {
                return columnNames[col].toString();
            }
            public int getRowCount() { return rows.size(); }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) {
                return rows.get(row).get(col);
            }
            public boolean isCellEditable(int row, int col)
                { return true; }
            public void setValueAt(Object value, int row, int col) {
                List<Object> aRow = rows.get(row);
                aRow.set(row, rows.get(row).get(col)) ;
                fireTableCellUpdated(row, col);
            }
        };
    }
    
    @Override
    public String getColumnName(int column) {
        if (columnNames[column] != null) {
            return columnNames[column];
        } else {
            return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        int type;
        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return super.getColumnClass(column);
        }

        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class;

            case Types.BIT:
                return Boolean.class;

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                return Integer.class;

            case Types.BIGINT:
                return Long.class;

            case Types.FLOAT:
            case Types.DOUBLE:
                return Double.class;

            case Types.DATE:
                return java.sql.Date.class;

            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (editableTable){
			try {
				return metaData.isWritable(column + 1);
			} catch (SQLException e) {
				return false;
			}
		}else {
			return false;
		}
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    // Data methods
    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int aRow, int aColumn) {
        List<Object> row = rows.get(aRow);
        return row.get(aColumn);
    }

    public String dbRepresentation(int column, Object value) {
        int type;

        if (value == null) {
            return "null";
        }

        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return value.toString();
         }

        switch (type) {
            case Types.INTEGER:
            case Types.DOUBLE:
            case Types.FLOAT:
                return value.toString();
            case Types.BIT:
                return ((Boolean) value).booleanValue() ? "1" : "0";
            case Types.DATE:
                return value.toString(); // This will need some conversion.
            default:
                return "\"" + value.toString() + "\"";
        }

    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        try {
            String tableName = metaData.getTableName(column + 1);
            // Some of the drivers seem buggy, tableName should not be null.
            if (tableName == null) {
                System.out.println("Table name returned null.");
            }
            String columnName = getColumnName(column);
            String query =
                    "update " + tableName + " set " + columnName + " = "
                    + dbRepresentation(column, value) + " where ";
            // We don't have a model of the schema so we don't know the
            // primary keys or which columns to lock on. To demonstrate
            // that editing is possible, we'll just lock on everything.
            for (int col = 0; col < getColumnCount(); col++) {
                String colName = getColumnName(col);
                if (colName.equals("")) {
                    continue;
                }
                if (col != 0) {
                    query = query + " and ";
                }
                query = query + colName + " = " + dbRepresentation(col,
                        getValueAt(row, col));
            }
            System.out.println(query);
            System.out.println("Not sending update to database");
            // statement.executeQuery(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("Update failed");
        }
        List<Object> dataRow = rows.get(row);
        dataRow.set(column, value);
    }    
    
    public void print(){
        System.out.println("row X column : (" + getRowCount() +
                " X " + getColumnCount() + ")");
        for (int i=0; i < getRowCount(); i++){
            for (int j=0; j < getColumnCount(); j++){
                System.out.print(getValueAt(i,j) + "\t");
            }
            System.out.println();
        }
    }

    public void executeQuery(String[] columnNames, String tableName, String clause) {
        String sql = SQL(columnNames,tableName);
        sql += " " + clause;
        SQL = sql;
        executeQuery(sql);
    }

}
