package pl.pawlak.comicsbox.infrastructure.comics.write;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsElasticDocument;

import java.util.UUID;

public interface ComicsElasticSearchRepository extends ElasticsearchRepository<ComicsElasticDocument, UUID> {
}
