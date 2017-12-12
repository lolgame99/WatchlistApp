package at.mab.watchlistapp;

/**
 * Created by Lucas Huber on 12.12.2017.
 */

public class Series {
    private String synopsis;
    private String id;
    private String header;
    private String poster;
    private String firstAirDate;
    private String[] genres;
    private String lastAirDate;
    private int seasonCount;
    private int episodeCount;
    private String[] productionCompanies;
    private String status;

    public Series(String synopsis, String id, String header, String poster, String firstAirDate, String[] genres, String lastAirDate, int seasonCount, int episodeCount, String[] productionCompanies, String status) {
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

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
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

    public String[] getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String[] productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
