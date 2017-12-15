package at.mab.watchlistapp;

/**
 * Created by Lucas Huber on 15.12.2017.
 */

public class SeriesSmall {
    private String id;
    private String name;

    public SeriesSmall(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
