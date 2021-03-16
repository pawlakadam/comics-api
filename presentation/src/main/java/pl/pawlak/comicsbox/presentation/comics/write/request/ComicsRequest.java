package pl.pawlak.comicsbox.presentation.comics.write.request;

import java.io.Serializable;

public class ComicsRequest implements Serializable {

    private String title;
    private String publishingHouse;
    private Long comicsNumber;

    public ComicsRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Long getComicsNumber() {
        return comicsNumber;
    }
}
