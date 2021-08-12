
import config.ServicesConfig;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BaseService;
import utils.Validator;

import static org.apache.http.HttpStatus.*;

public class AuthorTests extends BaseTest {

    @Test (description = "positive check for creation an author")
    public void verifyPostCreateAuthor () {
        BaseResponse responseCreateEntity = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),0, "authorPositive.csv");
        Validator.validateStatusCode(responseCreateEntity,SC_CREATED);
        Integer id = AuthorService.authorId;

        BaseResponse responseCheckEntity = new BaseService().getEntityById(ServicesConfig.AUTHOR.getSingular(),id);
        Validator.validateStatusCode(responseCheckEntity,SC_OK);
        Validator.validateAuthorResponse(responseCheckEntity);

        new BaseService().removeEntityForcibly(ServicesConfig.AUTHOR.getSingular(), id, "false");
    }

    @Test (description = "negative check for creation an author")
    public void verifyPostErrorCreateAuthor () {
        BaseResponse responseCreateEntity = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),0, "authorNegative.csv");
        Validator.validateStatusCode(responseCreateEntity,SC_BAD_REQUEST);
    }

    @Test (description = "positive check for update author")
    public void verifyPutUpdateAuthor () {
        BaseResponse responseCreateEntity = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),0, "authorPositive.csv");
        Validator.validateStatusCode(responseCreateEntity,SC_CREATED);
        Integer id = AuthorService.authorId;

        BaseResponse responseUpdateEntity = new AuthorService().updateAuthor(ServicesConfig.AUTHOR.getSingular(),1,id, "authorPositive.csv");
        Validator.validateStatusCode(responseUpdateEntity,SC_OK);

        BaseResponse responseCheckEntity = new BaseService().getEntityById(ServicesConfig.AUTHOR.getSingular(),id);
        Validator.validateStatusCode(responseCheckEntity,SC_OK);
        Validator.validateAuthorResponse(responseCheckEntity);

        new BaseService().removeEntityForcibly(ServicesConfig.AUTHOR.getSingular(), id, "false");
    }

    @Test (description = "positive check for delete an author")
    public void verifyDeleteAuthor () {
        BaseResponse responseCreateEntity = new AuthorService().createAuthor(ServicesConfig.AUTHOR.getSingular(),0, "authorPositive.csv");
        Validator.validateStatusCode(responseCreateEntity,SC_CREATED);
        Integer id = AuthorService.authorId;

        new BaseService().removeEntityForcibly(ServicesConfig.AUTHOR.getSingular(), id, "false");
        Validator.validateStatusCode(responseCreateEntity,SC_CREATED);

        BaseResponse responseCheckEntity = new BaseService().getEntityById(ServicesConfig.AUTHOR.getSingular(),id);
        Validator.validateStatusCode(responseCheckEntity,SC_NOT_FOUND);
    }

    @Test (description = "positive check for get all authors with queryParameters")
    public void verifyGetAllAuthorsQueryParams () {
        BaseResponse response = new BaseService().getAllEntitiesWithOptions(ServicesConfig.AUTHOR.getPlural(), "testData/queryOptions.json");
        Validator.validateStatusCode(response,SC_OK);
        //Validator.validateMultipleAuthorsResponse(response);
    }

    @Test (description = "positive check for search authors with queryParameters")
    public void verifyGetSearchAuthors () {
        BaseResponse response = new BaseService().getAllEntitiesWithSearch(ServicesConfig.AUTHOR.getPlural(), "testData/queryAuthor.json", ServicesConfig.SEARCH.getSingular());
        Validator.validateStatusCode(response,SC_OK);
    }

    @Test (description = "positive check for author by id")
    public void verifyGetAuthorByBook () {
        BaseResponse response = new BaseService().getAllEntitiesWithinAnother(ServicesConfig.AUTHOR.getSingular(),ServicesConfig.BOOK.getSingular(),55);
        Validator.validateStatusCode(response,SC_OK);
    }

    @Test (description = "positive check for author by id")
    public void verifyGetAuthorsByGenre () {
        BaseResponse response = new BaseService().getAllEntitiesWithinAnotherWithOptions(ServicesConfig.AUTHOR.getPlural(),ServicesConfig.GENRE.getSingular(),454, "testData/queryOptions.json");
        Validator.validateStatusCode(response,SC_OK);
    }

}
