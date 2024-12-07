package online.mizak.phdict.dictionary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dictionary")
class DictionaryController {

    private final DictionaryFacade dictionaryFacade;

    DictionaryController(DictionaryFacade dictionaryFacade) {
        this.dictionaryFacade = dictionaryFacade;
    }

    record CreateDictionaryProduct(
            String eanCode,
            String tradeName
    ) {
    }

    @PostMapping
    ResponseEntity<?> createDictionaryProduct(@RequestBody CreateDictionaryProduct createDictionaryProduct) {
        dictionaryFacade.createDictionaryProduct(createDictionaryProduct.eanCode, createDictionaryProduct.tradeName);
        return ResponseEntity.ok().build();
    }

}
