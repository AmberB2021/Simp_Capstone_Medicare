package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentManager {
	
public static final ExtentReports extentReports = new ExtentReports();
	
    public synchronized static ExtentReports createExtentReports() {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./test-output/extent-reports/extent-report.html");
        reporter.config().setReportName("Medicare Test Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Medi Capstone");
        extentReports.setSystemInfo("Auth", "AmberB");
        return extentReports;
    }

}