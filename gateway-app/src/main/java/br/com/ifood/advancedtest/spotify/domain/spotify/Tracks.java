
package br.com.ifood.advancedtest.spotify.domain.spotify;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tracks",
    "seeds"
})
public class Tracks {

    @JsonProperty("tracks")
    private List<Track> tracks = null;
    @JsonProperty("seeds")
    private List<Seed> seeds = null;

    @JsonProperty("tracks")
    public List<Track> getTracks() {
        return tracks;
    }

    @JsonProperty("tracks")
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @JsonProperty("seeds")
    public List<Seed> getSeeds() {
        return seeds;
    }

    @JsonProperty("seeds")
    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

}
