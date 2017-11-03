
package br.com.ifood.advancedtest.spotify.domain.openwheater;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "3h"
})
public class Rain {

    @JsonProperty("3h")
    private Integer _3h;

    @JsonProperty("3h")
    public Integer get3h() {
        return _3h;
    }

    @JsonProperty("3h")
    public void set3h(Integer _3h) {
        this._3h = _3h;
    }

}
