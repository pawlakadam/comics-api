package pl.pawlak.comicsbox.presentation.comics.read.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * Date: 17.04.19
 * Time: 14:26
 *
 * @author apawlak
 */
public class ComicsDataResource extends ResourceSupport {
    private String comicsId;
    private String title;
    private String publishingHouse;
    private Long comicsNumber;
    private byte[] fileContent;

    public ComicsDataResource(String comicsId, String title, String publishingHouse, Long comicsNumber, byte[] fileContent) {
        this.comicsId = comicsId;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.comicsNumber = comicsNumber;
        this.fileContent = fileContent;
    }

    public String getComicsId() {
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

    public byte[] getFileContent() {
        return fileContent;
    }
}
