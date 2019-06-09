package com.katalon.plugin.keyword.generator
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Element

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.katalon.plugin.keyword.connection.DBType
import com.katalon.plugin.keyword.connection.DatabaseKeywords


public class DataFileKeywords {

	/**
	 * This keyword returns a data file with using global connection
	 * Using global connection that setting in Project > Settings > Database
	 * @param fileName	The title of data file
	 * @param query		PostgresSql query
	 * @return			A data File
	 */
	@Keyword
	def createUsingGlobalConnection(String fileName,String query) {
		//create file path for data file
		String filePath = RunConfiguration.getProjectDir()+ "/Data Files"+"/"+fileName+".dat"

		//Create a file

		create(filePath)

		//Create element

		createDataFileEntity(filePath, fileName, generateGUIID(), query,true,"","","")

	}

	/**
	 * This keyword returns a data file with using internal connection
	 * @param fileName	Title of data file
	 * @param query		PostgresSql query
	 * @param user		The database user
	 * @param password	The database user's password.
	 * @param dbType	Database Type ex: mysql, sqlserver, oracle, postgres
	 * @param host		The host name of the server
	 * @param port		The port number the server
	 * @param dbName	The database name
	 * @return 			A Data File
	 */
	@Keyword
	def createUsingInternalConnection(String fileName, String query, String user, String password, DBType dbType,String host,String port,String dbName) {

		//create file path for data file
		String filePath = RunConfiguration.getProjectDir()+ "/Data Files"+"/"+fileName+".dat"

		//Create a file
		create(filePath)

		// Create element
		DatabaseKeywords db =new DatabaseKeywords()
		String dataSourceUrl = db.getConnectionUrl(dbType, host, port, dbName)
		println db.base64Decode(password)

		createDataFileEntity(filePath, fileName, generateGUIID(),query,false,user, password,dataSourceUrl)

	}


	/*-------------------------------Start Utilities------------------------------------------------*/

	//Create a file
	public void create(String filePath){
		try {

			File file = new File(filePath)

			if (file.createNewFile()){
				System.out.println("Created successfully!")
			}
			else{
				System.out.println("File already exists.")

			}
		}catch (IOException e) {

			e.printStackTrace();

		}
	}

	public String generateGUIID(){

		//Generate random GUIID
		return UUID.randomUUID().toString()

	}

	public void createDataFileEntity(String filePath, String fileName, String guiID ,String query, Boolean isGlobal, String user, String password,String dataSourceUrl){
		// Create element in file
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance()

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder()

			Document document = documentBuilder.newDocument()

			// root element
			Element root = document.createElement("DataFileEntity")
			document.appendChild(root)

			// description element
			Element description = document.createElement("description")
			root.appendChild(description)

			// name element
			Element name = document.createElement("name")
			name.appendChild(document.createTextNode(fileName))
			root.appendChild(name)

			// tag element
			Element tag = document.createElement("tag")
			root.appendChild(tag)

			// containsHeaders element
			Element email = document.createElement("containsHeaders")
			root.appendChild(email)

			// csvSeperator element
			Element csvSeperator = document.createElement("csvSeperator")
			root.appendChild(csvSeperator)

			// dataFileGUID elements
			Element dataFileGUID = document.createElement("dataFileGUID")
			dataFileGUID.appendChild(document.createTextNode(guiID))
			root.appendChild(dataFileGUID)

			// dataSourceUrl elements
			Element dataUrl = document.createElement("dataSourceUrl")
			root.appendChild(dataUrl)

			// driver elements
			Element driver = document.createElement("driver")
			driver.appendChild(document.createTextNode("DBData"))
			root.appendChild(driver)

			// isInternalPath elements
			Element isInternalPath = document.createElement("isInternalPath")
			isInternalPath.appendChild(document.createTextNode("false"))
			root.appendChild(isInternalPath)

			// password elements
			Element pass = document.createElement("password")
			root.appendChild(pass)

			// query elements
			Element qry = document.createElement("query")
			qry.appendChild(document.createTextNode(query))
			root.appendChild(qry)

			// secureUserAccount elements
			Element secureUserAccount = document.createElement("secureUserAccount")
			root.appendChild(secureUserAccount)

			// sheetName elements
			Element sheetName = document.createElement("sheetName")
			root.appendChild(sheetName)

			// user elements
			Element userName = document.createElement("user")
			root.appendChild(userName)

			// usingGlobalDBSetting elements
			Element usingGlobalDBSetting = document.createElement("usingGlobalDBSetting")
			String strGlobalCon = isGlobal.toString();
			usingGlobalDBSetting.appendChild(document.createTextNode(strGlobalCon))
			root.appendChild(usingGlobalDBSetting)

			if(isGlobal==false){

				userName.appendChild(document.createTextNode(user))
				root.appendChild(userName)

				pass.appendChild(document.createTextNode(password))
				root.appendChild(pass)

				dataUrl.appendChild(document.createTextNode(dataSourceUrl))
				root.appendChild(dataUrl)

				secureUserAccount.appendChild(document.createTextNode("true"))
				root.appendChild(secureUserAccount)

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance()

			Transformer transformer = transformerFactory.newTransformer()

			DOMSource domSource = new DOMSource(document)

			StreamResult streamResult = new StreamResult(new FileOutputStream(filePath))

			transformer.transform(domSource, streamResult)

		}catch (ParserConfigurationException pce) {

			pce.printStackTrace()

		} catch (TransformerException tfe) {

			tfe.printStackTrace()

		}

	}

	/*-------------------------------End Utilities------------------------------------------------*/

}
