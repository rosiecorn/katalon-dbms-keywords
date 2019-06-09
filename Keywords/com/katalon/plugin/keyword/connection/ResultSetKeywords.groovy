package com.katalon.plugin.keyword.connection

import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException

import com.kms.katalon.core.annotation.Keyword

public class ResultSetKeywords {

	/**
	 * Using get list of column value from a result set bases on column name
	 * @param rs              - A result set
	 * @param columnName   
	 * @return List of string value  
	 */

	@Keyword
	def getListCellValue (java.sql.ResultSet rs, String columnName) throws SQLException{
		List<String> listOfValue = new ArrayList<>();
		rs.beforeFirst();
		while (rs.next()){
			listOfValue.add(rs.getString(columnName));
		}
		return listOfValue
	}

	/**
	 * Using get list of column value from a result set bases on column index
	 * @param rs             - A result set
	 * @param columnIndex
	 * @return List of string value  
	 */
	@Keyword
	def getListCellValue (java.sql.ResultSet rs, int columnIndex) throws SQLException{
		List<String> listOfValue = new ArrayList<>();
		rs.beforeFirst();
		while (rs.next()) {
			listOfValue.add(rs.getString(columnIndex));
		}
		return listOfValue
	}

	/**
	 * Using get single of cell value from a result set bases on column and row index
	 * @param rs             - A result set
	 * @param rowIndex       - Index of row need to get data
	 * @param colIndex       - Index of column need to get data
	 * @return a value       - A String datatype
	 */
	@Keyword
	def getSingleCellValue(java.sql.ResultSet rs, int rowIndex, int colIndex) throws SQLException{
		rs.absolute(rowIndex)
		List<String> listOfValue = new ArrayList<>();

		return rs.getString(colIndex)
	}

	/**
	 * Using get single of cell value from a result set bases on column name and row index
	 * @param rs             - A Ressult set
	 * @param rowIndex       - Index of row need to get data
	 * @param columnName     - Index of column need to get data
	 * @return a value       - A String datatype
	 */
	@Keyword
	def getSingleCellValue(java.sql.ResultSet rs, int rowIndex, String columnName)throws SQLException{
		rs.absolute(rowIndex)
		return rs.getString(columnName)
	}

	/**
	 * Return total of rows from a provided result set
	 * @param rs            - A result set
	 * @return a number     - An integer datatype
	 */
	@Keyword
	def getRowCount(java.sql.ResultSet rs) throws SQLException {
		rs.last();
		return  rs.getRow();
	}


	@Keyword
	def getSingleRowValue(java.sql.ResultSet rs, int rowIndex) throws SQLException{
		List<String> listOfValue = new ArrayList<>();
		rs.absolute(rowIndex)
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();	;
		for (int i=1; i< columns; i ++) {
			listOfValue.add(md.getColumnName(i) +":" + rs.getString(i))
		}
		return listOfValue
	}

	@Keyword
	def List getListRowValue(java.sql.ResultSet rs, int fromRowIndex, int toRowIndex) throws SQLException{

		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		ArrayList list = new ArrayList();

		for (int i=fromRowIndex;i<=toRowIndex;i++) {
			rs.absolute(i)
			HashMap row = new HashMap(columns);

			for(int j=1; j<=columns; ++j){
				row.put(md.getColumnName(j),rs.getObject(j));
			}

			list.add(row);
		}
		return list
	}

	@Keyword
	def List resultSetToArrayList(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		ArrayList list = new ArrayList();
		rs.beforeFirst()
		while (rs.next()){
			HashMap row = new HashMap(columns);
			for(int i=1; i<=columns; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			list.add(row);
		}

		return list;
	}

	/**
	 * Return total of available columns from a provided result set
	 * @param rs             - A result set
	 * @return a number      - An integer datatype
	 */
	@Keyword
	def getColumnCount (java.sql.ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		return rsmd.getColumnCount();
	}

	/**
	 * Check a result set is empty or not
	 * @param rs                       - A result set
	 * @return true or false           - A boolean datatype
	 */
	@Keyword
	def isEmptyResult (java.sql.ResultSet rs) throws SQLException{
		if (!rs.first() ) {
			return true
		}
		else {
			return false
		}
	}

	/**
	 * Using export a result set to csv file
	 * @param rs            - It is a result set
	 * @param pathFile      - Location of output file 
	 * @return a csv file
	 */
	@Keyword
	def exportToCSV(java.sql.ResultSet rs, String pathFile) throws SQLException{
		FileWriter fw = new FileWriter(pathFile)
		int columns = rs.getMetaData().getColumnCount();

		for (int j=1; j<(columns+1); j++) {
			fw.append(rs.getMetaData().getColumnName (j));
			if (j<columns) fw.append(","); else fw.append("\r\n");
		}

		rs.beforeFirst();
		while (rs.next()) {
			for (int i = 1; i <= columns; i++) {
				fw.append(rs.getString(i));
				if (i<columns) fw.append(","); else fw.append("\r\n");
			}
		}
		fw.flush()
		fw.close()
	}
}
