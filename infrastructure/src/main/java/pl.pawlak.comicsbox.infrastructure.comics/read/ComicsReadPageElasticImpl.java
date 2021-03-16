package pl.pawlak.comicsbox.infrastructure.comics.read;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadPage;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsElasticDocument;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("elasticComics")
public class ComicsReadPageElasticImpl implements ComicsReadPage {
    private ElasticsearchTemplate elasticsearchTemplate;

    public ComicsReadPageElasticImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }


    @Override
    public Page<ComicsBasicData> findBy(String textSearch, Pageable pageable) {
        SearchQuery searchQuery;
        if (textSearch.equals("")) {
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.matchAllQuery())
                    .build();
        } else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.multiMatchQuery(textSearch, "title", "publishingHouse")
                            .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                            .operator(Operator.AND))
                    .build();
        }

        List<ComicsElasticDocument> comicsElasticDocuments = elasticsearchTemplate.queryForList(searchQuery, ComicsElasticDocument.class);
        PageImpl<ComicsElasticDocument> userDataReadEntityPages = new PageImpl<>(comicsElasticDocuments, pageable, comicsElasticDocuments.size());

        return userDataReadEntityPages.map(this::convert);
    }


    private ComicsBasicData convert(ComicsElasticDocument comicsElasticDocument) {
        byte[] fileContent = getImageByComicsId(comicsElasticDocument.getComicsId());
        return new ComicsBasicData(comicsElasticDocument.getComicsId(), comicsElasticDocument.getTitle(), comicsElasticDocument.getPublishingHouse(), comicsElasticDocument.getComicsNumber(), fileContent);
    }

    private byte[] getImageByComicsId(UUID comicsId) {
//        try{
//        return FileUtils.readFileToByteArray(new File("C:\\Users\\Adam\\Desktop\\picture\\d10703e3-57d8-49fe-a599-d81dfb18531dn.jpg"));
//        } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
        byte[] imagebytes = FileUtils.listFiles(new File("C:\\Users\\Adam\\Desktop\\picture\\"), null, true).stream()
                .filter(file -> file.getName().contains(comicsId.toString()))
                .findAny()
                .map(file -> {
                    try {
                        return FileUtils.readFileToByteArray(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .orElse(new byte[0]);
        return imagebytes;
    }
}
