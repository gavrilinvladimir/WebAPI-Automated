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
    public void beforeTest() {
    }
    @AfterTest(alwaysRun = true)
    public void afterTest() {
    }
}
