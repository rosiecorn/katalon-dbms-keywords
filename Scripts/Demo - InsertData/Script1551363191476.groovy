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
	"INSERT INTO  tblActor (ActorID,ActorName,ActorDOB,ActorGender) "
	+ " VALUES (5,'Tom Cruise2','1962-07-03','Male')"
	+ ", (6,'Sam Neill2','1947-09-14','Male')"
	+ ", (7,'Laura Dern2','1967-02-10','Female')"
	+ ", (8,'Jeff Goldblum2','1952-10-22','Male')"
)

actorData = CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.executeQuery'(globalConnection, "SELECT * FROM tblActor")

println CustomKeywords.'com.katalon.plugin.keyword.connection.ResultSetKeywords.getListCellValue'(actorData, "actorName")

CustomKeywords.'com.katalon.plugin.keyword.connection.DatabaseKeywords.closeConnection'(globalConnection)


