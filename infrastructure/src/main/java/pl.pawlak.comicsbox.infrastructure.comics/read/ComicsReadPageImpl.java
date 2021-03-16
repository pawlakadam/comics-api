package pl.pawlak.comicsbox.infrastructure.comics.read;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadPage;
import pl.pawlak.comicsbox.infrastructure.comics.entity.ComicsReadEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Profile("h2Comics")
public class ComicsReadPageImpl implements ComicsReadPage {
    private EntityManager entityManager;

    public ComicsReadPageImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<ComicsBasicData> findBy(String textSearch, Pageable pageable) {
        StringBuilder hqlBuilder = new StringBuilder("SELECT comics ");
        StringBuilder hqlFromBuilder = new StringBuilder("FROM ComicsReadEntity comics where comics.title = 'dasdasdas'");
        hqlBuilder.append(hqlFromBuilder);
        TypedQuery<ComicsReadEntity> query = entityManager.createQuery(hqlBuilder.toString(), ComicsReadEntity.class);

        Integer totalCount = calculateTotalCount(hqlFromBuilder.toString());

        List<ComicsReadEntity> resultList = executeQuery(pageable, query);

        PageImpl<ComicsReadEntity> userDataReadEntityPages = new PageImpl<>(resultList, pageable, 0);

        return userDataReadEntityPages.map(this::convert);
    }

    private Integer calculateTotalCount(String sql) {
        String hqlTotalCount = "SELECT COUNT(*) " + sql;
        TypedQuery<Long> query = entityManager.createQuery(hqlTotalCount, Long.class);

        return query.getSingleResult().intValue();
    }

    private List<ComicsReadEntity> executeQuery(Pageable pageable, TypedQuery<ComicsReadEntity> query) {
        long offsetLong = pageable.getOffset();
        int offset = Long.valueOf(offsetLong).intValue();
        int pageSize = pageable.getPageSize();

        query.setFirstResult(offset);
        query.setMaxResults(pageSize);

        List<ComicsReadEntity> resultList = query.getResultList();
        return resultList;
    }

    private ComicsBasicData convert(ComicsReadEntity comicsReadEntity) {
        return new ComicsBasicData(comicsReadEntity.getComicsId(), comicsReadEntity.getTitle(), comicsReadEntity.getPublishingHouse(), comicsReadEntity.getNumber(), null);
    }
}
