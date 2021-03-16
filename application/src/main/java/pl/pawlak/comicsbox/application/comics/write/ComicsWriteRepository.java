package pl.pawlak.comicsbox.application.comics.write;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ComicsWriteRepository {

    UUID addComics(ComicsWriteData comicsWriteData);

    void addImage(UUID comicsId, MultipartFile file);
}
