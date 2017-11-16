package com.testframework.testdata;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.testframework.core.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/*******************************************************************************************
 * Class Name		: TestDataProvider
 * Description 		: This class contains different ways of getting test data
 * Author 			: QA-Masters
 * Change History	:
 *             Date                 Modified By                   Change Description
 *             *****                  *********                     ***************
 *            NOV-2017                QA-Masters                     Initial Draft
 *
 ******************************************************************************************/

public class TestDataProvider 
{

	/*****************************************************************************
	 * Function Name: getTestData
	 * Description : Checking the type of test data format in this function
	 *****************************************************************************/
	@DataProvider (name="TestData")
	public static Object [][] getTestData (ITestContext context)
	{
		TestDataProvider testDataProvider = new TestDataProvider();

		String testDataSource = Configuration.getProperty("TestDataSource");
		if (testDataSource.equalsIgnoreCase("excel"))
		{
			return testDataProvider.readExcelData(context);
		}
		else if(testDataSource.equalsIgnoreCase("xml"))
		{
			return testDataProvider.readXmlData(context);
		}
		else
		{
			
			return null;
		}
	}

	/*************************************************************************
	 * Function Name: readExcelData
	 * Description : Test data is taken in excel format  in this function
	 *************************************************************************/
	private Object[][] readExcelData(ITestContext context) 
	{
		try
		{
			FileInputStream fis = new FileInputStream(Configuration.getProperty("TestDataFileLocation"));

			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(context.getName()));
			fis.close();

			int dataLength = sheet.getPhysicalNumberOfRows();

			Object[][] testData = new Object[dataLength-1][1];

			for (int intRowIterator = 1; intRowIterator < dataLength; intRowIterator++) 
			{
				HSSFRow headerRow = sheet.getRow(0);
				HSSFRow dataRow = sheet.getRow(intRowIterator);
				HashMap<String,String> tmpHashmap = new HashMap<String, String>();

				for (int intColIterator = 0; intColIterator < dataRow.getPhysicalNumberOfCells(); intColIterator++) {
					if (dataRow.getCell(intColIterator) != null) {

						tmpHashmap.put(headerRow.getCell(intColIterator).toString().trim(), dataRow.getCell(intColIterator).toString().trim());
					}
				}
				testData[intRowIterator-1] [0] = tmpHashmap;
			}
			return testData;
		}
		catch (Exception ex)
		{
			
			return null;
		}		
	}

	/**********************************************************************
	 * Function Name: readExcelData
	 * Description : Test data is taken in xml format  in this function
	 **********************************************************************/
	private Object[][] readXmlData(ITestContext context) 
	{
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(Configuration.getProperty("TestDataFileLocation")));
			doc.getDocumentElement().normalize();

			XPath xPath =  XPathFactory.newInstance().newXPath();

			String expression = "//testcase[@id='" + context.getName() + "']/iteration";	        
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			int dataLength = nodeList.getLength();
			Object[][] testData = new Object[dataLength][1];

			for (int nodeIterator = 0; nodeIterator < nodeList.getLength() ; nodeIterator++) 
			{
				Element nNode = (Element) nodeList.item(nodeIterator);
				HashMap<String,String> tmpHashmap = new HashMap<String, String>();
				for (int keyIterator = 0; keyIterator<= nNode.getElementsByTagName("entry").getLength()-1;keyIterator++)
				{
					Element textNode = (Element) nNode.getElementsByTagName("entry").item(keyIterator);
					tmpHashmap.put(textNode.getAttribute("key"), textNode.getTextContent());
				}
				testData[nodeIterator][0] = tmpHashmap;
			}
			return testData;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return null;
	}
}