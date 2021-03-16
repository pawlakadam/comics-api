package pl.pawlak.comicsbox.presentation.comics.read.resource;

import org.springframework.hateoas.ResourceSupport;

public class ImageResource extends ResourceSupport {
    private String fileContent;

    public ImageResource(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileContent() {
        return fileContent;
    }
}
