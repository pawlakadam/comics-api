package pl.pawlak.comicsbox.infrastructure.comics.write;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteData;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteRepository;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsElasticDocument;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Repository
public class ComicsWriteRepositoryImpl implements ComicsWriteRepository {

    private ComicsElasticSearchRepository elasticRepository;

    public ComicsWriteRepositoryImpl(ComicsElasticSearchRepository elasticRepository) {
        this.elasticRepository = elasticRepository;
    }

    @Override
    public UUID addComics(ComicsWriteData comicsWriteData) {
        ComicsElasticDocument comicsElasticDocument = new ComicsElasticDocument(comicsWriteData.getComicsId(), comicsWriteData.getTitle(),
                comicsWriteData.getPublishingHouse(), comicsWriteData.getNumber());
        elasticRepository.save(comicsElasticDocument);
        return comicsElasticDocument.getComicsId();
    }

    @Override
    public void addImage(UUID comicsId, MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") - 1);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Adam\\Desktop\\picture\\" + comicsId + extension);
            try {
                fos.write(bytes);
            } finally {
                fos.close();
            }
//            String baseImage = Base64.getEncoder().encodeToString(bytes);
//            ComicsElasticDocument comicsElasticDocument = elasticRepository.findById(comicsId).orElseThrow();
//            comicsElasticDocument.setImageBase64(baseImage);
//            elasticRepository.save(comicsElasticDocument);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
