package pl.pawlak.comicsbox.presentation.comics.read.resource;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.presentation.comics.read.ComicsReadController;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Date: 17.01.19
 * Time: 12:01
 *
 * @author tfert
 */

@Component("comicsDataResponseAssembler")
public class ComicsDataResponseAssembler extends ResourceAssemblerSupport<ComicsBasicData, ComicsDataResource> {

    private Logger logger = LoggerFactory.getLogger(ComicsDataResponseAssembler.class);

    public ComicsDataResponseAssembler() {
        super(RestController.class, ComicsDataResource.class);
    }

    @Override
    protected ComicsDataResource instantiateResource(ComicsBasicData comicsBasicData) {
//        String s = comicsBasicData.getFileConetnt() == null ? null : Base64.getEncoder().encodeToString(comicsBasicData.getFileConetnt());
        return new ComicsDataResource(
                comicsBasicData.getComicsId().toString(),
                comicsBasicData.getTitle(),
                comicsBasicData.getPublishingHouse(),
                comicsBasicData.getNumber(),
                comicsBasicData.getFileConetnt());
    }

    @Override
    public ComicsDataResource toResource(ComicsBasicData comicsBasicData) {

        ComicsDataResource resource = instantiateResource(comicsBasicData);

        try {
            resource.add(ControllerLinkBuilder
                    .linkTo(
                            ControllerLinkBuilder
                                    .methodOn(ComicsReadController.class)
                                    .getById(comicsBasicData.getComicsId().toString())
                    )
                    .withSelfRel());
        } catch (Exception e) {
            // wyjątek nie wystąpi, bo to nie jest wywołanie metody?
            logger.error("Ten błąd nie powinien nigdy wystąpić... :s", e);
        }

        return resource;
    }

}
