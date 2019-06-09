import java.sql.Connection
import java.sql.ResultSet

import com.katalon.plugin.keyword.connection.DBType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



Connection connection = null;

ResultSet actorData;

Field_Email_Address_Field = "abc@mail.com"

connection = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.createConnection'(DBType.mysql, 'localhost', '3306', 'Movies', 'katalon', 'a2F0YWxvbg==')

//text = WebUI.concatenate("INSERT INTO  tblActor (ActorID,ActorName,ActorDOB,ActorGender)","hongle123")

//CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeUpdate'(connection,
//	"INSERT INTO  tblActor (ActorID,ActorName,ActorDOB,ActorGender)"
//	+ " VALUES ('6','hongtest','1962-07-03','Male')"
//	
//)
//


//text = WebUI.concatenate(["INSERT INTO  tblActor (ActorID,ActorName,ActorDOB,ActorGender) VALUES ('7',","\"''' +Field_Email_Address_Field+ ''',",'1962-07-03','Male')'"] as String[], FailureHandling.STOP_ON_FAILURE)

//text = WebUI.concatenate("\'", ''+Field_Email_Address_Field+'',"\'")

//text = WebUI.concatenate("'", "'"+Field_Email_Address_Field+"'","'")

//text1  =  WebUI.concatenate (["INSERT INTO  tblActor VALUES ('8',","'" +Field_Email_Address_Field + "'",",'1962-07-03','Male')"] as String[], FailureHandling.STOP_ON_FAILURE ) 

println insertquery

//insertquery = WebUI.concatenate((([‘Insert into useraccounts_dev_ca values (’’, Field_Email_Address_Field, ‘’, ‘test123!’);’]) as String[]))


//'Example: Export a result set to csv file'
//CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.exportToCSV'(actorData, RunConfiguration.getProjectDir() +"/OutPut Files/mysql_actor.csv")
//
//'Example: Get total of rows from a result set'
//println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getRowCount'(actorData)
//
//'Example: Get total of coulmns from a result set'
//println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getColumnCount'(actorData)
//
//'Example: Get single cell value using row and column index'
//println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getSingleCellValue'(actorData, 2, 2)
//
//'Example: Get single cell value using row and column index'
//println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getSingleCellValue'(actorData, 2, 'ActorName')
//
//'Example: Get list of cell value using row and column index'
//println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, 3)
//
//'Example: Get list of cell value using row and column label'
//println  CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, 'ActorDOB')

CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.closeConnection'(connection)









