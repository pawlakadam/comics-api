package pl.pawlak.comicsbox.infrastructure.comics.entity;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import pl.pawlak.comicsbox.infrastructure.comics.common.UuidBasicType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "COMICS_READ_MODEL", schema = "COMICS")
@TypeDefs({
        @TypeDef(defaultForType = UUID.class, typeClass = UuidBasicType.class)
})
public class ComicsReadEntity {
    @Id
    @Column(name = "COMICS_ID", updatable = false)
    private UUID comicsId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PUBLISHING_HOUSE")
    private String publishingHouse;

    @Column(name = "COMICS_NUMBER")
    private Long number;

    ComicsReadEntity() {
    }

    public UUID getComicsId() {
        return comicsId;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public Long getNumber() {
        return number;
    }
}
