package pl.pawlak.comicsbox.application.comics.write;

import java.util.UUID;

public class ComicsWriteData {
    private UUID comicsId;
    private String title;
    private String publishingHouse;
    private Long number;

    public ComicsWriteData(UUID comicsId, String title, String publishingHouse, Long number) {
        this.comicsId = comicsId;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.number = number;
    }

    public UUID getComicsId() {
        return comicsId;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Long getNumber() {
        return number;
    }
}
