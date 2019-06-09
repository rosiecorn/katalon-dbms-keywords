package com.katalon.plugin.keyword.connection

import java.nio.charset.StandardCharsets

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.db.DatabaseConnection
import com.kms.katalon.core.db.DatabaseSettings

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.sql.Statement
import com.katalon.plugin.keyword.connection.DBType

public class DatabaseKeywords {

	/***
	 * This keyword is used to get global database setting connection
	 * @return A Connection
	 */
	@Keyword
	def getGlobalConnection() {
		DatabaseConnection dbConnection = new DatabaseSettings(RunConfiguration.getProjectDir()).getDatabaseConnection();
		return dbConnection.getConnection();
	}

	/***
	 * This keyword is used to create new connection by manually inputed db server
	 * @param type      - It is an enum type
	 * @param server    - It is server information ex: localhost, GTLRMC2\SQL2014EXPRESS
	 * @param port      - It is port number
	 * @param dbName    - It is database name or service name for oracle db type
	 * @param userName  - It is user name using to access to database
	 * @param Password  - It is a password using to access to database
	 * @return A Connection
	 */
	@Keyword
	def createConnection(DBType type, String server, String port, String dbName, String userName, String Password)  throws SQLException {

		String url = getConnectionUrl(type,server,port,dbName)
		return  DriverManager.getConnection(url,userName, base64Decode(Password))
	}

	/**
	 * This keyword is called when you need to close a connection
	 * @param conn  - It is a Connection 
	 * @return Null - Connection type
	 */
	@Keyword
	def closeConnection(Connection conn)  throws SQLException {

		if(conn != null && !conn.isClosed()){
			conn.close()
		}
		conn = null
	}

	/**
	 * Execute a SQL query on DB server name and return a result set 
	 * @param conn         - It is a Connection 
	 * @param queryString  - A single query and should return only one result set
	 * @return a RestultSet
	 */
	@Keyword
	def executeQuery(Connection conn, String queryString)  throws SQLException{
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
		ResultSet rs = stm.executeQuery(queryString)
		//stm.close();
		return rs
	}

	/**
	 * Execute a DDL statement
	 * @param conn          - A Connection
	 * @param queryString   - An SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or
	 * DELETE or an SQL statement that returns nothing     * 
	 */
	@Keyword
	def executeUpdate(Connection conn, String queryString)  throws SQLException{
		try {
			Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE)
			stm.executeUpdate(queryString)
			conn.commit();
			//stm.close();
			println "Database updated successfully ";
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes the given SQL statement, which may return multiple results or an update count
	 * In some (uncommon) situations, a single SQL statement may return
	 * multiple result sets and/or update counts.  Normally you can ignore
	 * this unless you are (1) executing a stored procedure that you know may
	 * return multiple results or (2) you are dynamically executing an
	 * unknown SQL string.
	 * @param conn           - A Connection
	 * @param queryString    - Any sql statement
	 * @return true or false
	 */
	@Keyword
	def execute(Connection conn, String queryString) throws SQLException {
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
		boolean result = stm.execute(queryString)
		conn.commit()
		//stm.close();
		return result
	}

	/*-----------------------Start Utilities-------------------------------*/


	private String getConnectionUrl(DBType type, String server, String port, String dbName) {
		if (type == DBType.sqlserver) {
			return "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + dbName
		}
		if (type == DBType.mysql) {

			return "jdbc:mysql://" + server + ":" + port + "/" + dbName
		}
		if (type == DBType.postgresql) {
			return "jdbc:postgresql://"+ server + ":" + port + "/" + dbName
		}
		if (type == DBType.oracle) {
			return "jdbc:oracle:thin:@//"+server+":" + port + "/" + dbName
		}
		else
			return ""
	}


	private String base64Decode (String encodedText) {
		new String(java.util.Base64.getDecoder().decode(encodedText), StandardCharsets.UTF_8)
	}


	private String base64Encode (String plainText) {
		return java.util.Base64.getEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8))
	}


	/*-----------------------End Utilities-------------------------------*/
}
