import config.HttpCodes;
import config.ServicesConfig;
import models.BaseModel;
import models.author.Author;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BaseService;
import utils.CsvReader;
import utils.Validator;

import java.util.LinkedList;

import static org.apache.http.HttpStatus.*;

public class AuthorTests extends BaseTest {
    private Logger LOG = Logger.getLogger(BaseTest.class);

    @Test (description = "positive check for creation an author")
    public void verifyPostCreateAuthor () {
        BaseResponse responseCreateEntity = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),0, "authorPositive.csv");
        Validator.validateStatusCode(responseCreateEntity,SC_CREATED);
        Integer id = new AuthorService().authorId;

        BaseResponse responseCheckEntity = new BaseService().getEntityById(ServicesConfig.AUTHOR.getSingular(),id);
        Validator.validateStatusCode(responseCheckEntity,SC_OK);
        Validator.validateAuthorResponse(responseCheckEntity);

        new BaseService().removeEntityForcibly(ServicesConfig.AUTHOR.getSingular(), id, "false");
    }

    @Test (description = "negative check for creation an author")
    public void verifyPostErrorCreateAuthorNoName () {
        BaseResponse response = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),14, "requestsSamples/postAuthorNoName.json");
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== SC_BAD_REQUEST,String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info("Status code is: %s"+statusCode);
        LOG.info("Response: %s"+response.getBody());
    }

    @Test (description = "positive check for update author")
    public void verifyPutUpdateAuthor () {
        new AuthorService().createAuthor("author",16, "requestsSamples/postAuthor.json");
        //BaseResponse response = new AuthorService().updateAuthor(ServicesConfig.AUTHOR.getSingular(),16, "requestsSamples/postAuthor.json", "first", "NewFirst");
        //int statusCode = response.getStatusCode();
        //Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        //LOG.info(String.format("Status code is: %s",statusCode));
        //LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for author by id")
    public void verifyGetAuthorById () {
        BaseResponse response = new BaseService().getEntityById(ServicesConfig.AUTHOR.getSingular(),16);
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for delete an author")
    public void verifyDeleteAuthor () {
        BaseResponse response = new BaseService().removeEntityForcibly(ServicesConfig.AUTHOR.getSingular(), 11, "false");
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS204.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for get all authors with queryParameters")
    public void verifyGetAllAuthorsQueryParams () {
        BaseResponse response = new BaseService().getAllEntitiesWithOptions(ServicesConfig.AUTHOR.getPlural(), "testData/queryOptions.json");
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for search authors with queryParameters")
    public void verifyGetSearchAuthors () {
        BaseResponse response = new BaseService().getAllEntitiesWithSearch(ServicesConfig.AUTHOR.getPlural(), "testData/queryAuthor.json", ServicesConfig.SEARCH.getSingular());
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for author by id")
    public void verifyGetAuthorByBook () {
        BaseResponse response = new BaseService().getAllEntitiesWithinAnother(ServicesConfig.AUTHOR.getSingular(),ServicesConfig.BOOK.getSingular(),55);
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

    @Test (description = "positive check for author by id")
    public void verifyGetAuthorsByGenre () {
        BaseResponse response = new BaseService().getAllEntitiesWithinAnotherWithOptions(ServicesConfig.AUTHOR.getPlural(),ServicesConfig.GENRE.getSingular(),454, "testData/queryOptions.json");
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode== HttpCodes.SUCCESS200.getCode(),String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info(String.format("Status code is: %s",statusCode));
        LOG.info(String.format("Response: %s",response.getBody()));
    }

}
