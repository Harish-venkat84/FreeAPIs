package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "./Feature",

        tags = ("@successfulRegistration or @loginValidCredentials"),

//		dryRun = true,

        monochrome = true,

        glue = "com.steps",

//		publish = true,
        plugin = { "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },

        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class TestRunner  extends AbstractTestNGCucumberTests {
}
