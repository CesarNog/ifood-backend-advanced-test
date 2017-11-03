
package br.com.ifood.advancedtest.spotify.domain.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "initialPoolSize",
    "afterFilteringSize",
    "afterRelinkingSize",
    "id",
    "type",
    "href"
})
public class Seed {

    @JsonProperty("initialPoolSize")
    private Integer initialPoolSize;
    @JsonProperty("afterFilteringSize")
    private Integer afterFilteringSize;
    @JsonProperty("afterRelinkingSize")
    private Integer afterRelinkingSize;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("href")
    private Object href;

    @JsonProperty("initialPoolSize")
    public Integer getInitialPoolSize() {
        return initialPoolSize;
    }

    @JsonProperty("initialPoolSize")
    public void setInitialPoolSize(Integer initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    @JsonProperty("afterFilteringSize")
    public Integer getAfterFilteringSize() {
        return afterFilteringSize;
    }

    @JsonProperty("afterFilteringSize")
    public void setAfterFilteringSize(Integer afterFilteringSize) {
        this.afterFilteringSize = afterFilteringSize;
    }

    @JsonProperty("afterRelinkingSize")
    public Integer getAfterRelinkingSize() {
        return afterRelinkingSize;
    }

    @JsonProperty("afterRelinkingSize")
    public void setAfterRelinkingSize(Integer afterRelinkingSize) {
        this.afterRelinkingSize = afterRelinkingSize;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("href")
    public Object getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(Object href) {
        this.href = href;
    }

}
