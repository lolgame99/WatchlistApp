package at.mab.watchlistapp;

/**
 * Created by Lucas Huber on 28.11.2017.
 */

public class SeriesBig {
    private String synopsis;
    private String poster;
    private String name;

    public SeriesBig(String synopsis, String poster, String name) {
        this.synopsis = synopsis;
        this.poster = poster;
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
