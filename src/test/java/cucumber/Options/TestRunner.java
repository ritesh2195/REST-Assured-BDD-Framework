package cucumber.Options;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

@RunWith(Cucumber.class)
@CucumberOptions(/*plugin= {"pretty",
        "html:out/cucumber-html-report","json:out/cucumber-report.json",
        "usage:target/cucumber-usage.json",
        "junit:target/cucumber-results.xml",
        "com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:"}
        ,*/features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        tags = "@AddPlace")
public class TestRunner {
  /*  @BeforeClass
    public static void setup() {

        LocalDateTime date = LocalDateTime.now();

        String fileName    = "target/report_";

        fileName           = fileName+date.toString().replace("-","_").replace(":","_")+ ".html";

        ExtentProperties extentProperties = ExtentProperties.INSTANCE;

        extentProperties.setReportPath(fileName);
    }

    @AfterClass
    public static void teardown() {

        Reporter.setSystemInfo("user", System.getProperty("user.name"));

        Reporter.setSystemInfo("OS", "Windows 10");

        Reporter.setSystemInfo("JAVA Version", "13");

        Reporter.setSystemInfo("Browser name", "Chrome");

        Reporter.setSystemInfo("Browser version", "85");

        Reporter.setTestRunnerOutput("Sample test runner output message");


    }*/

}
