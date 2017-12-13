package at.mab.watchlistapp;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Lucas Huber on 12.12.2017.
 */

public class Series {
    private String synopsis;
    private String id;
    private String header;
    private String poster;
    private String firstAirDate;
    private JSONArray genres;
    private String lastAirDate;
    private int seasonCount;
    private int episodeCount;
    private JSONArray productionCompanies;
    private String status;

    public Series(String synopsis, String id, String header, String poster, String firstAirDate, JSONArray genres, String lastAirDate, int seasonCount, int episodeCount, JSONArray productionCompanies, String status) {
        this.synopsis = synopsis;
        this.id = id;
        this.header = header;
        this.poster = poster;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.lastAirDate = lastAirDate;
        this.seasonCount = seasonCount;
        this.episodeCount = episodeCount;
        this.productionCompanies = productionCompanies;
        this.status = status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public JSONArray getGenres() {
        return genres;
    }

    public void setGenres(JSONArray genres) {
        this.genres = genres;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public JSONArray getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(JSONArray productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
