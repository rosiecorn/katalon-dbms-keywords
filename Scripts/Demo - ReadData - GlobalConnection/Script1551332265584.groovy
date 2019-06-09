import java.sql.Connection
import java.sql.ResultSet

import com.kms.katalon.core.configuration.RunConfiguration


 Connection globalConnection = null;
 ResultSet actorData;

 globalConnection = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.getGlobalConnection'()
 actorData = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeQuery'(globalConnection, "SELECT * FROM tblActor WITH(NOLOCK)")

'Example: check result set is empty'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.isEmptyResult'(actorData)
 
'Example: Export a result set to csv file'
 CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.exportToCSV'(actorData, RunConfiguration.getProjectDir() +"/OutPut Files/global_actor.csv")

'Example: Get total of rows from a result set'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getRowCount'(actorData)
 
'Example: Get total of coulmns from a result set'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getColumnCount'(actorData) 
 
'Example: Get single cell value using row and column index'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getSingleCellValue'(actorData, 2, 2)

'Example: Get single cell value using row and column index'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getSingleCellValue'(actorData, 2, 'ActorName')
 
'Example: Get list of cell value using row and column index'
 println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, 3)
 
'Example: Get list of cell value using row and column label'
 println  CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, 'ActorDOB') 
 
 CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.closeConnection'(globalConnection)
 
 
 
 
 
 
 
 