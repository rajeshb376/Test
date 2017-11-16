package com.testframework.core;

import java.io.FileInputStream;
import java.util.Properties;



/*************************************************************************************************
 * Class Name		: 	Configuration
 * Description 		: 	This class contains methods to access property files  
 * Author 			: 	QA-Masters
 * Change History	:
 *              Date                     Modified By                       Change Description
 *              *****                     *********                        ***************
 *             22/11/2016                QA-Masters                         Initial Draft
 *
 *************************************************************************************************/

public class Configuration 
{
	private static Properties prop; 

	/******************************************************************
	 * Function Name: getProperty
	 * Description : properties.xml file is accessed in this function
	 ******************************************************************/
	public static String getProperty(String key)
	{
		try{
			if (prop == null)
			{
				prop = new Properties();
				FileInputStream fis = new FileInputStream("resource/properties.xml");
				prop.loadFromXML(fis);
			}
			return prop.getProperty(key);
		}
		catch (Exception ex){
			
			return null;
		}
	}
}
