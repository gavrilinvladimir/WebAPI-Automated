package service;

import client.HttpClient;
import org.apache.log4j.Logger;
import response.BaseResponse;
import utils.EndpointBuilder;

public class BaseService {
    private final Logger LOG = Logger.getLogger(BaseService.class);

    public BaseResponse getAllEntities(String entity) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        LOG.info(String.format("Endpoint is: %s",endpoint));
        return HttpClient.get(endpoint);
    }

    public BaseResponse getEntityById(String entity, int entityId) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        LOG.info(String.format("Endpoint is: %s",endpoint));
        return HttpClient.get(endpoint);
    }

    public BaseResponse removeEntity(String entity, int entityId) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        LOG.info(String.format("Endpoint is: %s",endpoint));
        return HttpClient.delete(endpoint);
    }

    public BaseResponse removeEntityForcibly(String entity, int entityId, String forciblyValue) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(entityId).get();
        endpoint += String.format("?/forcibly=%s",forciblyValue);
        LOG.info(String.format("Endpoint is: %s",endpoint));
        return HttpClient.delete(endpoint);
    }

    public BaseResponse getAllEntitiesWithOptions(String entity, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addQueryOption(fileName).get();
        return HttpClient.get(endpoint);
    }

    public BaseResponse getAllEntitiesWithSearch(String entity, String fileName, String search) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityType(search).addQueryOption(fileName).get();
        return HttpClient.get(endpoint);
    }

    public BaseResponse getAllEntitiesWithinAnother (String entity, String byEntity, int byEntityId) {
        String endpoint = new EndpointBuilder().addEntityType(byEntity).addEntityId(byEntityId).addEntityType(entity).get();
        return HttpClient.get(endpoint);
    }

    public BaseResponse getAllEntitiesWithinAnotherWithOptions (String entity, String byEntity, int byEntityId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(byEntity).addEntityId(byEntityId).addEntityType(entity).addQueryOption(fileName).get();
        return HttpClient.get(endpoint);
    }
}
