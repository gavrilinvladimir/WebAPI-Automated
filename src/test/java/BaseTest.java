import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.*;

public class BaseTest {

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
