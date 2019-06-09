import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import java.sql.Connection
import java.sql.ResultSet

Connection globalConnection = null;
ResultSet actorData;

globalConnection = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.getGlobalConnection'()

'Example: Insert statement record on database'

CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeUpdate'(globalConnection,
	"DELETE FROM  tblActor WHERE actorId in (5,6,7,8); "
	+ " DELETE FROM  tblActor WHERE actorId in (1);"	
)

actorData = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeQuery'(globalConnection, "SELECT * FROM tblActor WHERE actorId in (1,5,7,6,8,1)")

println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, "actorName")

println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.isEmptyResult'(actorData)

CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.closeConnection'(globalConnection)


