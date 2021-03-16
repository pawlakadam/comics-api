package pl.pawlak.comicsbox.application.comics.write.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteData;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteRepository;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteService;

import java.util.UUID;

@Service
public class ComicsWriteServiceImpl implements ComicsWriteService {

    private ComicsWriteRepository comicsWriteRepository;

    public ComicsWriteServiceImpl(ComicsWriteRepository comicsWriteRepository) {
        this.comicsWriteRepository = comicsWriteRepository;
    }

    @Override
    public UUID addComics(ComicsWriteData comicsWriteData) {
        return comicsWriteRepository.addComics(comicsWriteData);
    }

    @Override
    public void addImage(UUID comicsId, MultipartFile file) {
        comicsWriteRepository.addImage(comicsId, file);
    }
}
