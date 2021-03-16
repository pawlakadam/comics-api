package pl.pawlak.comicsbox.application.comics.read;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ComicsReadService {

    ComicsBasicData findById(UUID comicsUuid);

    Page<ComicsBasicData> findBy(String textSearch, Pageable pageable);

    byte[] getImageByComicsId(UUID comicsId);
}
