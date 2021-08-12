package utils;

import models.author.Author;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import response.BaseResponse;
import service.AuthorService;
import java.util.List;

public class Validator {
    private static final Logger LOG = Logger.getLogger(Validator.class);
    public static void validateStatusCode (BaseResponse response, int expectedCode) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,expectedCode,String.format("\nStatus code is: '%s'.\nResponse: %s", statusCode,response.getBody()));
        LOG.info("Status code is: "+statusCode);
        LOG.info("Response: "+response.getBody());
    }

    public static void validateAuthorResponse (BaseResponse response) {
        Author testAuthor = AuthorService.body;
        Author responseAuthor = response.getAsAuthorClass();
        SoftAssert soft = new SoftAssert();

        Integer actualAuthorId = responseAuthor.getAuthorId();
        String actualAuthorNameFirst = responseAuthor.getAuthorName().getFirst();
        String actualAuthorNameSecond = responseAuthor.getAuthorName().getSecond();
        String actualNationality = responseAuthor.getNationality();
        String actualBirthDate = responseAuthor.getBirth().getDate();
        String actualBirthCountry = responseAuthor.getBirth().getCountry();
        String actualBirthCity = responseAuthor.getBirth().getCity();
        String actualAuthorDescription = responseAuthor.getAuthorDescription();

        Integer expectedAuthorId = testAuthor.getAuthorId();
        String expectedAuthorNameFirst = testAuthor.getAuthorName().getFirst();
        String expectedAuthorNameSecond = testAuthor.getAuthorName().getSecond();
        String expectedNationality = testAuthor.getNationality();
        String expectedBirthDate = testAuthor.getBirth().getDate();
        String expectedBirthCountry = testAuthor.getBirth().getCountry();
        String expectedBirthCity = testAuthor.getBirth().getCity();
        String expectedAuthorDescription = testAuthor.getAuthorDescription();

        soft.assertEquals(actualAuthorId,expectedAuthorId,String.format("\n'authorId' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualAuthorNameFirst,expectedAuthorNameFirst,String.format("\n'authorName'.'first' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualAuthorNameSecond,expectedAuthorNameSecond,String.format("\n'authorName'.'second' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualNationality,expectedNationality,String.format("\n'nationality' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualBirthDate,expectedBirthDate,String.format("\n'birth'.'date' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualBirthCountry,expectedBirthCountry,String.format("\n'birth'.'country' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualBirthCity,expectedBirthCity,String.format("\n'birth'.'city' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertEquals(actualAuthorDescription,expectedAuthorDescription,String.format("\n'authorDescription' Expected: %s.Actual: %s",expectedAuthorId,actualAuthorId));
        soft.assertAll();
        LOG.info("Created Author is correct");
    }

    public static void validateMultipleAuthorsResponse (BaseResponse response) {
        List<Author> responseAuthor = response.getAsAuthorClassArray();
        System.out.println(responseAuthor);
    }
}
