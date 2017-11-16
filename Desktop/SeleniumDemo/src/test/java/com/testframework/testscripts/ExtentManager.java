package com.testframework.testscripts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.HTMLReporter;


public class ExtentManager{
	public static ExtentReports extent;
	public static ExtentTest test;
	public String br;  
	//private static ExtentHtmlReporter htmlReporter;
	private static String fileName = new SimpleDateFormat("yyyyMMddHHmm'.html'").format(new Date());
	private static String filePath = "./results/reports/HTMLReport/ExtentReport_" + fileName;
	
	public static ExtentReports GetExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports(filePath);		
                extent.loadConfig(new File("./Resource/extent-config.xml"));
		return extent;
	}

	@SuppressWarnings("unused")
	private static HTMLReporter getHtmlReporter() {
	
		HTMLReporter htmlReporter = new HTMLReporter(filePath);
		
	// make the charts visible on report open
       // htmlReporter.getSystemInfoMap().setChartVisibilityOnOpen(true);
        htmlReporter.getSystemInfo();
        extent.addSystemInfo("Browser","Chrome");
        //htmlReporter.config().setDocumentTitle("QA Automation Report");
       // htmlReporter.config().setReportName("Regression Test - Release 1");
        return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.startTest(name, description);
		return test;
	}
	
}
	

