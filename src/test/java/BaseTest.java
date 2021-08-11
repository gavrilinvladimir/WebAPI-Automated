import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseTest {

    private Logger LOG = Logger.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        BasicConfigurator.configure();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(final ITestContext testContext) {
        LOG.info(String.format("Test Case started: %s",testContext.getName()));
    }
    @AfterTest(alwaysRun = true)
    public void afterTest(final ITestContext testContext) {
        LOG.info(String.format("Test Case ended: %s",testContext.getName()));
    }
}
