package apiServiceTests.author;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BaseService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;

@Feature("Author Service Tests")
public class AuthorTests extends BaseTestAuthor {
    @Test (description = "Positive check for creation an author", groups={"require_cleanup"})
    public void verifyPostCreateAuthor () {
        authorService
                .createAuthor(entityAuthor,testAuthorRow, "authorPositive.csv");
        validator
                .validateStatusCode(BaseService.rawResponse,SC_CREATED);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityAuthor, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateAuthorResponse(responseCheckEntity);
    }

    @Test (description = "Negative check for creation an author")
    public void verifyPostErrorCreateAuthor () {
        authorService
                .createAuthor(entityAuthor,testAuthorRow, "authorNegative.csv");
        validator
                .validateStatusCode(BaseService.rawResponse,SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update author", groups={"require_cleanup"})
    public void verifyPutUpdateAuthor () {
        authorService
                .createAuthor(entityAuthor,testAuthorRow, "authorPositive.csv");

        BaseResponse responseUpdateEntity = authorService.updateAuthor(entityAuthor,testAuthorUpdateRow, AuthorService.authorId, "authorPositive.csv");
        validator
                .validateStatusCode(responseUpdateEntity,SC_OK);

        BaseResponse responseCheckEntity = baseService.getEntityById(entityAuthor, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateAuthorResponse(responseCheckEntity);
    }

    @Test (description = "Positive check for delete author")
    public void verifyDeleteAuthor () {
        authorService
                .createAuthor(entityAuthor,testAuthorRow, "authorPositive.csv");

        baseService
                .removeEntityForcibly(entityAuthor, AuthorService.authorId, "false");

        BaseResponse responseCheckEntity = baseService.getEntityById(entityAuthor, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity,SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all authors with queryParameters", groups={"require_cleanup"})
    public void verifyGetAllAuthorsQueryParams () {
        authorService.
                createAuthor(entityAuthor,testAuthorRow, "authorPositive.csv");
        BaseResponse responseGetEntities = baseService.getAllEntitiesWithOptions(entityAuthors, "testData/queryOptionsAuthor.json");
        validator
                .validateStatusCode(responseGetEntities,SC_OK)
                .validateMultipleAuthorsResponseById(responseGetEntities);
    }

    @Test (description = "Positive check for search authors", groups={"require_cleanup"})
    public void verifyGetSearchAuthors () {
        authorService.
                createAuthor(entityAuthor,testAuthorUpdateRow, "authorPositive.csv");

        BaseResponse responseSearchEntities = baseService.getAllEntitiesWithSearch(entityAuthors, "testData/searchAuthor.json", search);
        validator
                .validateStatusCode(responseSearchEntities,SC_OK)
                .validateMultipleAuthorsResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get Author of special Book", groups={"require_full_cleanup"})
    public void verifyGetAuthorsByBook () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(entityAuthor,entityBook, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateAuthorResponse(responseCheckEntity);
    }

    @Test (description = "Positive check for get all Authors in special Genre", groups={"require_full_cleanup"})
    public void verifyGetAuthorsByAuthorByGenre () {
        BaseResponse responseCheckEntity = baseService.getAllEntitiesWithinAnother(entityAuthors,entityGenre,GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity,SC_OK)
                .validateMultipleAuthorsResponseById(responseCheckEntity);
    }

}
