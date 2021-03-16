package pl.pawlak.comicsbox.application.comics.read;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComicsReadPage {
    Page<ComicsBasicData> findBy(String textSearch, Pageable pageable);
}
