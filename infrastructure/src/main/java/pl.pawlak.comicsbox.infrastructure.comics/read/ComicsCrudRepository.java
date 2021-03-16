package pl.pawlak.comicsbox.infrastructure.comics.read;

import org.springframework.data.repository.CrudRepository;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsReadEntity;

import java.util.UUID;

public interface ComicsCrudRepository extends CrudRepository<ComicsReadEntity, UUID> {
}
