package pl.pawlak.comicsbox.presentation.comics.read;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pawlak.comicsbox.application.comics.read.ComicsBasicData;
import pl.pawlak.comicsbox.application.comics.read.ComicsReadService;
import pl.pawlak.comicsbox.presentation.comics.read.resource.ImageResource;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController()
public class ComicsReadController {
    private ComicsReadService comicsReadService;
    private ResourceAssemblerSupport comicsDataResponseAssembler;

    public ComicsReadController(ComicsReadService comicsReadService,
                                @Qualifier("comicsDataResponseAssembler") ResourceAssemblerSupport comicsDataResponseAssembler) {
        this.comicsReadService = comicsReadService;
        this.comicsDataResponseAssembler = comicsDataResponseAssembler;
    }

    @GetMapping(value = "/comics")
    public List<ComicsBasicData> findBy(@RequestParam(defaultValue = "") String textSearch,
                                 Pageable pageable,
                                 PagedResourcesAssembler pagedAssembler) {
        Page<ComicsBasicData> result = comicsReadService.findBy(textSearch, pageable);
        PagedResources pagedResources = pagedAssembler.toResource(result, comicsDataResponseAssembler);

        ComicsBasicData comicsBasicData = new ComicsBasicData(UUID.randomUUID(), "title", "publish", 1L, null);
        List<ComicsBasicData> content = List.of(comicsBasicData);
//        if (content.size() == 0) {
//            EmbeddedWrappers wrappers = new EmbeddedWrappers(true);
//            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(ComicsBasicData.class);
//            List<EmbeddedWrapper> embeddedWrappers = Arrays.asList(wrapper);
//            return new PagedResources<>(embeddedWrappers, pagedResources.getMetadata(), pagedResources.getLinks());
//        }

        return content;
    }

    @GetMapping(value = "/comics/{uuidAsString}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public HttpEntity<ImageResource> getImageByComics(@PathVariable String uuidAsString){
        UUID comicsId = UUID.fromString(uuidAsString);
        byte[] bytes = comicsReadService.getImageByComicsId(comicsId);
        String fileContent = Base64.getEncoder().encodeToString(bytes);
        ImageResource imageResource = new ImageResource(fileContent);
        return new ResponseEntity<>(imageResource, HttpStatus.OK);
    }

    @GetMapping(value = "/comics/{uuidAsString}")
    public HttpEntity<ResourceSupport> getById(@PathVariable String uuidAsString) {
        ComicsBasicData comicsBasicData = comicsReadService.findById(UUID.fromString(uuidAsString));
        ResourceSupport resourceSupport = comicsDataResponseAssembler.toResource(comicsBasicData);
        return new ResponseEntity<>(resourceSupport, HttpStatus.OK);
    }
}
