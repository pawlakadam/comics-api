package pl.pawlak.comicsbox.infrastructure.comics.read;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadRepository;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsReadEntity;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Repository
public class ComicsReadRepositoryImpl implements ComicsReadRepository {

    private ComicsCrudRepository comicsCrudRepository;

    public ComicsReadRepositoryImpl(ComicsCrudRepository comicsCrudRepository) {
        this.comicsCrudRepository = comicsCrudRepository;
    }

    @Override
    public ComicsBasicData getById(UUID comicsUid) {
        ComicsReadEntity comicsReadEntity = comicsCrudRepository.findById(comicsUid).orElseThrow();
        return convert(comicsReadEntity);
    }

    @Override
    public byte[] getImageByComicsId(UUID comicsId) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\Users\\Adam\\Desktop\\picture\\" + comicsId));
            return fileContent;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private ComicsBasicData convert(ComicsReadEntity comicsReadEntity) {
        return new ComicsBasicData(comicsReadEntity.getComicsId(), comicsReadEntity.getTitle(), comicsReadEntity.getPublishingHouse(), comicsReadEntity.getNumber(), null);
    }
}
