package org.shijinglu.asrc.service.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class GetConfigRequest {

    @JsonProperty("context")
    private Map<String, Object> context;

    @JsonProperty("namespace")
    private String namespace;

    public Map<String, Object> getContext() {
        return context;
    }

    public String getNamespace() {
        return namespace;
    }
}
