# Katalon Market Places

### Prerequisites

- Installed Katalon Studio


### How to use the key word "createUsingGlobalConnection(String fileName,String query)"
Go to Project > Settings > Database to create a connection to database.
Prepare two input values: 
							+ fileName: The title of data file
							+ query: PostgresSql query
Return value : a data file 

Example: 
CustomKeywords.'generator.DataFile.createUsingGlobalConnection'('Actor Data','Select * from tblactor where actorid=1')

### How to use the key word "createUsingInternalConnection(String fileName, String query, String user, String password, String dbType,String host,String port,String dbName)"

Prepare input values: 
							+ fileName	Title of data file
							+ query		PostgresSql query
							+ user		The database user
							+ password	The database user's password.
							+ dbType	Database Type
							+ host		The host name of the server
							+ port		The port number the server
							+ dbName	The database name

Return value : a data file 

Example:
CustomKeywords.'generator.DataFile.createUsingInternalConnection'('Films Data', 'Select * from tblfilm','postgres','a21zQDIwMTk=','postgresql','localhost','5432','Movies')
