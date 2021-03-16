package pl.pawlak.comicsbox.infrastructure.comics.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "comics", type = "com")
public class ComicsElasticDocument {

    @Id
    private UUID comicsId;

    private String title;

    private String publishingHouse;

    private Long comicsNumber;

    private String imageBase64;

     ComicsElasticDocument() {
    }

    public ComicsElasticDocument(UUID comicsId, String title, String publishingHouse, Long comicsNumber) {
        this.comicsId = comicsId;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.comicsNumber = comicsNumber;
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

    public Long getComicsNumber() {
        return comicsNumber;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
