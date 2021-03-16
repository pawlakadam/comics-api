package pl.pawlak.comicsbox.application.comics.read.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadPage;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadRepository;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadService;

import java.util.List;
import java.util.UUID;

@Service
public class ComicsReadServiceImpl implements ComicsReadService {

    private ComicsReadPage comicsReadPage;
    private ComicsReadRepository comicsReadRepository;

    public ComicsReadServiceImpl( ComicsReadRepository comicsReadRepository) {
        this.comicsReadPage = comicsReadPage;
        this.comicsReadRepository = comicsReadRepository;
    }

    @Override
    public ComicsBasicData findById(UUID comicsUuid) {
        return comicsReadRepository.getById(comicsUuid);
    }

    @Override
    public Page<ComicsBasicData> findBy(String textSearch, Pageable pageable) {
        return comicsReadPage.findBy(textSearch, pageable);
    }

    @Override
    public byte[] getImageByComicsId(UUID comicsId) {
        return comicsReadRepository.getImageByComicsId(comicsId);
    }
}
