package com.sanyam.frameWorkPackage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	

	public static ExtentReports createInstance(){
	//	String dateRepott = new SimpleDateFormat("yyyyddmmhhmmss").format(new Date());
		Date d = new Date();
		String fileName = "extentReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		String directory = System.getProperty("user.dir") + "\\Reports\\";
		new File(directory).mkdir();
		String reportDirectory = directory + fileName;
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportDirectory);
		htmlReporter.config().setDocumentTitle("Automation Testing");
		htmlReporter.config().setReportName("Sanyam Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Organization", "Sanyam's Office");
		extent.setSystemInfo("Browser", "Chrome");
		return extent;
		
	}
	
	public static String getScreenshot(WebDriver driver, String methodName) {
		// String dateName = new SimpleDateFormat("yyyyddmmhhmmss").format(new Date());
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String directory = System.getProperty("user.dir") + "\\ScreenShot\\";
		new File(directory).mkdir();
		String srcFilePath = directory + fileName;

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(srcFilePath);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return srcFilePath;

	}

}