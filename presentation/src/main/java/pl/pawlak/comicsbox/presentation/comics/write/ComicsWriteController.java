package pl.pawlak.comicsbox.presentation.comics.write;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteData;
import pl.pawlak.comicsbox.application.comics.write.ComicsWriteService;
import pl.pawlak.comicsbox.presentation.comics.write.request.ComicsRequest;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RestController
public class ComicsWriteController {

    private ComicsWriteService comicsWriteService;

    public ComicsWriteController() {
        this.comicsWriteService = comicsWriteService;
    }

    @PostMapping("/comics")
    public HttpEntity addComics(@RequestBody ComicsRequest comicsRequest) {
        ComicsWriteData comicsWriteData = new ComicsWriteData(UUID.randomUUID(), comicsRequest.getTitle(), comicsRequest.getPublishingHouse(), comicsRequest.getComicsNumber());
        UUID comicsId = comicsWriteService.addComics(comicsWriteData);
        return new ResponseEntity<>(comicsId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/comics/{comicsUuidAsString}/image", consumes = "multipart/form-data")
    public HttpEntity addImage(@PathVariable String comicsUuidAsString,
                               @RequestParam("myFile") MultipartFile file) throws IOException {
        UUID comicsId = UUID.fromString(comicsUuidAsString);
        comicsWriteService.addImage(comicsId, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpEntity addImage(@RequestParam(value = "file", required = false) MultipartFile content) throws IOException {
        byte[] fileContent = content.getBytes();
        String name = content.getName();
        System.out.println(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
