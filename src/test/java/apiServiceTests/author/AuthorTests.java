package apiServiceTests.author;

import apiServiceTests.BaseTest;
import config.Constants;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BaseService;
import service.BookService;
import service.GenreService;

import static org.apache.http.HttpStatus.*;

@Feature("Author Service Tests")
public class AuthorTests extends BaseTest {
    @Test (description = "Positive check for creation an author", groups = {"clean_author"})
    public void verifyPostCreateAuthor () {
        authorService
                .createAuthor(Constants.author,Constants.authorRow, Constants.authorPositive);
        validator
                .validateStatusCode(BaseService.rawResponse.getStatusCode(),SC_CREATED);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.author, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateAuthorResponse(responseCheckEntity,AuthorService.body);
    }

    @Test (description = "Negative check for creation an author")
    public void verifyPostErrorCreateAuthor () {
        BaseResponse responseCheckEntity = authorService
                .createAuthor(Constants.author,Constants.authorRow, Constants.authorNegative);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_BAD_REQUEST);
    }

    @Test (description = "Positive check for update author", groups={"require_author"})
    public void verifyPutUpdateAuthor () {
        BaseResponse responseUpdateEntity = authorService
                .updateAuthor(Constants.author,Constants.authorUpdateRow, AuthorService.authorId, Constants.authorPositive);
        validator
                .validateStatusCode(responseUpdateEntity.getStatusCode(),SC_OK);

        BaseResponse responseCheckAuthor = baseService.getEntityById(Constants.author, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckAuthor.getStatusCode(),SC_OK)
                .validateAuthorResponse(responseCheckAuthor,AuthorService.body);
    }

    @Test (description = "Positive check for delete author", groups={"require_author"})
    public void verifyDeleteAuthor () {
        baseService
                .removeEntityForcibly(Constants.author, AuthorService.authorId, Constants.forcibly);

        BaseResponse responseCheckEntity = baseService.getEntityById(Constants.author, AuthorService.authorId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_NOT_FOUND);
    }

    @Test (description = "Positive check for get all authors with queryParameters", groups={"require_author"})
    public void verifyGetAllAuthorsQueryParams () {
        BaseResponse responseGetEntities = baseService
                .getAllEntitiesWithOptions(Constants.authors, Constants.queryAuthor);
        validator
                .validateStatusCode(responseGetEntities.getStatusCode(),SC_OK)
                .validateMultipleAuthorsResponseById(responseGetEntities,AuthorService.body);
    }

    @Test (description = "Positive check for search authors", groups={"require_author"})
    public void verifyGetSearchAuthors () {
        BaseResponse responseSearchEntities = baseService
                .getAllEntitiesWithSearch(Constants.authors, Constants.searchAuthor, Constants.search);
        validator
                .validateStatusCode(responseSearchEntities.getStatusCode(),SC_OK)
                .validateMultipleAuthorsResponseByName(responseSearchEntities);
    }

    @Test (description = "Positive check for get Author of special Book", groups={"require_full_cleanup"})
    public void verifyGetAuthorsByBook () {
        BaseResponse responseCheckEntity = baseService
                .getAllEntitiesWithinAnother(Constants.author,Constants.book, BookService.bookId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateAuthorResponse(responseCheckEntity,AuthorService.body);
    }

    @Test (description = "Positive check for get all Authors in special Genre", groups={"require_full_cleanup"})
    public void verifyGetAuthorsByAuthorByGenre () {
        BaseResponse responseCheckEntity = baseService
                .getAllEntitiesWithinAnother(Constants.authors,Constants.genre,GenreService.genreId);
        validator
                .validateStatusCode(responseCheckEntity.getStatusCode(),SC_OK)
                .validateMultipleAuthorsResponseById(responseCheckEntity,AuthorService.body);
    }
}
