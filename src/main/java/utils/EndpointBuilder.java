package utils;

import config.QueryOptions;

import java.util.HashMap;

public class EndpointBuilder {
    private String endpoint;

    public EndpointBuilder() {
        this.endpoint = "";
    }

    public EndpointBuilder addEntityType(String entity) {
        this.endpoint += "/" + entity;
        return this;
    }

    public EndpointBuilder addEntityId(int entityId) {
        this.endpoint += "/" + entityId;
        return this;
    }

    public EndpointBuilder addQueryOption(String fileName) {
        HashMap <String, Object> queryOptions = new QueryOptions().getQueryOptions(fileName);
        String delimiter;
        for (Object key : queryOptions.keySet()) {
            if (this.endpoint.contains("?")) delimiter = "&";
            else delimiter = "?";
            this.endpoint += delimiter + key + "=" + queryOptions.get(key);
        }
        return this;
    }

    public String get() {
        return this.endpoint;
    }

}
