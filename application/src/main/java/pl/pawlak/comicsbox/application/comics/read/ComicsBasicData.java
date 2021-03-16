package pl.pawlak.comicsbox.application.comics.read;

import java.util.UUID;

public class ComicsBasicData {
    private UUID comicsId;
    private String title;
    private String publishingHouse;
    private Long number;
    private byte[] fileConetnt;

    public ComicsBasicData(UUID comicsId, String title, String publishingHouse, Long number, byte[] fileConetnt) {
        this.comicsId = comicsId;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.number = number;
        this.fileConetnt = fileConetnt;
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

    public byte[] getFileConetnt() {
        return fileConetnt;
    }
}
