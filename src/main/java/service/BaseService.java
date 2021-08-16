package service;

import client.HttpClient;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.TestAttachments;


public class BaseService {
    public static BaseResponse rawResponse;
    public static TestAttachments testAttachments = new TestAttachments();

    private final Logger LOG = Logger.getLogger(BaseService.class);
    public BaseResponse getAllEntities(String entity) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    @Step ("Get {entity} by id={entityId}")
    public BaseResponse getEntityById(String entity, int entityId) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    @Step ("Remove {entity} with id={entityId}")
    public BaseResponse removeEntity(String entity, int entityId) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        rawResponse=HttpClient.delete(endpoint);
        return rawResponse;
    }

    @Step ("Remove {entity} with id={entityId}")
    public BaseResponse removeEntityForcibly(String entity, int entityId, String forciblyValue) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        endpoint += String.format("?forcibly=%s",forciblyValue);
        rawResponse=HttpClient.delete(endpoint);
        return rawResponse;
    }

    @Step ("Get {entity} using query options: {fileName}")
    public BaseResponse getAllEntitiesWithOptions(String entity, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addQueryOption(fileName).get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    @Step ("Get {entity} using Search: {fileName}")
    public BaseResponse getAllEntitiesWithSearch(String entity, String fileName, String search) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityType(search).addQueryOption(fileName)
                .get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    @Step ("Get {entity} by {byEntity} with id={byEntityId}")
    public BaseResponse getAllEntitiesWithinAnother (String entity, String byEntity, int byEntityId) {
        String endpoint = new EndpointBuilder()
                .addEntityType(byEntity)
                .addEntityId(byEntityId)
                .addEntityType(entity)
                .get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    @Step ("Get {entity} by {byEntity} with id={byEntityId} of {ofEntity} with id={ofEntityId}")
    public BaseResponse getAllEntitiesWithinTwoAnother (String entity, String byEntity, int byEntityId, String ofEntity, int ofEntityId) {
        String endpoint = new EndpointBuilder()
                .addEntityType(byEntity)
                .addEntityId(byEntityId)
                .addEntityType(ofEntity)
                .addEntityId(ofEntityId)
                .addEntityType(entity)
                .get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }

    public BaseResponse getAllEntitiesWithinAnotherWithOptions (String entity, String byEntity, int byEntityId,
                                                                String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(byEntity).addEntityId(byEntityId).addEntityType(entity)
                .addQueryOption(fileName).get();
        rawResponse=HttpClient.get(endpoint);
        return rawResponse;
    }
}
