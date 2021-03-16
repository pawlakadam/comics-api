package pl.pawlak.comicsbox.application.comics.read;

import java.util.UUID;

public interface ComicsReadRepository {
    ComicsBasicData getById(UUID comicsUid);

    byte[] getImageByComicsId(UUID comicsId);
}
